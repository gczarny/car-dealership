package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import pl.car_dealership.business.dao.MechanicDAO;
import pl.car_dealership.infrastructure.database.entity.MechanicEntity;

import java.util.Optional;

@AllArgsConstructor
public class MechanicService {

    private final MechanicDAO mechanicDAO;
    public MechanicEntity findMechanic(String pesel) {
        Optional<MechanicEntity> mechanicByPesel = mechanicDAO.findByPesel(pesel);
        if (mechanicByPesel.isEmpty()) {
            throw new RuntimeException("Could not find mechanic by pesel: [%s]".formatted(pesel));
        }
        return mechanicByPesel.get();
    }
}
