package devolution.elles.domain.port.out;

import devolution.elles.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    Product create(Product product);

    Product retrieve(String id);

    List<Product> findAll();
}
