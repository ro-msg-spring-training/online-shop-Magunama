package ro.msg.learning.shop.service;

import ro.msg.learning.shop.model.dto.OrderDTO;

public interface OrderService {
    OrderDTO placeOrder(OrderDTO orderInfo);
}
