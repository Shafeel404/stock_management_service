package in.trois.stockmanagement.entity.master;

import in.trois.stock.auth.lib.service.dto.AbstractDto;
import in.trois.stock.auth.lib.service.entity.AbstractEntity;
import in.trois.stock.auth.lib.service.payload.DropdownPayload;
import in.trois.stock.auth.lib.service.utils.ValidationUtils;
import in.trois.stockmanagement.request.master.RoleDto;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_role", schema = "stock_management")
public class Role extends AbstractEntity {

    @Id private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Override
    public <K extends AbstractDto> void copyFromDTO(K dto) {
        RoleDto roleDto = (RoleDto) dto;
        if (ValidationUtils.isValid(roleDto.getName())) {
            name = roleDto.getName();
        }
        if (ValidationUtils.isValid(roleDto.getDescription())) {
            description = roleDto.getDescription();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public RoleDto toDTO() {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(id);
        roleDto.setName(name);
        roleDto.setDescription(description);
        return roleDto;
    }

    @Override
    public DropdownPayload<UUID> toDropDownPayload() {
        DropdownPayload<UUID> payLoad = new DropdownPayload<>();
        payLoad.setId(id);
        payLoad.setName(name);
        return payLoad;
    }
}
