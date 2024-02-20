package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.car_dealership.infrastructure.database.entity.SalesmanEntity;

import java.util.Optional;

@Repository
public interface SalesmanJpaRepository extends JpaRepository<SalesmanEntity, Long> {

    Optional<SalesmanEntity> findByPesel(String pesel);
}
