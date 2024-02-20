package pl.car_dealership.business;


import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.car_dealership.business.management.FileDataPreparationService;
import pl.car_dealership.domain.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class CarPurchaseService {

    private final FileDataPreparationService fileDataPreparationService;
    private final CustomerService customerService;
    private final CarService carService;
    private final SalesmanService salesmanService;

    @Transactional
    public void purchase() {
        var firstTimePurchaseData = fileDataPreparationService.prepareFirstTimePurchaseData();
        var nextTimePurchaseData = fileDataPreparationService.prepareNextTimePurchaseData();

        List<Customer> firstTimeCustomers = firstTimePurchaseData.stream()
                .map(this::createFirstTimeToBuyCustomer)
                .toList();
        firstTimeCustomers.forEach(customerService::issueInvoice);

        List<Customer> nextTimeCustomers = nextTimePurchaseData.stream()
                .map(this::createNextTimeToBuyCustomer)
                .toList();
        nextTimeCustomers.forEach(customerService::issueInvoice);

    }

    private Customer createFirstTimeToBuyCustomer(CarPurchaseRequestInputData inputData) {
        CarToBuy carToBuy = carService.findCarToBuy(inputData.getCarVin()); //get VIN
        Salesman salesman = salesmanService.findSalesman(inputData.getSalesmanPesel());
        Invoice invoice = buildInvoice(carToBuy, salesman);

        return fileDataPreparationService.buildCustomer(inputData, invoice);
    }

    private Customer createNextTimeToBuyCustomer(CarPurchaseRequestInputData inputData) {
        Customer existingCustomer = customerService.findCustomer(inputData.getCustomerEmail());
        CarToBuy carToBuy = carService.findCarToBuy(inputData.getCarVin()); //get VIN
        Salesman salesman = salesmanService.findSalesman(inputData.getSalesmanPesel());
        Invoice invoice = buildInvoice(carToBuy, salesman);
        existingCustomer.getInvoices().add(invoice);
        return existingCustomer;
    }

    private Invoice buildInvoice(CarToBuy carToBuy, Salesman salesman) {
        return Invoice.builder()
                .invoiceNumber(UUID.randomUUID().toString())
                .dateTime(OffsetDateTime.now(ZoneOffset.UTC))
                .car(carToBuy)
                .salesman(salesman)
                .build();
    }
}
