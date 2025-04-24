package devolution.elles.infrastructure.adapter.db.entity;

import devolution.elles.domain.enums.VehicleCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class VehicleEntity {
    @Id
    private String id;
    private Instant vehicleFirstRegistrationDate;
    private String licenceNumber;
    private String color;
    private int numberOfSeat;
    private int numberOfDoor;
    private VehicleCategory category;

    @PrePersist
    public void initId() {
        if (id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
        }
    }
}
