package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.car_dealership.infrastructure.database.entity.CarToServiceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CarToServiceJpaRepository extends JpaRepository<CarToServiceEntity, Integer> {


}
