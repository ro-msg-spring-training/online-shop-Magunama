package ro.msg.learning.shop.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "order")
public class OrderStrategyConfig {
    private StratType strategy;

    public void setStrategy(String strategy) {
        this.strategy = StratType.valueOf(strategy);
    }

    public enum StratType {SINGLE_LOCATION, MOST_ABUNDANT}
}
