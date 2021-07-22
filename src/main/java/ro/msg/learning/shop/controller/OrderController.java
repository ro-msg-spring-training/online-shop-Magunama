package ro.msg.learning.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.model.dto.OrderDTO;
import ro.msg.learning.shop.service.OrderServiceImpl;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderServiceImpl service;

    OrderController(OrderServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderInfo) {
        return ResponseEntity.ok(this.service.placeOrder(orderInfo));
    }
}
