package pl.car_dealership.integration;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.car_dealership.business.CarPurchaseService;
import pl.car_dealership.business.CarService;
import pl.car_dealership.business.CarServiceProcessingService;
import pl.car_dealership.business.CarServiceRequestService;
import pl.car_dealership.infrastructure.configuration.ApplicationConfiguration;
import pl.car_dealership.infrastructure.database.entity.CarServiceRequestEntity;
import pl.car_dealership.infrastructure.database.entity.ServiceMechanicEntity;
import pl.car_dealership.infrastructure.database.entity.ServicePartEntity;
import pl.car_dealership.infrastructure.database.repository.jpa.CarServiceRequestJpaRepository;
import pl.car_dealership.infrastructure.database.repository.jpa.InvoiceJpaRepository;
import pl.car_dealership.infrastructure.database.repository.jpa.ServiceMechanicJpaRepository;
import pl.car_dealership.infrastructure.database.repository.jpa.ServicePartJpaRepository;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringJUnitConfig(classes = {ApplicationConfiguration.class})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarDealershipTest {

    @Container
    static PostgreSQLContainer<?> POSTGRESQL_CONTAINER = new PostgreSQLContainer<>("postgres:15.0");

    @DynamicPropertySource
    static void postgresqlContainerProperties(DynamicPropertyRegistry registry) {
        registry.add("jdbc.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("jdbc.username", POSTGRESQL_CONTAINER::getUsername);
        registry.add("jdbc.pass", POSTGRESQL_CONTAINER::getPassword);
    }

    private CarPurchaseService carPurchaseService;
    private CarServiceRequestService carServiceRequestService;
    private CarServiceProcessingService carServiceProcessingService;
    private CarService carService;

    private final InvoiceJpaRepository invoiceJpaRepository;
    private final CarServiceRequestJpaRepository carServiceRequestJpaRepository;
    private final ServiceMechanicJpaRepository serviceMechanicJpaRepository;
    private final ServicePartJpaRepository servicePartJpaRepository;

    @Test
    @Order(1)
    void purhcase() {
        log.info("### RUNNING ORDER 3 ###");
        carPurchaseService.purchase();
    }

    @Test
    @Order(2)
    void makeServiceRequest() {
        log.info("### RUNNING ORDER 4 ###");
        carServiceRequestService.requestService();
    }

    @Test
    @Order(3)
    void processServiceRequest() {
        log.info("### RUNNING ORDER 5 ###");
        carServiceProcessingService.process();
    }

    @Test
    @Order(4)
    void printCarHistory() {
        log.info("### RUNNING ORDER 6 ###");
        carService.printCarHistory("2C3CDYAG2DH731952");
        carService.printCarHistory("1GCEC19X27Z109567");
    }

    @Test
    @Order(5)
    void verify() {
        assertEquals(6, invoiceJpaRepository.findAll().size());

        List<CarServiceRequestEntity> allServiceRequests = carServiceRequestJpaRepository.findAll();
        assertEquals(3, allServiceRequests.size());
        assertEquals(2, allServiceRequests.stream()
                .filter(sr -> Objects.nonNull(sr.getCompletedDateTime())).count());

        List<ServiceMechanicEntity> allServiceMechanics = serviceMechanicJpaRepository.findAll();
        assertEquals(5, allServiceMechanics.size());

        List<ServicePartEntity> allServiceParts = servicePartJpaRepository.findAll();
        assertEquals(4, allServiceParts.size());
    }
}
