package in.trois.stockmanagement.entity.mapping;

import in.trois.stockmanagement.request.mapping.InventoryCommercialsMappingDto;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "inventory_commercials_mapping", schema = "stock_management")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCommercialsMapping {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "inventory_id")
    private UUID inventoryId;

    @Column(name = "cost_price", nullable = false)
    private Double costPrice;

    @Column(name = "maximum_retail_price", nullable = false)
    private Double maximumRetailPrice;

    @Column(name = "selling_price", nullable = false)
    private Double sellingPrice;

    @Column(name = "non_tax_bill_selling_price", nullable = false)
    private Double nonTaxBillSellingPrice;

    @Column(name = "tax")
    private Double tax;

    public InventoryCommercialsMappingDto toDTO() {
        InventoryCommercialsMappingDto dto = new InventoryCommercialsMappingDto();
        dto.setId(id);
        dto.setInventoryId(inventoryId);
        dto.setCostPrice(costPrice);
        dto.setMaximumRetailPrice(maximumRetailPrice);
        dto.setSellingPrice(sellingPrice);
        dto.setNonTaxBillSellingPrice(nonTaxBillSellingPrice);
        dto.setTax(tax);
        return dto;
    }
}
