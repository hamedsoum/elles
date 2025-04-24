package devolution.elles.domain.port.out;

import devolution.elles.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByID(String id);
    List<User> findAll();
    User save(User user);
    void delete(String id);
}
