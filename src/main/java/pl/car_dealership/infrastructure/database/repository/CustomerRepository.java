package pl.car_dealership.infrastructure.database.repository;

import org.hibernate.Session;
import pl.car_dealership.business.dao.CustomerDAO;
import pl.car_dealership.infrastructure.database.entity.CustomerEntity;

import java.util.Objects;
import java.util.Optional;

public class CustomerRepository implements CustomerDAO {
    @Override
    public Optional<CustomerEntity> findCustomerByEmail(String email) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = "SELECT cu FROM CustomerEntity cu WHERE cu.email = :email";
            Optional<CustomerEntity> salesmanEntity = session.createQuery(query, CustomerEntity.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();
            session.getTransaction().commit();
            return salesmanEntity;
        }
    }

    @Override
    public void issueInvoice(CustomerEntity customer) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            if (Objects.isNull(customer.getCustomerId())) {
                session.persist(customer);
            }
            customer.getInvoices().stream()
                    .filter(invoice -> Objects.isNull(invoice.getInvoiceId()))
                    .forEach(invoice -> {
                        invoice.setCustomer(customer);
                        session.persist(invoice);
                    });
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveServiceRequest(CustomerEntity customer) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            customer.getCarServiceRequests().stream()
                    .filter(request -> Objects.isNull(request.getCarServiceRequestId()))
                    .forEach(session::persist);

            session.getTransaction().commit();
        }
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity entity) {
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
}
