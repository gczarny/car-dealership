package pl.car_dealership.business.dao;


import pl.car_dealership.domain.CarServiceRequest;
import pl.car_dealership.domain.ServiceMechanic;
import pl.car_dealership.domain.ServicePart;

public interface ServiceRequestProcessingDAO {
    void process(CarServiceRequest serviceRequest, ServiceMechanic serviceMechanic);

    void process(CarServiceRequest serviceRequest, ServiceMechanic serviceMechanic, ServicePart servicePart);
}
