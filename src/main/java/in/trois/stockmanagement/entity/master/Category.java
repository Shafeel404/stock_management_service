package in.trois.stockmanagement.entity.master;

import in.trois.stock.auth.lib.service.dto.AbstractDto;
import in.trois.stock.auth.lib.service.entity.AbstractEntity;
import in.trois.stock.auth.lib.service.payload.DropdownPayload;
import in.trois.stock.auth.lib.service.utils.ValidationUtils;
import in.trois.stockmanagement.request.master.CategoryDto;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "m_category", schema = "stock_management")
public class Category extends AbstractEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Override
    public <K extends AbstractDto> void copyFromDTO(K dto) {
        CategoryDto categoryDto = (CategoryDto) dto;
        if (ValidationUtils.isValid(categoryDto.getName())) {
            name = categoryDto.getName();
        }
        if (ValidationUtils.isValid(categoryDto.getDescription())) {
            description = categoryDto.getDescription();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public CategoryDto toDTO() {
        CategoryDto dto = new CategoryDto();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        return dto;
    }

    @Override
    public DropdownPayload<UUID> toDropDownPayload() {
        DropdownPayload<UUID> payLoad = new DropdownPayload<>();
        payLoad.setId(id);
        payLoad.setName(name);
        return payLoad;
    }
}
