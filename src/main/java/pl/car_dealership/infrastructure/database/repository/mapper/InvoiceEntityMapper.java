package pl.car_dealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import pl.car_dealership.domain.Invoice;
import pl.car_dealership.infrastructure.database.entity.InvoiceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceEntityMapper {


    InvoiceEntity mapToEntity(Invoice invoice);
}
