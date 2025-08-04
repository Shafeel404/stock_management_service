package in.trois.stockmanagement.request.master;

import static org.springframework.http.HttpMethod.PATCH;
import in.trois.stockmanagement.entity.master.Category;

import in.trois.stockmanagement.request.AbstractDto;
import in.trois.stockmanagement.utils.ValidationUtils;
import lombok.Data;

import org.springframework.http.HttpMethod;

import java.util.UUID;

@Data
public class CategoryDto extends AbstractDto {

    private UUID id;

    private String name;

    private String description;

    @Override
    @SuppressWarnings("unchecked")
    public Category toEntity() {
        Category entity = new Category();
        entity.setId(UUID.randomUUID());
        entity.setName(name);
        entity.setDescription(description);
        return entity;
    }

    @Override
    public boolean isValid(HttpMethod httpMethod) {
        if (httpMethod.equals(PATCH)) {
            if (!ValidationUtils.isValid(name)) {
                addError("name", name);
            }
        }
        return getErrors() == null || getErrors().isEmpty();
    }
}
