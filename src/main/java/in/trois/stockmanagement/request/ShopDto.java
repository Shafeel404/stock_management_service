package in.trois.stockmanagement.request;

import java.util.UUID;

import org.springframework.http.HttpMethod;

import in.trois.stock.auth.lib.service.dto.AbstractDto;
import in.trois.stockmanagement.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto extends AbstractDto {
    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    @Override
    public boolean isValid(HttpMethod arg0) {
        return false;
    }
    @SuppressWarnings("unchecked")
    @Override
    public Shop toEntity() {
        Shop shop = new Shop();
        shop.setId(this.id);
        shop.setName(this.name);
        shop.setAddress(this.address);
        shop.setPhoneNumber(this.phoneNumber);
        shop.setEmail(this.email);
        return shop;
    }

} 