package in.trois.stockmanagement.request;

import in.trois.stockmanagement.utils.ValidationUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.UUID;

import static ch.qos.logback.classic.util.StatusViaSLF4JLoggerFactory.addError;
import static in.trois.stockmanagement.entity.Counter_.counterNumber;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private UUID id;
    private String username;
    private String email;
    private String phoneNumber;
    private String fullName;
    private String password;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public boolean isValid(HttpMethod httpMethod) {
        if (httpMethod.equals(POST)) {
            if (!ValidationUtils.isValid(username)) {
                addError("username", username);
            }
            if (!ValidationUtils.isValid(email)) {
                addError("email", email);
            }
            if (!ValidationUtils.isValid(phoneNumber)) {
                addError("phoneNumber", phoneNumber);
            }
        }
        return true;
    }
} 