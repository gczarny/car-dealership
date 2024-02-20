package pl.car_dealership.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "servicePartId")
@ToString(of = {"servicePartId", "quantity"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "service_part")
public class ServicePartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_part_id")
    private Long servicePartId;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_service_request_id")
    private CarServiceRequestEntity carServiceRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    private PartEntity part;

}
