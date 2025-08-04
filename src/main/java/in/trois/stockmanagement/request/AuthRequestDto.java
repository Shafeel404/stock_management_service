package in.trois.stockmanagement.request;

import in.trois.stockmanagement.request.master.RoleDto;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthRequestDto {
    private UUID id;
    private String username;
    private String password;
//    private RoleDto roleId;
    private String email;
    private String phoneNumber;
    private String fullName;
//    private CounterDto counter;
}
