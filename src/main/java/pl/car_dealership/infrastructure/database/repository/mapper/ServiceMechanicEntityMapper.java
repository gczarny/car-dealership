package pl.car_dealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import pl.car_dealership.domain.ServiceMechanic;
import pl.car_dealership.infrastructure.database.entity.ServiceMechanicEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMechanicEntityMapper {
    ServiceMechanicEntity mapToEntity(ServiceMechanic serviceMechanic);
}
