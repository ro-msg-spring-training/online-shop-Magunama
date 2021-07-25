package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.converter.OrderConverter;
import ro.msg.learning.shop.model.dto.OrderDTO;
import ro.msg.learning.shop.model.dto.StockDTO;
import ro.msg.learning.shop.service.OrderServiceImpl;
import ro.msg.learning.shop.service.strategy.OrderStrategy;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderServiceImpl service;
    private final OrderConverter orderConverter;

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderInfo) {
        OrderStrategy strategy = this.service.selectStrategy();
        List<StockDTO> finalProducts = strategy.run(orderInfo);

        // create and save new order entity
        Order order = orderConverter.toEntity(orderInfo);
        Location primaryLocation = this.service.getPrimaryLocation(finalProducts);
        order.setShippedFrom(primaryLocation);

        // process order
        Order newOrder = this.service.placeOrder(order, finalProducts);

        // prepare order dto for return
        OrderDTO orderDto = orderConverter.toDTO(newOrder);
        orderDto.setProducts(finalProducts);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }
}
