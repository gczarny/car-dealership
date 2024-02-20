package pl.car_dealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import pl.car_dealership.domain.ServicePart;
import pl.car_dealership.infrastructure.database.entity.ServicePartEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServicePartEntityMapper {

    ServicePartEntity mapToEntity(ServicePart servicePart);
}
