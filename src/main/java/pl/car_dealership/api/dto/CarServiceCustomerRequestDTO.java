package pl.car_dealership.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.car_dealership.domain.CarToService;
import pl.car_dealership.domain.Customer;
import pl.car_dealership.domain.ServiceMechanic;
import pl.car_dealership.domain.ServicePart;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarServiceCustomerRequestDTO {

    private String existingCustomerEmail;

    private String customerName;
    private String customerSurname;
    private String customerPhone;
    private String customerEmail;
    private String customerAddressCountry;
    private String customerAddressCity;
    private String customerAddressPostalCode;
    private String customerAddressStreet;

    private String existingCarVin;
    private String carVin;
    private String carBrand;
    private String carModel;
    private Integer carYear;

    private String customerComment;

    public static Object buildDefault() {
        return CarServiceCustomerRequestDTO.builder()
                .existingCustomerEmail("alfred@gmail.com")
                .existingCarVin("1FT7X2B60FEA74019")
                .customerComment("Olej cieknie mi na stopy")
                .build();
    }

    public boolean isNewCarCandidate() {
        return Objects.isNull(getExistingCustomerEmail())
                || getExistingCustomerEmail().isBlank()
                || Objects.isNull(getExistingCarVin())
                || getExistingCarVin().isBlank();
    }
}
