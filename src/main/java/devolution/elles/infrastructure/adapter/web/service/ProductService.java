package devolution.elles.infrastructure.adapter.web.service;

import devolution.elles.domain.model.Product;
import devolution.elles.domain.port.in.ProductUseCase;
import devolution.elles.domain.port.out.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductUseCase {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.create(product);
    }

    @Override
    public Product retrieve(String id) {
        return productRepository.retrieve(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
