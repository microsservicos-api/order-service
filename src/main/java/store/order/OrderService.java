package store.order;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import store.order.Order;

@Service
public class OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public Order create(Order order) {
        if (null == order.password()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Password is mandatory!"
            );
        }
        if (null == order.email()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Email is mandatory!"
            );
        }

        if (orderRepository.findByEmail(order.email()) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Email already have been registered!"
            );

        return orderRepository.save(
            new OrderModel(order)
        ).to();
    }

    public List<Order> findAll() {
        return StreamSupport.stream(
            orderRepository.findAll().spliterator(), false)
            .map(OrderModel::to)
            .toList();
    }

    public Order findById(String id) {
        return orderRepository.findById(id).map(OrderModel::to).orElse(null);
    }

    public void delete(String id) {
        orderRepository.delete(new OrderModel().id(id));
    }
    
}
