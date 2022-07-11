package by.application.transport.controller.dto.application.v1;

import by.application.transport.entity.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Maksim Maksimovich
 */
@Data
public class V1ApplicationUpdateRequestDto {
    private Long id;

    @PastOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate orderTime;

    @DecimalMin(value = "0.1", message = "application price can't be less 0.1")
    private BigDecimal orderPrice;

    @ColumnTransformer(read = "UPPER(status)", write = "LOWER(?)")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private Long clientId;
    private Long carId;
    private Long userDriverId;
    private Long firmId;
}
