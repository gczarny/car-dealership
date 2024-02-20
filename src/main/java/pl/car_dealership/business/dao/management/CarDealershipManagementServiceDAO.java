package pl.car_dealership.business.dao.management;


import java.util.List;

public interface CarDealershipManagementServiceDAO {

    void purge();

    void saveAll(List<?> entities);
}
