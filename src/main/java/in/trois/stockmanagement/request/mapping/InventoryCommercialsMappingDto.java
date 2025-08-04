package in.trois.stockmanagement.request.mapping;

import in.trois.stockmanagement.entity.mapping.InventoryCommercialsMapping;

import lombok.Data;

import java.util.UUID;

@Data
public class InventoryCommercialsMappingDto {

    private UUID id;

    private UUID inventoryId;

    private Double costPrice;

    private Double maximumRetailPrice;

    private Double sellingPrice;

    private Double tax;

    private Double nonTaxBillSellingPrice;

    public InventoryCommercialsMapping toEntity() {
        InventoryCommercialsMapping entity = new InventoryCommercialsMapping();
        entity.setId(UUID.randomUUID());
        entity.setCostPrice(costPrice);
        entity.setMaximumRetailPrice(maximumRetailPrice);
        entity.setSellingPrice(sellingPrice);
        entity.setNonTaxBillSellingPrice(nonTaxBillSellingPrice);
        entity.setTax(tax);
        return entity;
    }
}
