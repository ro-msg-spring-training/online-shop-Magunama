package ro.msg.learning.shop.service.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.msg.learning.shop.model.dto.OrderDTO;
import ro.msg.learning.shop.model.dto.StockDTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test-single.properties")
@ActiveProfiles("test")
class SingleLocationStrategyIT {

    @Autowired
    private MockMvc mvc;

    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
    void createOrder_orderNotCreated_status404() throws Exception {
        OrderDTO order = this.makeOrderWrong();
        String orderStr = asJsonString(order);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/orders")
                .content(orderStr).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(status().isNotFound());
    }

    @Test
    void createOrder_orderCreated_status200() throws Exception {
        OrderDTO order = this.makeOrderRight();
        String orderStr = asJsonString(order);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/orders")
                .content(orderStr).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(status().isCreated());

    }
}