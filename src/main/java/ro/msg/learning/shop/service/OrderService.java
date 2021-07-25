package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.dto.StockDTO;

import java.util.List;

public interface OrderService {
    Order placeOrder(Order order, List<StockDTO> orderStock);
}
