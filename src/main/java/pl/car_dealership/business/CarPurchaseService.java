package pl.car_dealership.business;


import lombok.AllArgsConstructor;
import pl.car_dealership.business.management.FileDataPreparationService;
import pl.car_dealership.business.management.Keys;
import pl.car_dealership.infrastructure.database.entity.CarToBuyEntity;
import pl.car_dealership.infrastructure.database.entity.CustomerEntity;
import pl.car_dealership.infrastructure.database.entity.InvoiceEntity;
import pl.car_dealership.infrastructure.database.entity.SalesmanEntity;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class CarPurchaseService {

    private final FileDataPreparationService fileDataPreparationService;
    private final CustomerService customerService;
    private final CarService carService;
    private final SalesmanService salesmanService;

    public void purchase() {
        var firstTimePurchaseData = fileDataPreparationService.prepareFirstTimePurchaseData();
        var nextTimePurchaseData = fileDataPreparationService.prepareNextTimePurchaseData();

        List<CustomerEntity> firstTimeCustomers = firstTimePurchaseData.stream()
                .map(this::createFirstTimeToBuyCustomer)
                .toList();
        firstTimeCustomers.forEach(customerService::issueInvoice);

        List<CustomerEntity> nextTimeCustomers = nextTimePurchaseData.stream()
                .map(this::createNextTimeToBuyCustomer)
                .toList();
        nextTimeCustomers.forEach(customerService::issueInvoice);

    }

    private CustomerEntity createFirstTimeToBuyCustomer(Map<String, List<String>> inputData) {
        CarToBuyEntity carToBuy = carService.findCarToBuy(inputData.get(Keys.Entity.CAR.toString()).get(0)); //get VIN
        SalesmanEntity salesman = salesmanService.findSalesman(inputData.get(Keys.Entity.SALESMAN.toString()).get(0));
        InvoiceEntity invoice = buildInvoice(carToBuy, salesman);

        return fileDataPreparationService.buildCustomerEntity(inputData.get(Keys.Entity.CUSTOMER.toString()), invoice);
    }

    private CustomerEntity createNextTimeToBuyCustomer(Map<String, List<String>> inputData) {
        CustomerEntity existingCustomer = customerService.findCustomer(inputData.get(Keys.Entity.CUSTOMER.toString()).get(0));
        CarToBuyEntity carToBuy = carService.findCarToBuy(inputData.get(Keys.Entity.CAR.toString()).get(0)); //get VIN
        SalesmanEntity salesman = salesmanService.findSalesman(inputData.get(Keys.Entity.SALESMAN.toString()).get(0));
        InvoiceEntity invoice = buildInvoice(carToBuy, salesman);
        existingCustomer.getInvoices().add(invoice);
        return existingCustomer;
    }

    private InvoiceEntity buildInvoice(CarToBuyEntity carToBuy, SalesmanEntity salesman) {
        return InvoiceEntity.builder()
                .invoiceNumber(UUID.randomUUID().toString())
                .dateTime(OffsetDateTime.now(ZoneOffset.UTC))
                .car(carToBuy)
                .salesman(salesman)
                .build();
    }
}
