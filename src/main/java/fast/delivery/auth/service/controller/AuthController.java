package fast.delivery.auth.service.controller;

import fast.delivery.auth.service.dto.request.LoginRequestDto;
import fast.delivery.auth.service.dto.request.RefreshTokenRequestDto;
import fast.delivery.auth.service.dto.request.RegisterRequestDto;
import fast.delivery.auth.service.dto.response.LoginResponseDto;
import fast.delivery.auth.service.dto.response.RefreshTokenResponseDto;
import fast.delivery.auth.service.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("sign-up")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerDto) {
        authService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("sign-in")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping("refresh")
    public ResponseEntity<RefreshTokenResponseDto> refresh(@RequestBody RefreshTokenRequestDto requestDto) {
        return ResponseEntity.ok(authService.refreshToken(requestDto));
    }
}
