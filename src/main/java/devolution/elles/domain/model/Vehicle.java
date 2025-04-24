package devolution.elles.domain.model;

import devolution.elles.domain.enums.VehicleCategory;

import java.time.Instant;

public record Vehicle(
        String id,
        Instant vehicleFirstRegistrationDate,
        String licenceNumber,
        String color,
        int numberOfSeat,
        int numberOfDoor,
        VehicleCategory category) {
}
