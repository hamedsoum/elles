package devolution.elles.domain.port.in;

import devolution.elles.domain.enums.SubscriptionStatus;
import devolution.elles.domain.model.Subscription;

import java.util.List;

public interface SubscriptionUseCase {
    Subscription create(Subscription subscription);

    Subscription retrieve(String id);

    SubscriptionStatus status(String id);

    List<Subscription> findAll();
}
