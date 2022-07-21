package by.application.transport.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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
    private BigDecimal orderPrice;

    @Column(name = "status")
    @ColumnTransformer(read = "UPPER(status)", write = "LOWER(?)")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "car_id")
    private UUID carId;

    @Column(name = "user_driver_id")
    private UUID userDriverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firm_id", referencedColumnName = "id")
    private Firm firm;
}
