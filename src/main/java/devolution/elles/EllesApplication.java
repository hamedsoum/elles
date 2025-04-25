package devolution.elles;

import devolution.elles.domain.enums.RoleType;
import devolution.elles.domain.model.User;
import devolution.elles.infrastructure.adapter.db.repository.UserDetailRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class EllesApplication {

    public EllesApplication(UserDetailRepository userRepository) {

        userRepository.save(new User(
                        UUID.randomUUID().toString(),
                        "Admin",
                        "Admin",
                        "050505050505",
                        "admin@admin.ci"),
                "Justdoit",
                RoleType.ADMIN
        );
    }

    public static void main(String[] args) {
        SpringApplication.run(EllesApplication.class, args);
    }
}
