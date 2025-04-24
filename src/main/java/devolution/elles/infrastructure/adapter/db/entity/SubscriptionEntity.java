package devolution.elles.infrastructure.adapter.db.entity;

import devolution.elles.domain.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Table(name = "subscription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SubscriptionEntity {
    @Id
    private String id;
    private String productID;
    @OneToOne(cascade = CascadeType.ALL)
    private InsuredEntity insured;
    @OneToOne(cascade = CascadeType.ALL)
    private VehicleEntity vehicle;
    private SubscriptionStatus status;
    private Instant createdAt;
    private UUID createdBy;
    private Instant updatedAt;
    private UUID updatedBy;

    @PrePersist
    public void initId() {
        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
        }
    }
}
