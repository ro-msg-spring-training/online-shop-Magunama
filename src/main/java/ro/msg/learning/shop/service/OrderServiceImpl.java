package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.config.OrderStrategyConfig;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.model.dto.StockDTO;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.strategy.MostAbundant;
import ro.msg.learning.shop.service.strategy.OrderStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocation;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderStrategyConfig strategyConfig;
    private LocationRepository locationRepository;
    private StockRepository stockRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    public OrderStrategy selectStrategy(){
        OrderStrategyConfig.StratType configStrategy = strategyConfig.getStrategy();
        if (configStrategy == OrderStrategyConfig.StratType.SINGLE_LOCATION) {
            return new SingleLocation(stockRepository);
        }
        return new MostAbundant(stockRepository);
    }

    private void updateStock(List<StockDTO> orderStock) {
        for (StockDTO stockDTO: orderStock) {

            // todo: optimize search
            Optional<Stock> opStock = this.stockRepository.findStockById(stockDTO.getId());
            Stock stock = opStock.get();
            int diff = stock.getQuantity() - stockDTO.getQuantity();

            // if no stock left remove from db
            if (diff == 0) {
                this.stockRepository.deleteStockById(stockDTO.getId());
            } else {
                stock.setQuantity(diff);
                this.stockRepository.save(stock);
            }

        }
    }

    private void createOrderDetails(List<StockDTO> orderStock, Order newOrder) {
        for (StockDTO stockDTO: orderStock) {
            OrderDetail.OrderDetailId orderDetailId = new OrderDetail.OrderDetailId(newOrder,
                    stockDTO.getId().getProduct());
            OrderDetail orderDetail = new OrderDetail(orderDetailId, stockDTO.getQuantity());
            this.orderDetailRepository.save(orderDetail);
        }
    }

    public Location getPrimaryLocation(List<StockDTO> orderStock) {
        int locationId = orderStock.get(0).getLocation();
        return this.locationRepository.findById(locationId).get();
    }

    @Override
    public Order placeOrder(Order order, List<StockDTO> orderStock) {
        Order newOrder = this.orderRepository.save(order);

        // create and save new order detail entity
        this.createOrderDetails(orderStock, newOrder);

        // modify stock
        this.updateStock(orderStock);

        return newOrder;
    }
}
