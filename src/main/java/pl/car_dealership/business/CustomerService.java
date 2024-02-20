package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import pl.car_dealership.business.dao.CustomerDAO;
import pl.car_dealership.domain.Address;
import pl.car_dealership.domain.Customer;

import java.util.Optional;

@AllArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;
    public void issueInvoice(Customer customer) {
        customerDAO.issueInvoice(customer);
    }

    public Customer findCustomer(String email) {

        Optional<Customer> customerByEmail = customerDAO.findCustomerByEmail(email);
        if (customerByEmail.isEmpty()) {
            throw new RuntimeException("Could not find customer by email: [%s]".formatted(email));
        }
        return customerByEmail.get();
    }

    public void saveServiceRequest(Customer customer) {
        customerDAO.saveServiceRequest(customer);
    }

    public Customer saveCustomer(Customer customer) {
        Customer entity = Customer.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .address(Address.builder()
                        .country(customer.getAddress().getCountry())
                        .city(customer.getAddress().getCity())
                        .postalCode(customer.getAddress().getPostalCode())
                        .address(customer.getAddress().getAddress())
                        .build())
                .build();
        return customerDAO.saveCustomer(entity);
    }
}
