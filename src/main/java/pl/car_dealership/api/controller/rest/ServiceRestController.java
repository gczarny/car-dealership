package pl.car_dealership.api.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.car_dealership.api.dto.CarServiceCustomerRequestDTO;
import pl.car_dealership.api.dto.CarServiceRequestDTO;
import pl.car_dealership.api.dto.CarServiceRequestsDTO;
import pl.car_dealership.api.dto.mapper.CarServiceRequestMapper;
import pl.car_dealership.business.CarServiceRequestService;
import pl.car_dealership.domain.CarServiceRequest;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ServiceRestController.API_SERVICE)
public class ServiceRestController {

    public static final String API_SERVICE = "/api/service";
    public static final String SERVICE_REQUEST = "/request";

    private final CarServiceRequestService carServiceRequestService;
    private final CarServiceRequestMapper carServiceRequestMapper;

    @PostMapping(value = SERVICE_REQUEST)
    public CarServiceRequestsDTO makeServiceRequest(
            @Valid @RequestBody CarServiceCustomerRequestDTO serviceCustomerRequestDTO
    ) {
        CarServiceRequest serviceRequest = carServiceRequestMapper.map(serviceCustomerRequestDTO);
        carServiceRequestService.makeServiceRequest(serviceRequest);

        return CarServiceRequestsDTO.builder()
                .carServiceRequests(getAvailableCarServiceRequests())
                .build();
    }

    private List<CarServiceRequestDTO> getAvailableCarServiceRequests() {
        return carServiceRequestService.availableServiceRequests().stream()
                .map(carServiceRequestMapper::map)
                .toList();
    }
}
