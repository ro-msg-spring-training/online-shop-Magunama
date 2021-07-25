package ro.msg.learning.shop.model.converter;

import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.dto.LocationDTO;

public class LocationConverter implements EntityDTOConverter<Location, LocationDTO>{
    @Override
    public LocationDTO toDTO(Location entity) {
        if (entity == null) {
            return null;
        }

        return LocationDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .addressCountry(entity.getAddressCountry())
                .addressCity(entity.getAddressCity())
                .addressStreetAddress(entity.getAddressStreetAddress()).build();
    }

    @Override
    public Location toEntity(LocationDTO dto) {
        if (dto == null) {
            return null;
        }

        return Location.builder()
                .id(dto.getId())
                .name(dto.getName())
                .addressCountry(dto.getAddressCountry())
                .addressCity(dto.getAddressCity())
                .addressCounty(dto.getAddressCounty())
                .addressStreetAddress(dto.getAddressStreetAddress()).build();
    }
}
