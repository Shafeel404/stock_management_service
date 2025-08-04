package in.trois.stockmanagement.entity;

import in.trois.stock.auth.lib.service.dto.AbstractDto;
import in.trois.stock.auth.lib.service.entity.AbstractEntity;
import in.trois.stock.auth.lib.service.payload.DropdownPayload;
import in.trois.stock.auth.lib.service.utils.ValidationUtils;
import in.trois.stockmanagement.entity.master.Category;
import in.trois.stockmanagement.request.InventoryDto;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "inventory", schema = "stock_management")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;

    @Column(name = "description")
    private String description;

    @Column(name = "hsn_sac_code")
    private String hsnSacCode;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, insertable = false, updatable = false)
    private Category category;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @Column(name = "remaining_quantity")
    private Integer remainingQuantity;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "month")
    private String month;

    @Override
    public <K extends AbstractDto> void copyFromDTO(K dto) {
        InventoryDto inventoryDto = (InventoryDto) dto;
        if (ValidationUtils.isValid(inventoryDto.getProductName())) {
            productName = inventoryDto.getProductName();
        }
        if (ValidationUtils.isValid(inventoryDto.getProductCode())) {
            productCode = inventoryDto.getProductCode();
        }
        if (ValidationUtils.isValid(inventoryDto.getDescription())) {
            description = inventoryDto.getDescription();
        }
        if (ValidationUtils.isValid(inventoryDto.getHsnSacCode())) {
            hsnSacCode = inventoryDto.getHsnSacCode();
        }
        if (ValidationUtils.isValid(inventoryDto.getCategory())) {
            categoryId = inventoryDto.getCategory().getId();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public InventoryDto toDTO() {
        InventoryDto dto = new InventoryDto();
        dto.setId(id);
        dto.setProductName(productName);
        dto.setProductCode(productCode);
        dto.setDescription(description);
        dto.setHsnSacCode(hsnSacCode);
        dto.setCategory(ValidationUtils.isValid(category) ? category.toDTO() : null);
        dto.setRemainingQuantity(remainingQuantity);
        dto.setTotalQuantity(totalQuantity);
        return dto;
    }

    @Override
    public DropdownPayload<UUID> toDropDownPayload() {
        DropdownPayload<UUID> payLoad = new DropdownPayload<>();
        payLoad.setId(id);
        payLoad.setName(productCode);
        return payLoad;
    }
}
