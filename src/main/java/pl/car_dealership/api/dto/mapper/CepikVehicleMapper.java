package pl.car_dealership.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.car_dealership.api.dto.CarServiceCustomerRequestDTO;
import pl.car_dealership.api.dto.CarServiceMechanicProcessingUnitDTO;
import pl.car_dealership.api.dto.CarServiceRequestDTO;
import pl.car_dealership.api.dto.CepikVehicleDTO;
import pl.car_dealership.domain.*;

@Mapper(componentModel = "spring")
public interface CepikVehicleMapper extends OffsetDateTimeMapper {

    CepikVehicleDTO map(CepikVehicle cepikVehicle);
}
