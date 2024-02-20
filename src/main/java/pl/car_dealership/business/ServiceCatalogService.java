package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.car_dealership.business.dao.ServiceDAO;
import pl.car_dealership.domain.Service;

import java.util.Optional;

@AllArgsConstructor
public class ServiceCatalogService {

    private final ServiceDAO serviceDAO;

    @Transactional
    public Service findService(String serviceCode) {
        Optional<Service> service = serviceDAO.findByServiceCode(serviceCode);
        if (service.isEmpty()) {
            throw new RuntimeException("Could not find service by service code: [%s]".formatted(serviceCode));
        }
        return service.get();
    }
}