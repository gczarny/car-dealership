package pl.car_dealership.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.car_dealership.infrastructure.database.entity.CarToBuyEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarToBuyJpaRepository extends JpaRepository<CarToBuyEntity, Long> {

    Optional<CarToBuyEntity> findByVin(String vin);

//    @Query("""
//        SELECT car FROM CarToBuyEntity car
//        WHERE car.carToBuyId NOT IN (SELECT invoice.car.carToBuyId FROM InvoiceEntity invoice)
//    """)
    @Query("""
        SELECT car FROM CarToBuyEntity car 
        LEFT JOIN FETCH car.invoice invoice
        WHERE invoice.car.carToBuyId IS NULL
     """)
    List<CarToBuyEntity> findAvailableCars();

}
