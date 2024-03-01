package pl.car_dealership.business.dao;

import pl.car_dealership.domain.CepikVehicle;

import java.time.LocalDate;
import java.util.List;

public interface CepikVehicleDAO {

    List<CepikVehicle> getCepikVehicles(final LocalDate dateFrom, final LocalDate dateTo);

    CepikVehicle getCepikVehicle(final String vehicleId);
}
