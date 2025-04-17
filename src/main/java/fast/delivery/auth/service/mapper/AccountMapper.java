package fast.delivery.auth.service.mapper;

import fast.delivery.auth.service.dto.request.RegisterRequestDto;
import fast.delivery.auth.service.dto.response.RegisteredUserEventDto;
import fast.delivery.auth.service.model.AccountEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "externalId", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "passwordHash", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    @Mapping(target = "role", constant = "USER")
    @Mapping(target = "_2fa_enabled", constant = "false")
    @Mapping(target = "blocked", constant = "false")
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AccountEntity toEntity(RegisterRequestDto dto, @Context PasswordEncoder passwordEncoder);


    @Mapping(target = "externalId", source = "entity.externalId")
    @Mapping(target = "firstName", source = "dto.firstName")
    @Mapping(target = "lastName", source = "dto.lastName")
    @Mapping(target = "phoneNumber", source = "dto.phoneNumber")
    @Mapping(target = "createdAt", source = "entity.createdAt")
    @Mapping(target = "dateOfBirth", source = "dto.dateOfBirth")
    RegisteredUserEventDto toUserEventDto(AccountEntity entity, RegisterRequestDto dto);

}
