package devolution.elles.infrastructure.adapter.web.mapper;

import devolution.elles.domain.model.Product;
import devolution.elles.domain.model.Subscription;
import devolution.elles.infrastructure.adapter.web.dto.SubscriptionResponse;
import devolution.elles.infrastructure.adapter.web.service.ProductService;

final public class SubscriptionDtoMapper {

    public static SubscriptionResponse toSubscriptionDto(Subscription subscription, ProductService productService) {
        Product product = productService.retrieve(subscription.productID());
        return new SubscriptionResponse(
                subscription.id(),
                product.name(),
                subscription.insured().firstName(),
                subscription.insured().lastName(),
                subscription.insured().phoneNumber(),
                subscription.insured().identityNumber(),
                subscription.vehicle().category().name(),
                subscription.vehicle().licenceNumber(),
                subscription.vehicle().vehicleFirstRegistrationDate().toString(),
                subscription.status()
        );
    }
}
