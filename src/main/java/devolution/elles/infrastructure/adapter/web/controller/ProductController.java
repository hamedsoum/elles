package devolution.elles.infrastructure.adapter.web.controller;

import devolution.elles.domain.model.Product;
import devolution.elles.infrastructure.adapter.web.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    @Operation(summary = "Créer un nouveau produit")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        Product productSaved = this.productService.create(product);
        URI location = URI.create("/products/" + productSaved.id());

        return ResponseEntity
                .created(location)
                .body(productSaved);
    }

    @GetMapping()
    @Operation(summary = "Récupérer tous les produits d'assurance")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = this.productService.findAll();
        URI location = URI.create("/products/");
        return ResponseEntity
                .created(location)
                .body(products);
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "Récupérer un produit par ID")
    public Product retrieve(@PathVariable String id) {
        return this.productService.retrieve(id);
    }
}
