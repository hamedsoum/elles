package devolution.elles.infrastructure.adapter.web.mapper;

import devolution.elles.domain.model.User;
import devolution.elles.infrastructure.adapter.db.entity.UserEntity;
import devolution.elles.infrastructure.adapter.web.dto.UserRequestDto;
import devolution.elles.infrastructure.adapter.web.dto.UserResponseDto;

import java.util.UUID;

public class UserDtoMapper {
    public static User toDomain(UserRequestDto userRequestDto) {
        return new User(
                UUID.randomUUID().toString(),
                userRequestDto.firstName(),
                userRequestDto.lastName(),
                userRequestDto.phoneNumber(),
                userRequestDto.email()
        );
    }

    public static UserResponseDto toUserResponseDto(UserEntity user) {
        return new UserResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getRole() == null ? null : user.getRole().getLibelle().toString(),
                user.getEmail()
        );
    }
}
