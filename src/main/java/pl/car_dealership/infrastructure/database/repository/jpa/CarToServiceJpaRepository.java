package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.car_dealership.infrastructure.database.entity.CarToServiceEntity;

import java.util.Optional;

@Repository
public interface CarToServiceJpaRepository extends JpaRepository<CarToServiceEntity, Long> {


    Optional<CarToServiceEntity> findByVin(String vin);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"carServiceRequests",
                    "carServiceRequests.serviceMechanic",
                    "carServiceRequests.serviceMechanic.service",
                    "carServiceRequests.serviceParts.part",

            }
    )
    CarToServiceEntity findCarHistoryByVin(String vin);
}
