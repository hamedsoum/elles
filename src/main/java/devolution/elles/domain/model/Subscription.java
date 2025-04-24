package devolution.elles.domain.model;

import devolution.elles.domain.enums.SubscriptionStatus;

import java.time.Instant;
import java.util.UUID;

public record Subscription(
        String id,
        String productID,
        Insured insured,
        Vehicle vehicle,
        SubscriptionStatus status,
        Instant createdAt,
        UUID createdBy,
        Instant updatedAt,
        UUID updatedBy
) {
}
