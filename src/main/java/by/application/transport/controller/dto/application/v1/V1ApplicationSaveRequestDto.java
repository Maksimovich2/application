package by.application.transport.controller.dto.application.v1;

import by.application.transport.entity.ApplicationStatus;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
@Data
public class V1ApplicationSaveRequestDto {

    @DecimalMin(value = "0.1", message = "application price can't be less 0.1")
    private BigDecimal orderPrice;

    @ColumnTransformer(read = "UPPER(status)", write = "LOWER(?)")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @NotNull(message = "field client cannot be null")
    private UUID clientUuid;

    @NotNull(message = "field car cannot be null")
    private UUID carUuid;

    @NotNull(message = "field user driver cannot be null")
    private UUID userDriverUuid;

    @NotNull(message = "field firm cannot be null")
    private UUID firmUuid;
}
