package fast.delivery.auth.service.service.auth;

import fast.delivery.auth.service.dto.request.LoginRequestDto;
import fast.delivery.auth.service.dto.request.RefreshTokenRequestDto;
import fast.delivery.auth.service.dto.request.RegisterRequestDto;
import fast.delivery.auth.service.dto.response.LoginResponseDto;
import fast.delivery.auth.service.dto.response.RefreshTokenResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginDto);
    void register(RegisterRequestDto registerDto);
    RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshDto);
}
