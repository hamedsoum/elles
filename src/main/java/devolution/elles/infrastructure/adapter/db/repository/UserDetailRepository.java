package devolution.elles.infrastructure.adapter.db.repository;

import devolution.elles.domain.enums.RoleType;
import devolution.elles.domain.model.User;
import devolution.elles.infrastructure.adapter.db.entity.Role;
import devolution.elles.infrastructure.adapter.db.entity.UserEntity;
import devolution.elles.infrastructure.adapter.db.jparepository.JpaUserRepository;
import devolution.elles.infrastructure.adapter.db.mapper.UserDBMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@NoArgsConstructor
@Service
public class UserDetailRepository implements UserDetailsService {

    @Autowired
    UserDBMapper entityMapper;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserEntity save(User user, String password, RoleType role) {
        UserEntity entity = entityMapper.toEntity(user);
        String encodedPassword = this.passwordEncoder.encode(password);
        entity.setPassword(encodedPassword);

        Role userRole = new Role();
        userRole.setLibelle(role);
        entity.setRole(userRole);

        return this.jpaUserRepository.save(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.jpaUserRepository
                .findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur trouvé"));
    }
}
