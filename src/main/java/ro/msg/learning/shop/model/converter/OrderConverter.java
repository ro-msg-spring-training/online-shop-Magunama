package ro.msg.learning.shop.model.converter;

import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.dto.OrderDTO;

public class OrderConverter implements EntityDTOConverter<Order, OrderDTO>{

    @Override
    public OrderDTO toDTO(Order entity) {
        return new OrderDTO(entity.getId(), entity.getCreatedAt(), entity.getAddressCountry(), entity.getAddressCity(),
                entity.getAddressCounty(), entity.getAddressStreetAddress(), null);

    }

    @Override
    public Order toEntity(OrderDTO dto) {
        return new Order(dto.getId(), null, null, dto.getCreatedAt(), dto.getAddressCountry(), dto.getAddressCity(),
                dto.getAddressCounty(), dto.getAddressStreetAddress());
    }
}
