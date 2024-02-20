package pl.car_dealership.infrastructure.database.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.car_dealership.business.dao.CarDAO;
import pl.car_dealership.infrastructure.database.entity.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CarRepository implements CarDAO {
    @Override
    public Optional<CarToBuyEntity> findCarToBuyByVin(String vin) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CarToBuyEntity> criteriaQuery = criteriaBuilder.createQuery(CarToBuyEntity.class);
            Root<CarToBuyEntity> root = criteriaQuery.from(CarToBuyEntity.class);
            ParameterExpression<String> param1 = criteriaBuilder.parameter(String.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("vin"), param1));
            Query<CarToBuyEntity> query = session.createQuery(criteriaQuery);
            query.setParameter(param1, vin);
            try {
                CarToBuyEntity singleResult = query.getSingleResult();
                session.getTransaction().commit();
                return Optional.of(singleResult);
            } catch (Throwable ex) {
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<CarToServiceEntity> findCarToServiceByVin(String vin) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CarToServiceEntity> criteriaQuery = criteriaBuilder.createQuery(CarToServiceEntity.class);
            Root<CarToServiceEntity> root = criteriaQuery.from(CarToServiceEntity.class);
            ParameterExpression<String> param1 = criteriaBuilder.parameter(String.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("vin"), param1));
            Query<CarToServiceEntity> query = session.createQuery(criteriaQuery);
            query.setParameter(param1, vin);
            try {
                CarToServiceEntity singleResult = query.getSingleResult();
                session.getTransaction().commit();
                return Optional.of(singleResult);
            } catch (Throwable ex) {
                return Optional.empty();
            }
        }
    }

    @Override
    public CarToServiceEntity saveCarToService(CarToServiceEntity entity) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public CarHistoryEntity findCarHistoryByVin(String vin) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CarToServiceEntity> criteriaQuery = criteriaBuilder.createQuery(CarToServiceEntity.class);
            Root<CarToServiceEntity> root = criteriaQuery.from(CarToServiceEntity.class);

            ParameterExpression<String> param1 = criteriaBuilder.parameter(String.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("vin"), param1));

            Query<CarToServiceEntity> query = session.createQuery(criteriaQuery);
            query.setParameter(param1, vin);
            CarToServiceEntity carToServiceEntity = query.getSingleResult();
            CarHistoryEntity result = CarHistoryEntity.builder()
                    .carVin(vin)
                    .serviceRequests(carToServiceEntity.getCarServiceRequestEntities().stream().map(this::mapServiceRequest).toList())
                    .build();
            session.getTransaction().commit();
            return result;
        }
    }

    private CarHistoryEntity.ServiceRequest mapServiceRequest(CarServiceRequestEntity entity) {
        return CarHistoryEntity.ServiceRequest.builder()
                .serviceRequestNumber(entity.getCarServiceRequestNumber())
                .receivedDateTime(entity.getReceivedDateTime())
                .completionDateTime(entity.getCompletedDateTime())
                .customerComment(entity.getCustomerComment())
                .services(mapService(entity.getServiceMechanics().stream().map(ServiceMechanicEntity::getService).toList()))
                .parts(mapParts(entity.getServiceParts().stream().map(ServicePartEntity::getPart).toList()))
                .build();
    }

    private List<CarHistoryEntity.Service> mapService(List<ServiceEntity> entities) {
        return entities.stream()
                .map(service -> CarHistoryEntity.Service.builder()
                        .serviceCode(service.getServiceCode())
                        .description(service.getDescription())
                        .price(service.getPrice())
                        .build()).toList();
    }

    private List<CarHistoryEntity.Part> mapParts(List<PartEntity> entities) {
        return entities.stream()
                .map(part -> CarHistoryEntity.Part.builder()
                        .serialNumber(part.getSerialNumber())
                        .description(part.getDescription())
                        .price(part.getPrice())
                        .build()).toList();
    }
}
