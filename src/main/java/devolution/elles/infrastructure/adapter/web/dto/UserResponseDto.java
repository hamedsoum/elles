package devolution.elles.infrastructure.adapter.web.dto;

public record UserResponseDto(String id, String firstName, String lastName, String phoneNumber, String role,
                              String email) {
}
