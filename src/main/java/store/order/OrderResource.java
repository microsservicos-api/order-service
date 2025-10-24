package store.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import store.order.OrderController;
import store.order.OrderIn;
import store.order.OrderOut;

@RestController
public class OrderResource implements OrderController {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseEntity<OrderOut> create(OrderIn in) {
        // parser OrderIn to Order
        Order order = OrderParser.to(in);

        Order saved = orderService.create(order);

        // parser Order to OrderOut and build to
        // HATEAOS standard
        return ResponseEntity
            .created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.id())
                    .toUri()
            ).body(OrderParser.to(saved));
    }

    @Override
    public ResponseEntity<OrderOut> findById(String id) {
        return ResponseEntity
            .ok(OrderParser.to(orderService.findById(id)));
    }

    @Override
    public ResponseEntity<OrderOut> findByEmailAndPassword(OrderIn in) {
        return ResponseEntity
            .ok()
            .body(OrderParser.to(
                orderService.findByEmailAndPassword(in.email(), in.password())
            ));
    }

    @Override
    public ResponseEntity<List<OrderOut>> findAll() {
        return ResponseEntity
            .ok()
            .body(OrderParser.to(orderService.findAll()));
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        return ResponseEntity
            .noContent()
            .build();
    }

    @Override
    public ResponseEntity<OrderOut> whoAmI(String idOrder) {
        final Order found = orderService.findById(idOrder);
        if (found == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(OrderParser.to(found));
    }
    
}
