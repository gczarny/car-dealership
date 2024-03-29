package pl.car_dealership.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "carServiceRequestId")
@ToString(of = {"carServiceRequestId", "carServiceRequestNumber", "receivedDateTime", "completedDateTime", "customerComment"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "car_service_request")
public class CarServiceRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_service_request_id")
    private Long carServiceRequestId;

    @Column(name = "car_service_request_number", unique = true)
    private String carServiceRequestNumber;

    @Column(name = "received_date_time")
    private OffsetDateTime receivedDateTime;

    @Column(name = "completed_date_time")
    private OffsetDateTime completedDateTime;

    @Column(name = "customer_comment")
    private String customerComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_to_service_id")
    private CarToServiceEntity car;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carServiceRequest")
    private Set<ServiceMechanicEntity> serviceMechanics;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carServiceRequest")
    private Set<ServicePartEntity> serviceParts;
}
