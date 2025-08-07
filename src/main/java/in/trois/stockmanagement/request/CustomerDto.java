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
                return false;
            }
            if (!ValidationUtils.isValid(email)) {
                return false;
            }
            return ValidationUtils.isValid(phoneNumber);
        }
        return true;
    }
} 