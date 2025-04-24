package devolution.elles.domain.port.in;

import devolution.elles.domain.model.Product;

import java.util.List;

public interface ProductUseCase {
    Product create(Product product);

    Product retrieve(String id);

    List<Product> findAll();
}
