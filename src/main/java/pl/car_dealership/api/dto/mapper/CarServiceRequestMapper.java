package pl.car_dealership.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.car_dealership.api.dto.CarServiceCustomerRequestDTO;
import pl.car_dealership.api.dto.CarServiceMechanicProcessingUnitDTO;
import pl.car_dealership.api.dto.CarServiceRequestDTO;
import pl.car_dealership.domain.*;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CarServiceRequestMapper extends OffsetDateTimeMapper {

    default CarServiceRequest map(CarServiceCustomerRequestDTO dto) {
        if (dto.isNewCarCandidate()) {
            return CarServiceRequest.builder()
                    .customer(Customer.builder()
                            .name(dto.getCustomerName())
                            .surname(dto.getCustomerSurname())
                            .phone(dto.getCustomerPhone())
                            .email(dto.getCustomerEmail())
                            .address(Address.builder()
                                    .country(dto.getCustomerAddressCountry())
                                    .city(dto.getCustomerAddressCity())
                                    .postalCode(dto.getCustomerAddressPostalCode())
                                    .address(dto.getCustomerAddressStreet())
                                    .build())
                            .build())
                    .car(CarToService.builder()
                            .vin(dto.getCarVin())
                            .brand(dto.getCarBrand())
                            .model(dto.getCarModel())
                            .year(dto.getCarYear())
                            .build())
                    .customerComment(dto.getCustomerComment())
                    .build();

        } else {
            return CarServiceRequest.builder()
                    .customer(Customer.builder()
                            .email(dto.getExistingCustomerEmail())
                            .build())
                    .car(CarToService.builder()
                            .vin(dto.getExistingCarVin())
                            .build())
                    .customerComment(dto.getCustomerComment())
                    .build();
        }
    }


    @Mapping(source = "car.vin", target = "carVin")
    @Mapping(source = "receivedDateTime", target = "receivedDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "completedDateTime", target = "completedDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    CarServiceRequestDTO map(CarServiceRequest request);

    @Mapping(source = "mechanicComment", target = "comment")
    CarServiceProcessingRequest map(CarServiceMechanicProcessingUnitDTO dto);
}