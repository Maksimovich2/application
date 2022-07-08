package by.application.transport.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Maksim Maksimovich
 */
@Entity
@Table(name = "applications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Application extends ParentEntity {

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Column(name = "order_price")
    private LocalDateTime orderPrice;

    @Column(name = "status")
    private ApplicationStatus applicationStatus;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "car_id")
    private Long carId;

    @Column(name = "user_driver_id")
    private Long userDriverId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "firm_id", referencedColumnName = "id")
    private Firm firm;
}
