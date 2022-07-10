package by.application.transport.controller.dto.application.v1;

import by.application.transport.entity.ApplicationStatus;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Maksim Maksimovich
 */
@Data
public class V1ApplicationResponseDto {
    private Long id;
    private LocalDateTime orderTime;
    private BigDecimal orderPrice;

    @ColumnTransformer(read = "UPPER(status)", write = "LOWER(?)")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private Long clientId;
    private Long carId;
    private Long userDriverId;
    private Long firmId;
}
