package pl.car_dealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.car_dealership.domain.CarToBuy;
import pl.car_dealership.infrastructure.database.entity.CarToBuyEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarToBuyEntityMapper {

    @Mapping(target = "invoice", ignore = true) //because of LAZY loading
    CarToBuy mapFromEntity(CarToBuyEntity entity);
}
