package pl.car_dealership.infrastructure.database.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.car_dealership.domain.CarServiceRequest;
import pl.car_dealership.infrastructure.database.entity.CarServiceRequestEntity;

import java.util.Set;

@Repository
public interface CarServiceRequestJpaRepository extends JpaRepository<CarServiceRequestEntity, Long> {


    @Query("""
            SELECT csr FROM CarServiceRequestEntity csr
            WHERE csr.completedDateTime is NULL AND csr.car.vin = :carVin
            """)
    Set<CarServiceRequestEntity> findActiveServiceRequestsByCarVin(final @Param("vin") String carVin);
}
