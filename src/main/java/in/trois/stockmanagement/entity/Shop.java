package in.trois.stockmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import in.trois.stock.auth.lib.service.dto.AbstractDto;
import in.trois.stock.auth.lib.service.entity.AbstractEntity;
import in.trois.stockmanagement.request.ShopDto;

@Entity
@Table(name = "shop", schema = "stock_management")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shop extends AbstractEntity {
    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private String address;
    private String phoneNumber;
    private String email;
    @Override
    public <K extends AbstractDto> void copyFromDTO(K dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'copyFromDTO'");
    }
    @SuppressWarnings("unchecked")
    @Override
    public ShopDto toDTO() {
        ShopDto dto = new ShopDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setAddress(this.address);
        dto.setPhoneNumber(this.phoneNumber);
        dto.setEmail(this.email);
        return dto;
    }


} 