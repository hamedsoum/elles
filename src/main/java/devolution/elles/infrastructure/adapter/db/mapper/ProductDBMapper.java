package devolution.elles.infrastructure.adapter.db.mapper;

import devolution.elles.domain.model.Product;
import devolution.elles.infrastructure.adapter.db.entity.ProductEntity;

import java.util.Arrays;

public class ProductDBMapper {

    public static Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                Arrays.asList(entity.getWarranties().split(",")),
                Arrays.asList(entity.getEligibleVehicles().split(","))
        );
    }

    public static ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.id(),
                product.name(),
                String.join(",", product.warranties()),
                String.join(",", product.eligibleVehicles())
        );
    }

}
