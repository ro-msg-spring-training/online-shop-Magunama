package ro.msg.learning.shop.service.strategy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.converter.OrderConverter;
import ro.msg.learning.shop.model.dto.OrderDTO;
import ro.msg.learning.shop.model.dto.StockDTO;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.OrderServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test-single.properties")
@ActiveProfiles("test")
class SingleStrategyUnitTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private OrderConverter orderConverter;


    @BeforeEach
    void setUp() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/populate");
        mvc.perform(request).andExpect(status().isOk());
    }

    @AfterEach
    void tearDown() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/depopulate");
        mvc.perform(request).andExpect(status().isOk());
    }

    private OrderDTO makeOrderRight() {
        Timestamp ts = Timestamp.valueOf("2020-09-01 09:01:15");
        LocalDateTime now = ts.toLocalDateTime();

        List<StockDTO> products = new ArrayList<>();
        products.add(new StockDTO(null, 1, null, 2));
        products.add(new StockDTO(null, 2, null, 1));

        return new OrderDTO(null, now, "Romania", "Timisoara",
                "Timis", "Str. Garii 12", products);
    }

    private OrderDTO makeOrderWrong() {
        Timestamp ts = Timestamp.valueOf("2020-09-01 09:01:15");
        LocalDateTime now = ts.toLocalDateTime();

        List<StockDTO> products = new ArrayList<>();
        products.add(new StockDTO(null, 1, null, 2));
        products.add(new StockDTO(null, 3, null, 3));

        return new OrderDTO(null, now, "Romania", "Timisoara",
                "Timis", "Str. Garii 12", products);
    }


    @Test
    void runRight(){
        OrderDTO order = makeOrderRight();

        OrderStrategy strategy = new SingleLocation(stockRepository);
        List<StockDTO> finalProducts = strategy.run(order);

        Order orderResult = orderService.placeOrder(orderConverter.toEntity(order), finalProducts);
        assertEquals(1, orderResult.getId());
        assertEquals(2, finalProducts.get(0).getLocation());
        assertEquals(2, finalProducts.get(1).getLocation());
    }

    @Test
    void runWrong(){
        OrderDTO order = makeOrderWrong();
        OrderStrategy strategy = new SingleLocation(stockRepository);

        assertThrows(SingleLocation.NoSuchLocationException.class, () -> strategy.run(order));
    }

}
