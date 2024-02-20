package pl.car_dealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import pl.car_dealership.domain.Service;
import pl.car_dealership.infrastructure.database.entity.ServiceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceEntityMapper {

    @Mapping(target = "serviceMechanics", ignore = true)
    Service mapFromEntity(ServiceEntity entity);
}
