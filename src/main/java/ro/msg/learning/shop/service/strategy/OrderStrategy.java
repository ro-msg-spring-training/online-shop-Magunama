package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.model.dto.OrderDTO;
import ro.msg.learning.shop.model.dto.StockDTO;

import java.util.List;

public interface OrderStrategy {
    List<StockDTO> run(OrderDTO orderInfo);
}
