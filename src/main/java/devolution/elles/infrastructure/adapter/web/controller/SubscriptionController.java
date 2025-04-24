package devolution.elles.infrastructure.adapter.web.controller;

import devolution.elles.domain.enums.SubscriptionStatus;
import devolution.elles.domain.model.Subscription;
import devolution.elles.infrastructure.adapter.web.service.SubscriptionService;
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

    public SubscriptionController(SubscriptionService subscriptionService) {

        this.subscriptionService = subscriptionService;
    }

    @PostMapping()
    public ResponseEntity<Subscription> save(@RequestBody Subscription subscription) {
        Subscription subscriptionSaved = this.subscriptionService.create(subscription);
        URI location = URI.create("/subscriptions/" + subscriptionSaved.id());
        System.out.println("");
        return ResponseEntity
                .created(location)
                .body(subscriptionSaved);
    }

    @GetMapping()
    public ResponseEntity<List<Subscription>> findAll() {
        List<Subscription> subscriptions = this.subscriptionService.findAll();
        URI location = URI.create("/subscriptions/");
        return ResponseEntity
                .created(location)
                .body(subscriptions);
    }

    @GetMapping(path = "{id}")
    public Subscription retrieve(@PathVariable String id) {
        return this.subscriptionService.retrieve(id);
    }

    @GetMapping(path = "{id}/status")
    public SubscriptionStatus status(@PathVariable String id) {
        return this.subscriptionService.status(id);
    }
}
