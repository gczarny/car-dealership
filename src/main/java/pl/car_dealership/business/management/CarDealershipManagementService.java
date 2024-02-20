package pl.car_dealership.business.management;

import lombok.AllArgsConstructor;
import pl.car_dealership.business.dao.management.CarDealershipManagementServiceDAO;

import java.util.List;

@AllArgsConstructor
public class CarDealershipManagementService{

    private final CarDealershipManagementServiceDAO carDealershipManagementServiceDAO;
    private final FileDataPreparationService fileDataPreparationService;

    public void purge() {
        carDealershipManagementServiceDAO.purge();
    }

    public void init() {
        List<?> entities = fileDataPreparationService.prepareInitData();
        carDealershipManagementServiceDAO.saveAll(entities);
    }
}
