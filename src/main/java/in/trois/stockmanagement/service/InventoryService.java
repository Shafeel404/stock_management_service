package in.trois.stockmanagement.service;


import in.trois.stockmanagement.annotation.ReadTransactional;
import in.trois.stockmanagement.annotation.WriteTransactional;
import in.trois.stockmanagement.constants.Status;
import in.trois.stockmanagement.entity.Inventory;
import in.trois.stockmanagement.entity.mapping.InventoryCommercialsMapping;
import in.trois.stockmanagement.exception.RestException;
import in.trois.stockmanagement.repository.InventoryRepository;
import in.trois.stockmanagement.repository.mapping.InventoryCommercialMappingRepository;
import in.trois.stockmanagement.request.InventoryDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryService
        extends AbstractJpaService<InventoryDto, UUID, InventoryRepository, Inventory> {

    @Autowired private InventoryCommercialMappingRepository inventoryCommercialMappingRepository;

    @WriteTransactional
    public String saveInventory(InventoryDto dto) {
        String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM"));
        dto.setMonth(currentMonth);
        Inventory inventory = super.save(dto);
        InventoryCommercialsMapping inventoryCommercialsMapping =
                dto.getInventoryCommercialsMapping().toEntity();
        inventoryCommercialsMapping.setInventoryId(inventory.getId());
        inventoryCommercialMappingRepository.save(inventoryCommercialsMapping);
        return "Inventory saved successfully";
    }

    @ReadTransactional
    public InventoryDto findById(UUID id) {
        Inventory inventory =
                repository
                        .findById(id)
                        .orElseThrow(() -> new RestException("No inventory found for id: " + id));
        InventoryCommercialsMapping commercialsMapping =
                inventoryCommercialMappingRepository
                        .findByInventoryId(id)
                        .orElseThrow(
                                () ->
                                        new RestException(
                                                "No inventory commercial found for this inventory with id: "
                                                        + id));

        InventoryDto inventoryDto = inventory.toDTO();
        inventoryDto.setInventoryCommercialsMapping(commercialsMapping.toDTO());
        return inventoryDto;
    }

    @ReadTransactional
    public InventoryDto getInventoryByProductCode(String productCode) {

        Inventory inventory =
                repository
                        .findByProductCode(productCode)
                        .orElseThrow(
                                () ->
                                        new RestException(
                                                "No inventory found for product code: "
                                                        + productCode));

        InventoryCommercialsMapping inventoryCommercialsMapping =
                inventoryCommercialMappingRepository
                        .findByInventoryId(inventory.getId())
                        .orElseThrow(
                                () ->
                                        new RestException(
                                                "No inventory commercial details found for product code: "
                                                        + productCode));

        InventoryDto response = inventory.toDTO();
        response.setInventoryCommercialsMapping(inventoryCommercialsMapping.toDTO());
        return response;
    }

    @ReadTransactional
    public List<InventoryDto> getInventoriesByProductCodes(List<String> productCodes) {
        List<InventoryDto> response = new ArrayList<>();
        List<Inventory> inventory =
                repository.findByProductCodeInAndStatus(productCodes, Status.ALLOWED);
        if (!inventory.isEmpty()) {
            inventory.forEach(item -> response.add(item.toDTO()));
        }
        return response;
    }

    @WriteTransactional
    public String deductProductCountByInvoiceGeneration(UUID id, Integer quantity) {
        Inventory inventory =
                repository.findById(id).orElseThrow(() -> new RestException("Inventory not found"));
        Integer currentQuantity = inventory.getRemainingQuantity();
        if (currentQuantity >= quantity) {
            inventory.setRemainingQuantity(currentQuantity - quantity);
            repository.save(inventory);
            return "Product count deducted successfully";
        }
        throw new RestException("Insufficient product count");
    }
}
