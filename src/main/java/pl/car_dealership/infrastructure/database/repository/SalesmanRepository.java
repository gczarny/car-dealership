package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.SalesmanDAO;
import pl.car_dealership.domain.Salesman;
import pl.car_dealership.infrastructure.database.repository.jpa.SalesmanJpaRepository;

import java.util.Optional;


@Repository
@AllArgsConstructor
public class SalesmanRepository implements SalesmanDAO {

    private final SalesmanJpaRepository salesmanJpaRepository;
    private final SalesmanMapper salesmanMapper;

    @Override
    public Optional<Salesman> findByPesel(String pesel) {
        return salesmanJpaRepository.findByPesel(pesel)
                .map(salesmanMapper::mapFromEntity);
    }
}
