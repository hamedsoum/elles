package devolution.elles.infrastructure.adapter.web.dto;

import devolution.elles.domain.enums.SubscriptionStatus;

public record SubscriptionResponse(
        String id,
        String productName,
        String insuredFirstName,
        String insuredLastName,
        String phoneNumber,
        String identityNumber,
        String vehicleCategory,
        String licenceNumber,
        String vehicleFirstRegistrationDate,
        SubscriptionStatus status
) {
}
