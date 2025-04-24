package devolution.elles.infrastructure.adapter.db.entity;

import devolution.elles.domain.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // User user = User.builder().name("Alice").build();
@Entity
public class Role {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private RoleType libelle;

    @PrePersist
    public void initId() {
        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
        }
    }
}
