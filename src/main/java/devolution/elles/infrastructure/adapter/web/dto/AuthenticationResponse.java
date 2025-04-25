package devolution.elles.infrastructure.adapter.web.dto;

import devolution.elles.infrastructure.adapter.db.entity.UserEntity;

public record AuthenticationResponse(String token, UserEntity user) {
}
