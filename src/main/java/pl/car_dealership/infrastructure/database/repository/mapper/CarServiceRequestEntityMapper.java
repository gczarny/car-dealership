package pl.car_dealership.infrastructure.database.repository.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.car_dealership.domain.*;
import pl.car_dealership.infrastructure.database.entity.CarServiceRequestEntity;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarServiceRequestEntityMapper {

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "car", ignore = true)
    @Mapping(target = "serviceMechanics", ignore = true)
    @Mapping(target = "serviceParts", ignore = true)
    CarServiceRequest mapFromEntity(CarServiceRequestEntity entity);

    @Mapping(target = "customer.address", ignore = true)
    @Mapping(target = "customer.carServiceRequests", ignore = true)
    CarServiceRequestEntity mapToEntity(CarServiceRequest request);
}
