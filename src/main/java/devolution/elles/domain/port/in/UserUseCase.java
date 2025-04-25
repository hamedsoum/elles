package devolution.elles.domain.port.in;

import devolution.elles.domain.model.User;

import java.util.List;

public interface UserUseCase {
    User save(User user);

    List<User> findAll();

    User update(String id, User user);

    void delete(String id);

    User retrieve(String email);

}
