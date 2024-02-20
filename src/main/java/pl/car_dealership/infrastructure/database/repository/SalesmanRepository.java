package pl.car_dealership.infrastructure.database.repository;

import org.hibernate.Session;
import pl.car_dealership.business.dao.SalesmanDAO;
import pl.car_dealership.infrastructure.database.entity.SalesmanEntity;

import java.util.Objects;
import java.util.Optional;

public class SalesmanRepository implements SalesmanDAO {
    @Override
    public Optional<SalesmanEntity> findByPesel(String pesel) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT se FROM SalesmanJpaRepository se WHERE se.pesel = :pesel";
            Optional<SalesmanEntity> salesmanEntity = session.createQuery(query, SalesmanEntity.class)
                    .setParameter("pesel", pesel)
                    .uniqueResultOptional();
            session.getTransaction().commit();
            return salesmanEntity;
        }
    }
}
