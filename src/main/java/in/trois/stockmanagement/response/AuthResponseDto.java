package in.trois.stockmanagement.response;

import in.trois.stockmanagement.request.master.RoleDto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private String username;
    private String fullName;
    private RoleDto role;
}
