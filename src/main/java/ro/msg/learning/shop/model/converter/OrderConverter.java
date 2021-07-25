package ro.msg.learning.shop.model.converter;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.dto.OrderDTO;

@Component
public class OrderConverter implements EntityDTOConverter<Order, OrderDTO>{

    @Override
    public OrderDTO toDTO(Order entity) {
        if (entity == null) {
            return null;
        }
        return OrderDTO.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .addressCountry(entity.getAddressCountry())
                .addressCity(entity.getAddressCity())
                .addressCounty(entity.getAddressCounty())
                .addressStreetAddress(entity.getAddressStreetAddress()).build();
    }

    @Override
    public Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        return Order.builder()
//                .id(0)
                .createdAt(dto.getCreatedAt())
                .addressCountry(dto.getAddressCountry())
                .addressCity(dto.getAddressCity())
                .addressCounty(dto.getAddressCounty())
                .addressStreetAddress(dto.getAddressStreetAddress()).build();
    }
}
