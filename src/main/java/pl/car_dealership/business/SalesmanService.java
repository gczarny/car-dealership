package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.car_dealership.business.dao.SalesmanDAO;
import pl.car_dealership.domain.Salesman;
import pl.car_dealership.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class SalesmanService {

    private final SalesmanDAO salesmanDAO;

    public List<Salesman> findAvailable() {
        List<Salesman> availableSalesman = salesmanDAO.findAvailable();
        log.info("Available salesman: [{}]", availableSalesman);
        return availableSalesman;
    }

    @Transactional
    public Salesman findSalesman(String pesel) {
        Optional<Salesman> salesman = salesmanDAO.findByPesel(pesel);
        if (salesman.isEmpty()) {
            throw new NotFoundException("Could not find salesman by pesel: [%s]".formatted(pesel));
        }
        return salesman.get();
    }
}
