package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import pl.car_dealership.business.dao.CustomerDAO;
import pl.car_dealership.domain.CarServiceRequest;
import pl.car_dealership.infrastructure.database.entity.AddressEntity;
import pl.car_dealership.infrastructure.database.entity.CustomerEntity;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;
    public void issueInvoice(CustomerEntity customer) {
        customerDAO.issueInvoice(customer);
    }

    public CustomerEntity findCustomer(String email) {

        Optional<CustomerEntity> customerByEmail = customerDAO.findCustomerByEmail(email);
        if (customerByEmail.isEmpty()) {
            throw new RuntimeException("Could not find customer by email: [%s]".formatted(email));
        }
        return customerByEmail.get();
    }

    public void saveServiceRequest(CustomerEntity customer) {
        customerDAO.saveServiceRequest(customer);
    }

    public CustomerEntity saveCustomer(CarServiceRequest.Customer customer) {
        CustomerEntity entity = CustomerEntity.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .address(AddressEntity.builder()
                        .country(customer.getAddress().getCountry())
                        .city(customer.getAddress().getCity())
                        .postalCode(customer.getAddress().getPostalCode())
                        .address(customer.getAddress().getAddress())
                        .build())
                .build();
        return customerDAO.saveCustomer(entity);
    }
}
