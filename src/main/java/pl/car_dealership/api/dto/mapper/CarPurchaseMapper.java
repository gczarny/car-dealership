package pl.car_dealership.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.car_dealership.api.dto.CarPurchaseDTO;
import pl.car_dealership.api.dto.CarToBuyDTO;
import pl.car_dealership.domain.CarPurchaseRequest;
import pl.car_dealership.domain.CarToBuy;

@Mapper(componentModel = "spring")
public interface CarPurchaseMapper {

    CarPurchaseRequest map(final CarPurchaseDTO dto);

    CarToBuyDTO map(CarToBuy a);
}
