package devolution.elles.infrastructure.adapter.web.service;

import devolution.elles.domain.model.User;
import devolution.elles.domain.port.in.UserUseCase;
import devolution.elles.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserUseCase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User update(String id, User user) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
