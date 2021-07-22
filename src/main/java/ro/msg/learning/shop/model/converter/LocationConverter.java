package ro.msg.learning.shop.model.converter;

import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.dto.LocationDTO;

public class LocationConverter implements EntityDTOConverter<Location, LocationDTO>{
    @Override
    public LocationDTO toDTO(Location entity) {
        return new LocationDTO(entity.getId(), entity.getName(), entity.getAddressCountry(), entity.getAddressCity(),
                entity.getAddressCounty(), entity.getAddressStreetAddress());

    }

    @Override
    public Location toEntity(LocationDTO dto) {
        return new Location(dto.getId(), dto.getName(), dto.getAddressCountry(), dto.getAddressCity(),
                dto.getAddressCounty(), dto.getAddressStreetAddress());
    }
}
