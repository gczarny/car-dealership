package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.car_dealership.domain.CarHistory;
import pl.car_dealership.domain.CarToBuy;
import pl.car_dealership.domain.CarToService;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class CarService {

    private final CarDAO carDAO;
    public CarToBuy findCarToBuy(String vin) {
        Optional<CarToBuy> carToBuyByVin = carDAO.findCarToBuyByVin(vin);
        if (carToBuyByVin.isEmpty()) {
            throw new RuntimeException("Could not find car by vin: [%s]".formatted(vin));
        }
        return carToBuyByVin.get();
    }

    public Optional<CarToService> findCarToService(String vin) {
        return carDAO.findCarToServiceByVin(vin);
    }

    public CarToService saveCarToService(CarToBuy carToBuy) {
        CarToService entity = CarToService.builder()
                .vin(carToBuy.getVin())
                .brand(carToBuy.getBrand())
                .model(carToBuy.getModel())
                .year(carToBuy.getYear())
                .build();
        return carDAO.saveCarToService(entity);
    }

    public CarToService saveCarToService(CarToService car) {
        CarToService entity = CarToService.builder()
                .vin(car.getVin())
                .brand(car.getBrand())
                .model(car.getModel())
                .year(car.getYear())
                .build();
        return carDAO.saveCarToService(entity);
    }

    public void printCarHistory(String vin) {
        CarHistory carHistoryByVin = carDAO.findCarHistoryByVin(vin);
        log.info("### CAR HISTORY FOR VIN: [{}] ###",vin);
        carHistoryByVin.getCarCarServiceRequests().forEach(this::printServiceRequest);
    }

    private void printServiceRequest(CarHistory.CarServiceRequest serviceRequest) {
        log.info("##### SERVICE REQUEST: [{}] #####", serviceRequest);
        serviceRequest.getServices().forEach(service -> log.info("### SERVICE: [{}] ###", service));
        serviceRequest.getParts().forEach(part -> log.info("### PART: [{}] ###", part));
    }
}
