package pl.car_dealership.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.car_dealership.api.dto.CarHistoryDTO;
import pl.car_dealership.api.dto.CarToBuyDTO;
import pl.car_dealership.api.dto.CarToServiceDTO;
import pl.car_dealership.domain.CarHistory;
import pl.car_dealership.domain.CarToBuy;
import pl.car_dealership.domain.CarToService;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper extends OffsetDateTimeMapper {

    CarToBuyDTO map(final CarToBuy car);

    CarToServiceDTO map(final CarToService car);

    @Mapping(source = "carServiceRequests", target = "carServiceRequests", qualifiedByName = "mapServiceRequests")
    CarHistoryDTO map(CarHistory carHistory);

    @SuppressWarnings("unused")
    @Named("mapServiceRequests")
    default List<CarHistoryDTO.ServiceRequestDTO> mapServiceRequests(
        List<CarHistory.CarServiceRequest> requests
    ) {
        return requests.stream().map(this::mapServiceRequest).toList();
    }

    @Mapping(source = "receivedDateTime", target = "receivedDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "completedDateTime", target = "completedDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    CarHistoryDTO.ServiceRequestDTO mapServiceRequest(CarHistory.CarServiceRequest carServiceRequest);
}
