package in.trois.stockmanagement.request;

import in.trois.stockmanagement.entity.AbstractEntity;
import in.trois.stockmanagement.request.master.RoleDto;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpMethod;

import java.util.UUID;

@Getter
@Setter
public class UserDto extends AbstractDto {

    private UUID id;

    private String username;

    private String password;

    private RoleDto role;

    private String email;

    private String phoneNumber;

    private String fullName;

    @Override
    public <T extends AbstractEntity> T toEntity() {
        return null;
    }

    @Override
    public boolean isValid(HttpMethod httpMethod) {
        return false;
    }
}
