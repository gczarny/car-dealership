package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.car_dealership.business.dao.MechanicDAO;
import pl.car_dealership.domain.Mechanic;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MechanicService {

    private final MechanicDAO mechanicDAO;

    @Transactional
    public Mechanic findMechanic(String pesel) {
        Optional<Mechanic> mechanic = mechanicDAO.findByPesel(pesel);
        if (mechanic.isEmpty()) {
            throw new RuntimeException("Could not find mechanic by pesel: [%s]".formatted(pesel));
        }
        return mechanic.get();
    }
}
