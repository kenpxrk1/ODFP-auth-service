package fast.delivery.auth.service.mapper;

import fast.delivery.auth.service.dto.response.LoginResponseDto;
import fast.delivery.auth.service.dto.response.RefreshTokenResponseDto;
import fast.delivery.auth.service.model.AccountEntity;
import fast.delivery.auth.service.model.TokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TokenMapper {
    @Mapping(target = "account", source = "accountEntity")
    @Mapping(target = "refreshToken", source = "refreshToken")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "expireTime", ignore = true)
    TokenEntity toTokenEntity(AccountEntity accountEntity, UUID refreshToken);

    @Mapping(target = "accessToken", source = "accessToken")
    @Mapping(target = "refreshToken", source = "refreshToken")
    LoginResponseDto toLoginResponse(String accessToken, UUID refreshToken);

    @Mapping(target = "accessToken", source = "accessToken")
    @Mapping(target = "refreshToken", source = "refreshToken")
    RefreshTokenResponseDto toRefreshReponse(String accessToken, UUID refreshToken);
}
