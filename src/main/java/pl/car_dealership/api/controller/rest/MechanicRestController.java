package pl.car_dealership.api.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.car_dealership.api.dto.CarServiceMechanicProcessingUnitDTO;
import pl.car_dealership.api.dto.CarServiceRequestDTO;
import pl.car_dealership.api.dto.CarServiceRequestsDTO;
import pl.car_dealership.api.dto.mapper.CarServiceRequestMapper;
import pl.car_dealership.business.CarServiceProcessingService;
import pl.car_dealership.business.CarServiceRequestService;
import pl.car_dealership.domain.CarServiceProcessingRequest;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(MechanicRestController.API_MECHANIC)
public class MechanicRestController {

    public static final String API_MECHANIC = "/api/mechanic";
    public static final String AVAILABLE_SERVICE_REQUESTS = "/availableServiceRequests";
    public static final String MECHANIC_WORK_UNIT = "/workkUnit";

    private final CarServiceRequestService carServiceRequestService;
    private final CarServiceProcessingService carServiceProcessingService;
    private final CarServiceRequestMapper carServiceRequestMapper;

    @GetMapping(value = AVAILABLE_SERVICE_REQUESTS)
    public CarServiceRequestsDTO availableSericeRequests() {
        return getCarServiceRequestsDTO();
    }

    @PostMapping(value = MECHANIC_WORK_UNIT)
    public CarServiceRequestsDTO mechanicPerformWorkUnit(
            @Valid @RequestBody CarServiceMechanicProcessingUnitDTO processingUnitDTO
    ) {
        CarServiceProcessingRequest request = carServiceRequestMapper.map(processingUnitDTO);
        carServiceProcessingService.process(request);
        return getCarServiceRequestsDTO();
    }

    private CarServiceRequestsDTO getCarServiceRequestsDTO() {
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
