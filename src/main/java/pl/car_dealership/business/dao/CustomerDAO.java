package pl.car_dealership.business.dao;

import pl.car_dealership.infrastructure.database.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerDAO {
    Optional<CustomerEntity> findCustomerByEmail(String email);

    void issueInvoice(CustomerEntity customer);

    void saveServiceRequest(CustomerEntity customer);

    CustomerEntity saveCustomer(CustomerEntity entity);
}
