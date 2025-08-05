package in.trois.stockmanagement.service;


import in.trois.stockmanagement.annotation.ReadTransactional;
import in.trois.stockmanagement.entity.Customer;
import in.trois.stockmanagement.exception.RestException;
import in.trois.stockmanagement.repository.CustomerRepository;
import in.trois.stockmanagement.response.AuthResponseDto;
import in.trois.stockmanagement.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    @ReadTransactional
    public AuthResponseDto login(String username, String password) {
        AuthResponseDto responseDto = new AuthResponseDto();
        Customer customer =
                customerRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new RestException("User not found"));
        if (!passwordEncoder.matches(password, customer.getPassword())) {
            throw new RestException("Invalid credentials");
        }
        responseDto.setUsername(customer.getUsername());
        responseDto.setFullName(customer.getFullName());
        responseDto.setToken(jwtUtil.generateToken(username, "Customer"));
        return responseDto;
    }
}
