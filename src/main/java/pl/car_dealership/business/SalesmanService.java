package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import pl.car_dealership.business.dao.SalesmanDAO;
import pl.car_dealership.infrastructure.database.entity.SalesmanEntity;

import java.util.Optional;

@AllArgsConstructor
public class SalesmanService {

    private final SalesmanDAO salesmanDAO;
    public SalesmanEntity findSalesman(String pesel) {
        Optional<SalesmanEntity> salesmanByPesel = salesmanDAO.findByPesel(pesel);
        if (salesmanByPesel.isEmpty()) {
            throw new RuntimeException("Could not find salesman by pesel: [%s]".formatted(pesel));
        }
        return salesmanByPesel.get();
    }
}
