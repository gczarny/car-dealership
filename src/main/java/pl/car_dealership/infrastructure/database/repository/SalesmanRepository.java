package pl.car_dealership.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.car_dealership.business.dao.SalesmanDAO;
import pl.car_dealership.domain.Salesman;
import pl.car_dealership.infrastructure.database.repository.jpa.SalesmanJpaRepository;
import pl.car_dealership.infrastructure.database.repository.mapper.SalesmanEntityMapper;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class SalesmanRepository implements SalesmanDAO {

    private final SalesmanJpaRepository salesmanJpaRepository;
    private final SalesmanEntityMapper salesmanEntityMapper;

    @Override
    public List<Salesman> findAvailable() {
        return salesmanJpaRepository.findAll().stream()
                .map(salesmanEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<Salesman> findByPesel(String pesel) {
        return salesmanJpaRepository.findByPesel(pesel)
                .map(salesmanEntityMapper::mapFromEntity);
    }
}
