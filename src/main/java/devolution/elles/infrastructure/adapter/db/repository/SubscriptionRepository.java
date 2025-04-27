package devolution.elles.infrastructure.adapter.db.repository;

import devolution.elles.domain.enums.SubscriptionStatus;
import devolution.elles.domain.model.Product;
import devolution.elles.domain.model.Subscription;
import devolution.elles.domain.port.out.ProductRepository;
import devolution.elles.infrastructure.adapter.db.entity.SubscriptionEntity;
import devolution.elles.infrastructure.adapter.db.jparepository.JpaSubscriptionRepository;
import devolution.elles.infrastructure.adapter.db.mapper.SubscriptionDBMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SubscriptionRepository implements devolution.elles.domain.port.out.SubscriptionRepository {

    private final JpaSubscriptionRepository jpaSubscriptionRepository;
    private final ProductRepository productRepository;

    public SubscriptionRepository(JpaSubscriptionRepository jpaSubscriptionRepository, ProductRepository productRepository) {
        this.jpaSubscriptionRepository = jpaSubscriptionRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Subscription create(Subscription subscription) {
        Product product = productRepository.retrieve(subscription.productID());
        if (product == null) throw new RuntimeException("Subscription not found for id " + subscription.productID());
        SubscriptionEntity entity = SubscriptionDBMapper.toEntity(subscription);
        entity.setProductID(product.id());
        SubscriptionEntity saved = jpaSubscriptionRepository.save(entity);
        return SubscriptionDBMapper.toDomain(saved);
    }

    @Override
    public Subscription retrieve(String id) {
        Optional<SubscriptionEntity> entity = this.jpaSubscriptionRepository.findById(id);
        if (entity.isPresent()) return SubscriptionDBMapper.toDomain(entity.get());
        else throw new RuntimeException("Subscription not found for id " + id);
    }

    @Override
    public SubscriptionStatus status(String id) {
        Subscription subscription = this.retrieve(id);
        return subscription.status();
    }

    @Override
    public List<Subscription> findAll() {
        return this.jpaSubscriptionRepository.findAll()
                .stream().map(SubscriptionDBMapper::toDomain)
                .collect(Collectors.toList());
    }
}
