package pl.car_dealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.car_dealership.domain.Customer;
import pl.car_dealership.domain.Invoice;
import pl.car_dealership.infrastructure.database.entity.CustomerEntity;
import pl.car_dealership.infrastructure.database.entity.InvoiceEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerEntityMapper {

    @Mapping(target = "address.customer", ignore = true)
    @Mapping(source = "invoices", target = "invoices", qualifiedByName = "mapInvoices")
    Customer mapFromEntity(CustomerEntity entity);

    @Named("mapInvoices")
    @SuppressWarnings("unused")
    default Set<Invoice> mapInvoices(Set<InvoiceEntity> invoiceEntities) {
        return invoiceEntities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }

    @Mapping(target = "car", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "salesman", ignore = true)
    Invoice mapFromEntity(InvoiceEntity entity);

    @Mapping(target = "carServiceRequests", ignore = true)
    CustomerEntity mapToEntity(Customer customer);
}
