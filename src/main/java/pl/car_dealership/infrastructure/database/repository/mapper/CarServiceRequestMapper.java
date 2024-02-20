package pl.car_dealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.car_dealership.domain.CarServiceRequest;
import pl.car_dealership.infrastructure.database.entity.CarServiceRequestEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarServiceRequestMapper {


    CarServiceRequestEntity mapToEntity(CarServiceRequest carServiceRequest);
}
