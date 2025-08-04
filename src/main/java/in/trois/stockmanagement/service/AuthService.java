package in.trois.stockmanagement.service;


import in.trois.stockmanagement.annotation.ReadTransactional;
import in.trois.stockmanagement.entity.User;
import in.trois.stockmanagement.entity.master.Role;
import in.trois.stockmanagement.exception.RestException;
import in.trois.stockmanagement.repository.UserRepository;
import in.trois.stockmanagement.response.AuthResponseDto;

import in.trois.stockmanagement.utils.JwtUtil;
import in.trois.stockmanagement.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ReadTransactional
    public AuthResponseDto login(String username, String password) {
        AuthResponseDto responseDto = new AuthResponseDto();
        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new RestException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RestException("Invalid credentials");
        }
        if (!ValidationUtils.isValid(user.getRole())) {
            throw new RestException("Wait for admin to assign you a role");
        }
        Role role = user.getRole();
        responseDto.setUsername(user.getUsername());
        responseDto.setFullName(user.getFullName());
        responseDto.setRole(role.toDTO());
        responseDto.setToken(jwtUtil.generateToken(username, role.getName()));
        return responseDto;
    }
}
