package devolution.elles.infrastructure.adapter.db.jparepository;

import devolution.elles.infrastructure.adapter.db.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, String> {

}
