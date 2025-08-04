package in.trois.stockmanagement.request.master;

import static org.springframework.http.HttpMethod.PATCH;

import in.trois.stock.auth.lib.service.dto.AbstractDto;
import in.trois.stock.auth.lib.service.utils.ValidationUtils;
import in.trois.stockmanagement.entity.master.Role;

import lombok.Data;

import org.springframework.http.HttpMethod;

import java.util.UUID;

@Data
public class RoleDto extends AbstractDto {

    private UUID id;

    private String name;

    private String description;

    @Override
    @SuppressWarnings("unchecked")
    public Role toEntity() {
        Role entity = new Role();
        entity.setId(UUID.randomUUID());
        entity.setName(name);
        entity.setDescription(description);
        return entity;
    }

    @Override
    public boolean isValid(HttpMethod httpMethod) {
        if (httpMethod.equals(PATCH)) {
            if (!ValidationUtils.isValid(id)) {
                addError("id", id);
            }
            if (!ValidationUtils.isValid(name)) {
                addError("name", name);
            }
        }
        return getErrors() == null || getErrors().isEmpty();
    }
}
