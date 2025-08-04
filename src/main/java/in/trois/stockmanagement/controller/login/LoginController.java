package in.trois.stockmanagement.controller.login;

import in.trois.stockmanagement.request.AuthRequestDto;
import in.trois.stockmanagement.response.AuthResponseDto;
import in.trois.stockmanagement.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class LoginController {

    private final AuthService authService;

    @PostMapping("/no-auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto request) {
        AuthResponseDto token = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(Map.of("tokenResponse", token));
    }
}
