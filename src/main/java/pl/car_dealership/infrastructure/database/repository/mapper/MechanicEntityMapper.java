package pl.car_dealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import pl.car_dealership.domain.Mechanic;
import pl.car_dealership.infrastructure.database.entity.MechanicEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MechanicEntityMapper {

    @Mapping(target = "serviceMechanics", ignore = true)
    Mechanic mapFromEntity(MechanicEntity entity);
}
