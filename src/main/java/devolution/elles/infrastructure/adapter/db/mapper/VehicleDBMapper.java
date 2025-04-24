package devolution.elles.infrastructure.adapter.db.mapper;

import devolution.elles.domain.model.Vehicle;
import devolution.elles.infrastructure.adapter.db.entity.VehicleEntity;

public class VehicleDBMapper {

    public static Vehicle toDomain(VehicleEntity entity) {
        return new Vehicle(
                entity.getId(),
                entity.getVehicleFirstRegistrationDate(),
                entity.getLicenceNumber(),
                entity.getColor(),
                entity.getNumberOfSeat(),
                entity.getNumberOfDoor(),
                entity.getCategory()
        );
    }

    public static VehicleEntity toEntity(Vehicle vehicle) {
        return new VehicleEntity(
                vehicle.id(),
                vehicle.vehicleFirstRegistrationDate(),
                vehicle.licenceNumber(),
                vehicle.color(),
                vehicle.numberOfSeat(),
                vehicle.numberOfDoor(),
                vehicle.category()
        );
    }
}
