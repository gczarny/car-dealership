package pl.car_dealership.api.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.car_dealership.api.dto.mapper.CarMapper;
import pl.car_dealership.api.dto.mapper.MechanicMapper;
import pl.car_dealership.api.dto.mapper.SalesmanMapper;
import pl.car_dealership.business.CarPurchaseService;
import pl.car_dealership.business.CarServiceRequestService;

@Controller
@RequiredArgsConstructor
public class SalesmanController {

    private static final String SALESMAN = "/salesman"; //endpoint

    private final CarPurchaseService carPurchaseService;
    private final CarServiceRequestService carServiceRequestService;
    private final CarMapper carMapper;
    private final SalesmanMapper salesmanMapper;
    private final MechanicMapper mechanicMapper;

    @GetMapping(value = SALESMAN)
    public String homePage(Model model) {
        var availableCars = carPurchaseService.availableCars().stream()
            .map(carMapper::map)
            .toList();
        var availableSalesmen = carPurchaseService.availableSalesman().stream()
            .map(salesmanMapper::map)
            .toList();
        var availableMechanics = carServiceRequestService.availableMechanics().stream()
            .map(mechanicMapper::map)
            .toList();

        model.addAttribute("availableCarDTOs", availableCars);
        model.addAttribute("availableSalesmenDTOs", availableSalesmen);
        model.addAttribute("availableMechanicDTOs", availableMechanics);

        return "salesman_portal";
    }
}
