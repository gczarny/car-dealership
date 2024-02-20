package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.CustomerDAO;
import pl.car_dealership.domain.Customer;
import pl.car_dealership.infrastructure.database.entity.CarServiceRequestEntity;
import pl.car_dealership.infrastructure.database.entity.CarToServiceEntity;
import pl.car_dealership.infrastructure.database.entity.CustomerEntity;
import pl.car_dealership.infrastructure.database.entity.InvoiceEntity;
import pl.car_dealership.infrastructure.database.repository.jpa.CarServiceRequestJpaRepository;
import pl.car_dealership.infrastructure.database.repository.jpa.CustomerJpaRepository;
import pl.car_dealership.infrastructure.database.repository.jpa.InvoiceJpaRepository;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class CustomerRepository implements CustomerDAO {

    private final CustomerJpaRepository customerJpaRepository;
    private final InvoiceJpaRepository invoiceJpaRepository;
    private final CarServiceRequestJpaRepository carServiceRequestJpaRepository;
    private final CustomerEntityMapper customerEntityMapper;
    private final InvoiceEntityMapper invoiceEntityMapper;
    private final CarServiceRequestMapper carServiceRequestMapper;


    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerJpaRepository.findByEmail(email)
                .map(customerEntityMapper::mapFromEntity);
    }

    @Override
    public void issueInvoice(Customer customer) {
        CustomerEntity toSave = customerEntityMapper.mapToEntity(customer);
        CustomerEntity saved = customerJpaRepository.save(toSave);

        customer.getInvoices().stream()
                .map(invoiceEntityMapper::mapToEntity)
                .forEach((InvoiceEntity entity) -> {
                            entity.setCustomer(saved);
                            invoiceJpaRepository.save(entity);
                        }
                );
    }

    @Override
    public void saveServiceRequest(Customer customer) {
        List<CarServiceRequestEntity> serviceRequests = customer.getCarServiceRequests().stream()
                .map(carServiceRequestMapper::mapToEntity)
                .toList();

        serviceRequests
                .forEach(request -> request.setCustomer(customerEntityMapper.mapToEntity(customer)));
        carServiceRequestJpaRepository.saveAllAndFlush(serviceRequests);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerEntity carToServiceEntity = customerEntityMapper.mapToEntity(customer);
        CustomerEntity saved = customerJpaRepository.save(carToServiceEntity);
        return customerEntityMapper.mapFromEntity(saved);
    }
}
