package in.trois.stockmanagement.entity.master;


import in.trois.stockmanagement.entity.AbstractEntity;
import in.trois.stockmanagement.payload.DropdownPayload;
import in.trois.stockmanagement.request.AbstractDto;
import in.trois.stockmanagement.request.master.CategoryDto;

import in.trois.stockmanagement.utils.ValidationUtils;
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
