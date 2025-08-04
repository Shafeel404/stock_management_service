package in.trois.stockmanagement.entity;

import in.trois.stock.auth.lib.service.dto.AbstractDto;
import in.trois.stock.auth.lib.service.entity.AbstractEntity;
import in.trois.stock.auth.lib.service.utils.ValidationUtils;
import in.trois.stockmanagement.entity.master.Role;
import in.trois.stockmanagement.request.UserDto;

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
@Table(name = "user", schema = "stock_management")
public class User extends AbstractEntity {

    @Id private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "full_name")
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "role_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    private Role role;

    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "counter_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    private Counter counter;

    @Column(name = "counter_id")
    private UUID counterId;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @SuppressWarnings("unchecked")
    public UserDto toDTO() {
        UserDto dto = new UserDto();
        dto.setId(id);
        dto.setUsername(username);
        dto.setEmail(email);
        dto.setPhoneNumber(phoneNumber);
        dto.setFullName(fullName);
        dto.setRole(ValidationUtils.isValid(role) ? role.toDTO() : null);
        dto.setCounter(ValidationUtils.isValid(counter) ? counter.toDTO() : null);
        return dto;
    }

    @Override
    public <K extends AbstractDto> void copyFromDTO(K dto) {}
}
