package ro.msg.learning.shop.model.converter;

public interface EntityDTOConverter <E, D> {
    D toDTO (E entity);
    E toEntity (D dto);
}
