package pl.car_dealership.api.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.car_dealership.infrastructure.database.entity.CarToBuyEntity;
import pl.car_dealership.infrastructure.database.repository.jpa.CarToBuyJpaRepository;
import pl.car_dealership.integration.configuration.PersistenceContainerTestConfiguration;

import java.util.List;

import static pl.car_dealership.util.EntityFixtures.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarToBuyJpaRepositoryTest {

    private CarToBuyJpaRepository carToBuyJpaRepository;

    @Test
    void thatCarBeSavedCorrectly() {
        //given
        var cars = List.of(someCar1(), someCar2(), someCar3());
        carToBuyJpaRepository.saveAllAndFlush(cars);

        // when
        List<CarToBuyEntity> availableCars = carToBuyJpaRepository.findAvailableCars();

        // then
        Assertions.assertThat(availableCars).hasSize(9);
    }
}
