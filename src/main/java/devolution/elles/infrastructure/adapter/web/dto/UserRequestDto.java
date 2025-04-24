package devolution.elles.infrastructure.adapter.web.dto;

import devolution.elles.domain.enums.RoleType;

public record UserRequestDto(
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        RoleType role,
        String password
) {
}
