package pl.car_dealership.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "carToServiceId")
@ToString(of = {"carToServiceId", "vin", "brand", "model", "year"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "car_to_service")
public class CarToServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_to_service_id")
    private Long carToServiceId;

    @Column(name = "vin", unique = true)
    private String vin;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private Set<CarServiceRequestEntity> carServiceRequestEntities;
}
