package pl.car_dealership.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.car_dealership.business.dao.CepikVehicleDAO;
import pl.car_dealership.domain.CepikVehicle;
import pl.car_dealership.domain.exception.NotFoundException;
import pl.car_dealership.domain.exception.ProcessingException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@AllArgsConstructor
@Service
public class CepikService {

    private final CepikVehicleDAO cepikVehicleDAO;

    public CepikVehicle findRandom(final LocalDate dateFrom, final LocalDate dateTo) {
        log.debug("Looking for random CEPIK vehicle, first registration between: [{}] and [{}]", dateFrom, dateTo);
        List<CepikVehicle> cepikVehicles = cepikVehicleDAO.getCepikVehicles(dateFrom, dateTo);
        if (cepikVehicles.isEmpty()) {
            throw new ProcessingException(
                    String.format("CEPIK returned empty list for first registration between [%s] and [%s]", dateFrom, dateTo)
            );
        }

        return Optional.ofNullable(cepikVehicles.get(new Random().nextInt(cepikVehicles.size())))
                .map(anyVehicle -> cepikVehicleDAO.getCepikVehicle(anyVehicle.getCepikId()))
                .orElseThrow(() -> new NotFoundException("Could not find random CEPIK vehicle"));
    }
}
