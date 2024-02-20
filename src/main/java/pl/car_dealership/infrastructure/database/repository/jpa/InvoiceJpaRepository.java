package pl.car_dealership.infrastructure.database.repository.jpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.car_dealership.infrastructure.database.entity.InvoiceEntity;

@Repository
public interface InvoiceJpaRepository extends JpaRepository<InvoiceEntity, Long> {


}
