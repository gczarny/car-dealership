package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import pl.car_dealership.business.dao.MechanicDAO;
import pl.car_dealership.domain.Mechanic;

import java.util.Optional;

@AllArgsConstructor
public class MechanicService {

    private final MechanicDAO mechanicDAO;
    public Mechanic findMechanic(String pesel) {
        Optional<Mechanic> mechanicByPesel = mechanicDAO.findByPesel(pesel);
        if (mechanicByPesel.isEmpty()) {
            throw new RuntimeException("Could not find mechanic by pesel: [%s]".formatted(pesel));
        }
        return mechanicByPesel.get();
    }
}
