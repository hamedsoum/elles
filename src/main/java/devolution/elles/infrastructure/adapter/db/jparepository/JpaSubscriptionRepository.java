package devolution.elles.infrastructure.adapter.db.jparepository;

import devolution.elles.infrastructure.adapter.db.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSubscriptionRepository extends JpaRepository<SubscriptionEntity, String> {
}
