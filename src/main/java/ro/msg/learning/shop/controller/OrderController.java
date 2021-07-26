package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.converter.OrderConverter;
import ro.msg.learning.shop.model.dto.OrderDTO;
import ro.msg.learning.shop.model.dto.StockDTO;
import ro.msg.learning.shop.service.EmailServiceImpl;
import ro.msg.learning.shop.service.OrderServiceImpl;
import ro.msg.learning.shop.service.strategy.OrderStrategy;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderServiceImpl orderService;
    private final OrderConverter orderConverter;
    private final EmailServiceImpl emailService;

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderInfo) {
        OrderStrategy strategy = this.orderService.selectStrategy();
        List<StockDTO> finalProducts = strategy.run(orderInfo);

        // create and save new order entity
        Order order = orderConverter.toEntity(orderInfo);
        Location primaryLocation = this.orderService.getPrimaryLocation(finalProducts);
        order.setShippedFrom(primaryLocation);

        // process order
        Order newOrder = this.orderService.placeOrder(order, finalProducts);

        // prepare order dto for return
        OrderDTO orderDto = orderConverter.toDTO(newOrder);
        orderDto.setProducts(finalProducts);

        // send confirmation mail
        Customer customer = Customer.builder()
                .firstName("FirstName")
                .lastName("LastName")
                .emailAddress("target@mail.com").build();
        this.emailService.sendMail(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }
}
