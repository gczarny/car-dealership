package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.car_dealership.business.dao.MechanicDAO;
import pl.car_dealership.domain.Mechanic;
import pl.car_dealership.domain.Salesman;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MechanicService {

    private final MechanicDAO mechanicDAO;

    @Transactional
    public List<Mechanic> findAvailable() {
        List<Mechanic> availableMechanic = mechanicDAO.findAvailable();
        log.info("Available mechanic: [{}]", availableMechanic);
        return availableMechanic;
    }

    @Transactional
    public Mechanic findMechanic(String pesel) {
        Optional<Mechanic> mechanic = mechanicDAO.findByPesel(pesel);
        if (mechanic.isEmpty()) {
            throw new RuntimeException("Could not find mechanic by pesel: [%s]".formatted(pesel));
        }
        return mechanic.get();
    }
}
