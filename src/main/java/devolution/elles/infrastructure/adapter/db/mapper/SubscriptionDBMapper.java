package devolution.elles.infrastructure.adapter.db.mapper;

import devolution.elles.domain.model.Subscription;
import devolution.elles.infrastructure.adapter.db.entity.SubscriptionEntity;

public class SubscriptionDBMapper {

    public static Subscription toDomain(SubscriptionEntity entity) {
        return new Subscription(
                entity.getId(),
                entity.getProductID(),
                InsuredDBMapper.toDomain(entity.getInsured()),
                VehicleDBMapper.toDomain(entity.getVehicle()),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getUpdatedAt(),
                entity.getUpdatedBy()
        );
    }

    public static SubscriptionEntity toEntity(Subscription subscription) {
        return new SubscriptionEntity(
                subscription.id(),
                subscription.id(),
                InsuredDBMapper.toEntity(subscription.insured()),
                VehicleDBMapper.toEntity(subscription.vehicle()),
                subscription.status(),
                subscription.createdAt(),
                subscription.createdBy(),
                subscription.updatedAt(),
                subscription.updatedBy()

        );
    }


}
