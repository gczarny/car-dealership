package pl.car_dealership.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.car_dealership.api.dto.ServiceDTO;
import pl.car_dealership.domain.Service;


@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDTO map(Service service);
}
