package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.math.BigDecimal;

@Service @AllArgsConstructor
public class PopulationServiceImpl implements PopulationService {

    private final LocationRepository locationRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    @Override
    public void populate() {

        // dummy data
        Location l1 = new Location(1, "Timisoara Dept", "Romania", "Timisoara",
                "Timis", "Str. Garii 12");
        Location l2 = new Location(2, "Cluj-Napoca Dept", "Romania", "Cluj-Napoca",
                "Cluj", "Str. Garii 25");
        this.locationRepository.save(l1);
        this.locationRepository.save(l2);

        Product p1 = new Product(1, "LG G9", "The promised neverland", BigDecimal.valueOf(380),
                0.17, null, null, null);
        Product p2 = new Product(2, "Samsung TV MNO110MS1A", "Good for table tennis", BigDecimal.valueOf(150000),
                85.0, null, null, null);
        this.productRepository.save(p1);
        this.productRepository.save(p2);

        Stock s1 = new Stock(new Stock.StockId(p1, l1), 1);
        Stock s2 = new Stock(new Stock.StockId(p2, l2), 2);
        Stock s3 = new Stock(new Stock.StockId(p1, l2), 3);
        this.stockRepository.save(s1);
        this.stockRepository.save(s2);
        this.stockRepository.save(s3);

    }

    @Override
    public void depopulate() {
        this.stockRepository.deleteAll();
        // todo: fix Referential integrity constraint violation
//        this.productRepository.deleteAll();
//        this.locationRepository.deleteAll();
    }
}
