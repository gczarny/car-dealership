package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.ServiceRequestProcessingDAO;
import pl.car_dealership.domain.CarServiceRequest;
import pl.car_dealership.domain.ServiceMechanic;
import pl.car_dealership.domain.ServicePart;
import pl.car_dealership.infrastructure.database.entity.CarServiceRequestEntity;
import pl.car_dealership.infrastructure.database.entity.PartEntity;
import pl.car_dealership.infrastructure.database.entity.ServiceMechanicEntity;
import pl.car_dealership.infrastructure.database.entity.ServicePartEntity;
import pl.car_dealership.infrastructure.database.repository.jpa.CarServiceRequestJpaRepository;
import pl.car_dealership.infrastructure.database.repository.jpa.PartJpaRepository;
import pl.car_dealership.infrastructure.database.repository.jpa.ServiceMechanicJpaRepository;
import pl.car_dealership.infrastructure.database.repository.jpa.ServicePartJpaRepository;
import pl.car_dealership.infrastructure.database.repository.mapper.ServiceMechanicEntityMapper;
import pl.car_dealership.infrastructure.database.repository.mapper.ServicePartEntityMapper;

import java.util.Objects;


@Repository
@AllArgsConstructor
public class ServiceRequestProcessingRepository implements ServiceRequestProcessingDAO {

    private final ServiceMechanicJpaRepository serviceMechanicJpaRepository;
    private final CarServiceRequestJpaRepository carServiceRequestJpaRepository;
    private final PartJpaRepository partJpaRepository;
    private final ServicePartJpaRepository servicePartJpaRepository;
    private final ServiceMechanicEntityMapper serviceMechanicEntityMapper;
    private final ServicePartEntityMapper servicePartEntityMapper;

    @Override
    public void process(CarServiceRequest serviceRequest, ServiceMechanic serviceMechanic) {
        ServiceMechanicEntity serviceMechanicEntity = serviceMechanicEntityMapper.mapToEntity(serviceMechanic);
        serviceMechanicJpaRepository.saveAndFlush(serviceMechanicEntity);
        if (Objects.nonNull(serviceRequest.getCompletedDateTime())) {
            CarServiceRequestEntity carServiceRequestEntity = carServiceRequestJpaRepository
                    .findById(serviceRequest.getCarServiceRequestId())
                    .orElseThrow();
            carServiceRequestEntity.setCompletedDateTime(serviceRequest.getCompletedDateTime());
            carServiceRequestJpaRepository.saveAndFlush(carServiceRequestEntity);
        }
    }

    @Override
    public void process(
            CarServiceRequest serviceRequest,
            ServiceMechanic serviceMechanic,
            ServicePart servicePart)
    {
        PartEntity partEntity = partJpaRepository.findById(servicePart.getPart().getPartId()).orElseThrow();
        ServicePartEntity servicePartEntity = servicePartEntityMapper.mapToEntity(servicePart);
        servicePartEntity.setPart(partEntity);
        servicePartJpaRepository.saveAndFlush(servicePartEntity);
        process(serviceRequest, serviceMechanic);
    }
}
