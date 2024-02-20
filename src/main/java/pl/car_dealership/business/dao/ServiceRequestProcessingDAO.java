package pl.car_dealership.business.dao;

import pl.car_dealership.infrastructure.database.entity.CarServiceRequestEntity;
import pl.car_dealership.infrastructure.database.entity.ServiceMechanicEntity;
import pl.car_dealership.infrastructure.database.entity.ServicePartEntity;

public interface ServiceRequestProcessingDAO {
    void process(CarServiceRequestEntity serviceRequest, ServiceMechanicEntity serviceMechanicEntity);

    void process(CarServiceRequestEntity serviceRequest, ServiceMechanicEntity serviceMechanicEntity, ServicePartEntity servicePartEntity);
}
