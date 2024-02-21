package pl.car_dealership.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "mechanicId")
@ToString(of = {"mechanicId", "name", "surname", "pesel"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mechanic")
public class MechanicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mechanic_id")
    private Long mechanicId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pesel")
    private String pesel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mechanic")
    private Set<ServiceMechanicEntity> serviceMechanics;
}
