package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.car_dealership.business.dao.SalesmanDAO;
import pl.car_dealership.domain.Salesman;

import java.util.Optional;

@AllArgsConstructor
public class SalesmanService {

    private final SalesmanDAO salesmanDAO;

    @Transactional
    public Salesman findSalesman(String pesel) {
        Optional<Salesman> salesmanByPesel = salesmanDAO.findByPesel(pesel);
        if (salesmanByPesel.isEmpty()) {
            throw new RuntimeException("Could not find salesman by pesel: [%s]".formatted(pesel));
        }
        return salesmanByPesel.get();
    }
}
