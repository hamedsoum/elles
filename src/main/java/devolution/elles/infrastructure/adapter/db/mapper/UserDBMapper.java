package devolution.elles.infrastructure.adapter.db.mapper;

import devolution.elles.domain.model.User;
import devolution.elles.infrastructure.adapter.db.entity.UserEntity;
import devolution.elles.infrastructure.adapter.db.jparepository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component

public class UserDBMapper {

    private final JpaUserRepository jpaUserRepository;

    public UserDBMapper(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhoneNumber(),
                entity.getEmail()
        );
    }

    public UserEntity toEntity(User user) {
        Optional<UserEntity> existingEntity = this.jpaUserRepository.findById(user.id());
        return new UserEntity(
                user.id(),
                user.firstname(),
                user.lastname(),
                user.phoneNumber(),
                user.email(),
                existingEntity.<String>map(UserEntity::getPassword).orElse(null),
                false,
                existingEntity.map(UserEntity::getRole).orElse(null)
        );
    }
}
