package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.car_dealership.infrastructure.database.entity.SalesmanEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesmanJpaRepository extends JpaRepository<SalesmanEntity, Integer> {

}