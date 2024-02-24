package pl.car_dealership.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.car_dealership.api.dto.SalesmanDTO;
import pl.car_dealership.domain.Salesman;

@Mapper(componentModel = "spring")
public interface SalesmanMapper {

    SalesmanDTO map(final Salesman car);
}
