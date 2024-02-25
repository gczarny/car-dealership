package pl.car_dealership.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarPurchaseDTO {

    private String existingCustomerEmail;
    private String customerName;
    private String customerSurname;
    private String customerPhone;
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
}
