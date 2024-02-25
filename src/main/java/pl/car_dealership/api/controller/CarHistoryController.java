package pl.car_dealership.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.car_dealership.api.dto.CarHistoryDTO;
import pl.car_dealership.api.dto.CarToServiceDTO;
import pl.car_dealership.api.dto.mapper.CarMapper;
import pl.car_dealership.business.CarService;
import pl.car_dealership.domain.CarHistory;

import java.util.Objects;

@Controller
@AllArgsConstructor
public class CarHistoryController {

    private static final String CAR_HISTORY = "/car/history";

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping(value = CAR_HISTORY)
    public String carHistory(
        @RequestParam(value = "carVin", required = false) String carVin,
        Model model
    ) {
        var allCars = carService.findAllCarsWithHistory().stream().map(carMapper::map).toList();
        var allCarVins = allCars.stream().map(CarToServiceDTO::getVin).toList();

        model.addAttribute("allCarDTOs", allCars);
        model.addAttribute("allCarVins", allCarVins);

        if (Objects.nonNull(carVin)) {
            CarHistory carHistory = carService.findCarHistoryByVin(carVin);
            model.addAttribute("carHistoryDTO", carMapper.map(carHistory));
        } else {
            model.addAttribute("carHistoryDTO", CarHistoryDTO.buildDefault());
        }
        return "car_history";
    }
}
