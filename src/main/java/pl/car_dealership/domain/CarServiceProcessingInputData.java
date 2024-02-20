package pl.car_dealership.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class CarServiceProcessingInputData {

    String customerName;
    String customerSurname;
    String customerPhone;
    String customerEmail;
    String customerAddressCountry;
    String customerAddressCity;
    String customerAddressPostalCode;
    String customerAddressStreet;
    String carVin;
    String salesmanPesel;
}