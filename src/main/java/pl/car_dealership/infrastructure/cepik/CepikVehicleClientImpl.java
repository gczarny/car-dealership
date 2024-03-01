package pl.car_dealership.infrastructure.cepik;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.car_dealership.business.dao.CepikVehicleDAO;
import pl.car_dealership.domain.CepikVehicle;
import pl.car_dealership.domain.exception.NotFoundException;
import pl.zajavka.infrastructure.cepik.api.PojazdyApi;
import pl.zajavka.infrastructure.cepik.api.SownikiApi;
import pl.zajavka.infrastructure.cepik.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CepikVehicleClientImpl implements CepikVehicleDAO {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private static final String VOIVODESHIP_KEY = "MAZOWIECKIE";
    private static final String VOIVODESHIP_DICTIONARY = "wojewodztwa";
    private final CepikVehicleMapper cepikVehicleMapper;
    private final PojazdyApi pojazdyApi;
    private final SownikiApi sownikiApi;

    @Override
    public List<CepikVehicle> getCepikVehicles(LocalDate dateFrom, LocalDate dateTo) {
        DictionaryDtoElement dictionaryEntry = getDictionaryEntry();
        var vehicles = callVehicles(dateFrom, dateTo, dictionaryEntry.getKluczSlownika());

        return vehicles.map(JsonApiForListVehicle::getData)
                .map(data -> data.stream()
                        .map(vehicleData -> cepikVehicleMapper.map(vehicleData.getId(), vehicleData.getAttributes()))
                        .toList())
                .orElse(Collections.emptyList());
    }

    @Override
    public CepikVehicle getCepikVehicle(String vehicleId) {
        return Optional.ofNullable(pojazdyApi.getPojazd(vehicleId, null).block())
                .map(JsonApiForObjectVehicle::getData)
                .map(data -> cepikVehicleMapper.map(data.getId(), data.getAttributes()))
                .orElseThrow(() -> new NotFoundException(
                        "Could not find vehicle with id: [%s]".formatted(vehicleId)
                ));
    }

    private DictionaryDtoElement getDictionaryEntry() {
        var dictionary = Optional.ofNullable(sownikiApi.getSlownik(VOIVODESHIP_DICTIONARY).block())
                .orElseThrow(() -> new NotFoundException(
                        "Could not find dictionary definition for: [%s]".formatted(VOIVODESHIP_DICTIONARY)
                ));

        return Optional.ofNullable(dictionary.getData())
                .map(a -> a.getAttributes())
                .map(a -> a.getDostepneRekordySlownika())
                .flatMap(records -> records.stream()
                        .filter(record -> VOIVODESHIP_KEY.equals(record.getWartoscSlownika()))
                        .findAny())
                .orElseThrow(() -> new NotFoundException(
                        "Could not find dictionary entry for: [%s]".formatted(VOIVODESHIP_KEY)
                ));
    }

    private Optional<JsonApiForListVehicle> callVehicles(LocalDate dateFrom, LocalDate dateTo, String key) {
        return Optional.ofNullable(pojazdyApi.getListaPojazdow(
                key,
                dateFrom.format(DATE_TIME_FORMATTER),
                dateTo.format(DATE_TIME_FORMATTER),
                "1",
                true,
                true,
                null,
                "50",
                "1",
                null
        ).block());
    }
}
