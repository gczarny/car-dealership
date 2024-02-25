package pl.car_dealership.api.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.car_dealership.api.dto.CarServiceCustomerRequestDTO;
import pl.car_dealership.api.dto.mapper.CarServiceRequestMapper;
import pl.car_dealership.business.CarServiceRequestService;
import pl.car_dealership.domain.CarServiceRequest;

import java.util.Map;

@Controller
@AllArgsConstructor
public class ServiceController {

    private static final String SERVICE_NEW = "/service/new";
    private static final String SERVICE_REQUEST = "/service/request";

    private final CarServiceRequestService carServiceRequestService;
    private final CarServiceRequestMapper carServiceRequestMapper;


    @GetMapping(value = SERVICE_NEW)
    public ModelAndView carServicePage() {
        Map<String, ?> model = Map.of(
                "carServiceRequestDTO", CarServiceCustomerRequestDTO.buildDefault()
        );

        return new ModelAndView("car_service_request", model);
    }

    @PostMapping(value = SERVICE_REQUEST)
    public String makeServiceRequest(
            @ModelAttribute("carServiceRequestDTO") CarServiceCustomerRequestDTO serviceCustomerRequestDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "error";
        }
        CarServiceRequest serviceRequest = carServiceRequestMapper.map(serviceCustomerRequestDTO);
        carServiceRequestService.makeServiceRequest(serviceRequest);

        return "car_service_request_done";
    }
}
