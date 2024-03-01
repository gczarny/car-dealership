package pl.car_dealership.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.car_dealership.api.dto.CarHistoryDTO;
import pl.car_dealership.api.dto.CarToBuyDTO;
import pl.car_dealership.api.dto.CarToServiceDTO;
import pl.car_dealership.api.dto.InvoiceDTO;
import pl.car_dealership.domain.CarHistory;
import pl.car_dealership.domain.CarToBuy;
import pl.car_dealership.domain.CarToService;
import pl.car_dealership.domain.Invoice;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper extends OffsetDateTimeMapper {

    InvoiceDTO map(Invoice invoice);
}
