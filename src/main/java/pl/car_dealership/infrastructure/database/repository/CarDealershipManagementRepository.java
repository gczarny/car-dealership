package pl.car_dealership.infrastructure.database.repository;

import org.hibernate.Session;
import pl.car_dealership.business.dao.management.CarDealershipManagementServiceDAO;

import java.util.List;
import java.util.Objects;

public class CarDealershipManagementRepository implements CarDealershipManagementServiceDAO {
    @Override
    public void purge() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.createMutationQuery("DELETE FROM ServiceMechanicJpaRepository ent").executeUpdate();
            session.createMutationQuery("DELETE FROM ServicePartJpaRepository ent").executeUpdate();
            session.createMutationQuery("DELETE FROM CarServiceRequestJpaRepository ent").executeUpdate();
            session.createMutationQuery("DELETE FROM InvoiceJpaRepository ent").executeUpdate();
            session.createMutationQuery("DELETE FROM MechanicJpaRepository ent").executeUpdate();
            session.createMutationQuery("DELETE FROM PartJpaRepository ent").executeUpdate();
            session.createMutationQuery("DELETE FROM ServiceJpaRepository ent").executeUpdate();
            session.createMutationQuery("DELETE FROM CarToServiceJpaRepository ent").executeUpdate();
            session.createMutationQuery("DELETE FROM CarToBuyJpaRepository ent").executeUpdate();
            session.createMutationQuery("DELETE FROM CustomerJpaRepository ent").executeUpdate();
            session.createMutationQuery("DELETE FROM AddressJpaRepository ent").executeUpdate();
            session.createMutationQuery("DELETE FROM SalesmanJpaRepository ent").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveAll(List<?> entities) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            for (var entity : entities) {
                session.persist(entity);
            }
            session.getTransaction().commit();
        }

    }
}
