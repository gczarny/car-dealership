package pl.car_dealership.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarPurchaseDTO {

    @Email
    private String existingCustomerEmail;
    private String customerName;
    private String customerSurname;
    @Size()
    @Pattern(regexp = "^[+]\\d{2}\\s\\d{3}\\s\\d{3}\\s\\d{3}$")
    private String customerPhone;
    @Email
    private String customerEmail;
    private String customerAddressCountry;
    private String customerAddressCity;
    private String customerAddressPostalCode;
    private String customerAddressStreet;

    private String carVin;
    private String salesmanPesel;

    public static CarPurchaseDTO buildDefaultData() {
        return CarPurchaseDTO.builder()
                .customerName("Alfred")
                .customerSurname("Samochodowy")
                .customerPhone("+48 2902201")
                .customerEmail("alfred@gmail.com")
                .customerAddressCountry("Polska")
                .customerAddressCity("Warszawa")
                .customerAddressPostalCode("00-001")
                .customerAddressStreet("Bokserska 1")
                .build();
    }

    public Map<String, String> asMap() {
        Map<String, String> result = new HashMap<>();
        ofNullable(customerName).ifPresent(v -> result.put("customerName", v));
        ofNullable(customerSurname).ifPresent(v -> result.put("customerSurname", v));
        ofNullable(customerPhone).ifPresent(v -> result.put("customerPhone", v));
        ofNullable(customerEmail).ifPresent(v -> result.put("customerEmail", v));
        ofNullable(existingCustomerEmail).ifPresent(v -> result.put("existingCustomerEmail", v));
        ofNullable(customerAddressCountry).ifPresent(v -> result.put("customerAddressCountry", v));
        ofNullable(customerAddressCity).ifPresent(v -> result.put("customerAddressCity", v));
        ofNullable(customerAddressPostalCode).ifPresent(v -> result.put("customerAddressPostalCode", v));
        ofNullable(customerAddressStreet).ifPresent(v -> result.put("customerAddressStreet", v));
        ofNullable(carVin).ifPresent(v -> result.put("carVin", v));
        ofNullable(salesmanPesel).ifPresent(v -> result.put("salesmanPesel", v));
        return result;
    }
}
