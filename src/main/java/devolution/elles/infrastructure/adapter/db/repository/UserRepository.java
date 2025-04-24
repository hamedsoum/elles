package devolution.elles.infrastructure.adapter.db.repository;

import devolution.elles.domain.model.User;
import devolution.elles.infrastructure.adapter.db.entity.UserEntity;
import devolution.elles.infrastructure.adapter.db.jparepository.JpaUserRepository;
import devolution.elles.infrastructure.adapter.db.mapper.UserDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository implements devolution.elles.domain.port.out.UserRepository {

    @Autowired
    UserDBMapper entityMapper;

    private final JpaUserRepository jpaUserRepository;

    public UserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findByID(String id) {
        return this.jpaUserRepository.findById(id)
                .map(UserDBMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return this.jpaUserRepository.findAll()
                .stream().map(UserDBMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        UserEntity entity = entityMapper.toEntity(user);
        UserEntity saved = this.jpaUserRepository.save(entity);

        return UserDBMapper.toDomain(saved);
    }

    @Override
    public void delete(String id) {
        this.jpaUserRepository.deleteById(id);
    }
}
