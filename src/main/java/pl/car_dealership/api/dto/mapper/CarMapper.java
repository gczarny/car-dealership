package pl.car_dealership.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.car_dealership.api.dto.CarToBuyDTO;
import pl.car_dealership.domain.CarToBuy;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarToBuyDTO map(final CarToBuy car);
}
