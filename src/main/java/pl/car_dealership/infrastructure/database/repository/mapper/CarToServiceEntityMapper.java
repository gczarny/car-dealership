package pl.car_dealership.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.car_dealership.domain.CarHistory;
import pl.car_dealership.domain.CarToService;
import pl.car_dealership.domain.Part;
import pl.car_dealership.domain.Service;
import pl.car_dealership.infrastructure.database.entity.CarToServiceEntity;
import pl.car_dealership.infrastructure.database.entity.ServiceMechanicEntity;
import pl.car_dealership.infrastructure.database.entity.ServicePartEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarToServiceEntityMapper {

    @Mapping(target = "carServiceRequests", ignore = true)
    CarToService mapFromEntity(CarToServiceEntity entity);

    default CarHistory mapFromEntity(String vin, CarToServiceEntity entity) {
        return CarHistory.builder().carVin(vin).carServiceRequests(entity.getCarServiceRequests().stream()
                .map(request -> CarHistory.CarServiceRequest.builder()
                        .carServiceRequestNumber(request.getCarServiceRequestNumber())
                        .receivedDateTime(request.getReceivedDateTime())
                        .completionDateTime(request.getCompletedDateTime())
                        .customerComment(request.getCustomerComment())
                        .services(request.getServiceMechanics().stream()
                                .map(ServiceMechanicEntity::getService)
                                .map(service -> Service.builder()
                                        .serviceCode(service.getServiceCode())
                                        .description(service.getDescription())
                                        .price(service.getPrice())
                                        .build())
                                .toList())
                        .parts(request.getServiceParts().stream()
                                .map(ServicePartEntity::getPart)
                                .map(part -> Part.builder()
                                        .serialNumber(part.getSerialNumber())
                                        .description(part.getDescription())
                                        .price(part.getPrice())
                                        .build())
                                .toList())
                        .build())
                .toList())
                .build();
    }

    CarToServiceEntity mapToEntity(CarToService car);
}
