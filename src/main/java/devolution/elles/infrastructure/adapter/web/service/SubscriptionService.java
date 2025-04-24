package devolution.elles.infrastructure.adapter.web.service;

import devolution.elles.domain.enums.SubscriptionStatus;
import devolution.elles.domain.model.Subscription;
import devolution.elles.domain.port.in.SubscriptionUseCase;
import devolution.elles.domain.port.out.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService implements SubscriptionUseCase {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }


    @Override
    public Subscription create(Subscription subscription) {
        return subscriptionRepository.create(subscription);
    }

    @Override
    public Subscription retrieve(String id) {
        return subscriptionRepository.retrieve(id);
    }

    @Override
    public SubscriptionStatus status(String id) {
        return subscriptionRepository.status(id);
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }
}
