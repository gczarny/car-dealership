package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.car_dealership.business.dao.CustomerDAO;
import pl.car_dealership.domain.Address;
import pl.car_dealership.domain.Customer;

import java.util.Optional;

@AllArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;

    @Transactional
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

    @Transactional
    public void saveServiceRequest(Customer customer) {
        customerDAO.saveServiceRequest(customer);
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerDAO.saveCustomer(customer);
    }
}
