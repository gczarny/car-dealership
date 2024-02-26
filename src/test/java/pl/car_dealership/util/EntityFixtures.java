package pl.car_dealership.util;


import lombok.experimental.UtilityClass;
import pl.car_dealership.infrastructure.database.entity.CarToBuyEntity;

import java.math.BigDecimal;
import java.util.List;

@UtilityClass
public class EntityFixtures {

    public static CarToBuyEntity someCar1() {
        return CarToBuyEntity.builder()
                .vin("12345678901234567")
                .brand("Audi")
                .model("A4")
                .year(2000)
                .color("red")
                .price(BigDecimal.valueOf(50000))
                .build();
    }

    public static CarToBuyEntity someCar2() {
        return CarToBuyEntity.builder()
                .vin("12345678901234568")
                .brand("Audi")
                .model("A5")
                .year(2001)
                .color("blue")
                .price(BigDecimal.valueOf(60000))
                .build();
    }

    public static CarToBuyEntity someCar3() {
        return CarToBuyEntity.builder()
                .vin("12345678901234569")
                .brand("BMW")
                .model("Jakieś")
                .year(2002)
                .color("green")
                .price(BigDecimal.valueOf(70000))
                .build();
    }


}
