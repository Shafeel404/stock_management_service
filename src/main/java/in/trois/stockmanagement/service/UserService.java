package in.trois.stockmanagement.service;

import in.trois.stock.auth.lib.service.annotation.WriteTransactional;
import in.trois.stock.auth.lib.service.exception.RestException;
import in.trois.stock.auth.lib.service.service.AbstractJpaService;
import in.trois.stock.auth.lib.service.utils.ValidationUtils;
import in.trois.stockmanagement.entity.User;
import in.trois.stockmanagement.repository.UserRepository;
import in.trois.stockmanagement.request.AuthRequestDto;
import in.trois.stockmanagement.request.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService extends AbstractJpaService<UserDto, UUID, UserRepository, User> {

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private UserRepository userRepository;

    @WriteTransactional
    public void register(AuthRequestDto requestDto) {
        String encodedPassword =
                passwordEncoder.encode(requestDto.getPassword()); // Hash the password
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setPassword(encodedPassword);
        user.setRoleId(
                ValidationUtils.isValid(requestDto.getRoleId())
                        ? requestDto.getRoleId().getId()
                        : null);
        user.setEmail(requestDto.getEmail());
        user.setFullName(requestDto.getFullName());
        user.setPhoneNumber(requestDto.getPhoneNumber());
        user.setId(UUID.randomUUID());
        user.setCounterId(
                ValidationUtils.isValid(requestDto.getCounter())
                        ? requestDto.getCounter().getId()
                        : null);
        userRepository.save(user);
    }

    @WriteTransactional
    public void update(AuthRequestDto requestDto) {
        User user =
                userRepository
                        .findById(requestDto.getId())
                        .orElseThrow(() -> new RestException("User not found"));
        if (ValidationUtils.isValid(requestDto.getPassword())) {
            String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
            user.setPassword(encodedPassword);
        }
        if (ValidationUtils.isValid(requestDto.getRoleId())) {
            user.setRoleId(requestDto.getRoleId().getId());
        }
        if (ValidationUtils.isValid(requestDto.getUsername())) {
            user.setUsername(requestDto.getUsername());
        }
        if (ValidationUtils.isValid(requestDto.getEmail())) {
            user.setEmail(requestDto.getEmail());
        }
        if (ValidationUtils.isValid(requestDto.getFullName())) {
            user.setFullName(requestDto.getFullName());
        }
        if (ValidationUtils.isValid(requestDto.getPhoneNumber())) {
            user.setPhoneNumber(requestDto.getPhoneNumber());
        }
        if (ValidationUtils.isValid(requestDto.getCounter())) {
            user.setCounterId(requestDto.getCounter().getId());
        }
        userRepository.save(user);
    }
}
