package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.car_dealership.infrastructure.database.entity.CarHistoryEntity;

@Repository
public interface CarHistoryJpaRepository extends JpaRepository<CarHistoryEntity, Long> {
}
