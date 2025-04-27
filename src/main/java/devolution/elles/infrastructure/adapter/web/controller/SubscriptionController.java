package devolution.elles.infrastructure.adapter.web.controller;

import devolution.elles.domain.enums.SubscriptionStatus;
import devolution.elles.domain.model.Subscription;
import devolution.elles.infrastructure.adapter.web.dto.SubscriptionResponse;
import devolution.elles.infrastructure.adapter.web.mapper.SubscriptionDtoMapper;
import devolution.elles.infrastructure.adapter.web.service.ProductService;
import devolution.elles.infrastructure.adapter.web.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final ProductService productService;

    public SubscriptionController(SubscriptionService subscriptionService, ProductService productService) {

        this.subscriptionService = subscriptionService;
        this.productService = productService;
    }

    @PostMapping()
    @Operation(summary = "Créer une nouvelle souscription")
    public ResponseEntity<SubscriptionResponse> save(@RequestBody Subscription subscription) {
        Subscription subscriptionSaved = this.subscriptionService.create(subscription);
        URI location = URI.create("/subscriptions/" + subscriptionSaved.id());
        return ResponseEntity
                .created(location)
                .body(SubscriptionDtoMapper.toSubscriptionDto(subscriptionSaved, productService));
    }

    @GetMapping()
    @Operation(summary = "Récupérer toutes les souscriptions")
    public ResponseEntity<List<SubscriptionResponse>> findAll() {
        List<Subscription> subscriptions = this.subscriptionService.findAll();
        List<SubscriptionResponse> subscriptionResponses = subscriptions.stream().
                map(subscription -> SubscriptionDtoMapper.toSubscriptionDto(subscription, productService)).toList();

        URI location = URI.create("/subscriptions/");
        return ResponseEntity
                .created(location)
                .body(subscriptionResponses);
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "Récupérer une souscription par ID")
    public SubscriptionResponse retrieve(@PathVariable String id) {
        Subscription subscription = this.subscriptionService.retrieve(id);
        return SubscriptionDtoMapper.toSubscriptionDto(subscription, productService);
    }

    @GetMapping(path = "{id}/status")
    @Operation(summary = "Checker le status d'une souscription")
    public SubscriptionStatus status(@PathVariable String id) {
        return this.subscriptionService.status(id);
    }
}
