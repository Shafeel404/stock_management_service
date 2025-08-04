package in.trois.stockmanagement.request;

import static org.springframework.http.HttpMethod.PATCH;

import in.trois.stock.auth.lib.service.dto.AbstractDto;
import in.trois.stockmanagement.entity.Inventory;
import in.trois.stockmanagement.request.mapping.InventoryCommercialsMappingDto;
import in.trois.stockmanagement.request.master.CategoryDto;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpMethod;

import java.util.UUID;

@Getter
@Setter
public class InventoryDto extends AbstractDto {

    private UUID id;

    private String productName;

    private String productCode;

    private String description;

    private String hsnSacCode;

    private UUID categoryId;

    private CategoryDto category;

    private InventoryCommercialsMappingDto inventoryCommercialsMapping;

    private Integer remainingQuantity;

    private Integer totalQuantity;

    private Integer quantity;

    private String month;

    @Override
    @SuppressWarnings("unchecked")
    public Inventory toEntity() {
        Inventory entity = new Inventory();
        entity.setId(UUID.randomUUID());
        entity.setProductName(productName);
        entity.setProductCode(productCode);
        entity.setDescription(description);
        entity.setHsnSacCode(hsnSacCode);
        entity.setCategoryId(category.getId());
        entity.setTotalQuantity(totalQuantity);
        entity.setRemainingQuantity(totalQuantity);
        entity.setMonth(month);
        return entity;
    }

    @Override
    public boolean isValid(HttpMethod httpMethod) {
        if (httpMethod.equals(PATCH)) {}

        return getErrors() == null || getErrors().isEmpty();
    }
}
