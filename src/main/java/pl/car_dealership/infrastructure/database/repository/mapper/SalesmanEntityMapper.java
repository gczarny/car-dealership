package pl.car_dealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import pl.car_dealership.domain.Salesman;
import pl.car_dealership.infrastructure.database.entity.SalesmanEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalesmanEntityMapper {

    @Mapping(target = "invoices", ignore = true)
    Salesman mapFromEntity(SalesmanEntity entity);
}
