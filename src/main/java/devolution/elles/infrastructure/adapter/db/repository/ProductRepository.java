package devolution.elles.infrastructure.adapter.db.repository;

import devolution.elles.domain.model.Product;
import devolution.elles.infrastructure.adapter.db.entity.ProductEntity;
import devolution.elles.infrastructure.adapter.db.jparepository.JpaProductRepository;
import devolution.elles.infrastructure.adapter.db.mapper.ProductDBMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements devolution.elles.domain.port.out.ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepository(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Product create(Product product) {
        ProductEntity entity = ProductDBMapper.toEntity(product);
        ProductEntity saved = this.jpaProductRepository.save(entity);
        return ProductDBMapper.toDomain(saved);
    }

    @Override
    public Product retrieve(String id) {
        Optional<ProductEntity> product = this.jpaProductRepository.findById(id);
        if (product.isPresent()) return ProductDBMapper.toDomain(product.get());
        else throw new RuntimeException("product not found for id " + id);
    }

    @Override
    public List<Product> findAll() {
        return this.jpaProductRepository.findAll()
                .stream().map(ProductDBMapper::toDomain)
                .collect(Collectors.toList());
    }
}
