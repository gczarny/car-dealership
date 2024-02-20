package pl.car_dealership.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "salesmanId")
@ToString(of = {"salesmanId", "name", "surname", "pesel"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "salesman")
public class SalesmanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salesman_id")
    private Long salesmanId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pesel", unique = true)
    private String pesel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "salesman")
    private Set<InvoiceEntity> invoices;

}
