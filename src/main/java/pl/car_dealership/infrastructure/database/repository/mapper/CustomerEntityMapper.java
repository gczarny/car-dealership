package pl.car_dealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.car_dealership.domain.Customer;
import pl.car_dealership.infrastructure.database.entity.CustomerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerEntityMapper {

    Customer mapFromEntity(CustomerEntity entity);
    CustomerEntity mapToEntity(Customer customer);
}
