package pl.car_dealership.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.car_dealership.api.dto.CarPurchaseDTO;
import pl.car_dealership.domain.CarPurchaseRequest;

@Mapper(componentModel = "spring")
public interface CarPurchaseMapper {

    CarPurchaseRequest map(final CarPurchaseDTO dto);
}
