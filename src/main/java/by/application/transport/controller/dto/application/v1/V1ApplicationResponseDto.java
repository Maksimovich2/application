package by.application.transport.controller.dto.application.v1;

import by.application.transport.entity.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
@Data
public class V1ApplicationResponseDto {
    private UUID uuid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate orderTime;

    private BigDecimal orderPrice;

    @ColumnTransformer(read = "UPPER(status)", write = "LOWER(?)")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private Long clientUuid;
    private Long carUuid;
    private Long userDriverUuid;
    private UUID firmUuid;
}
