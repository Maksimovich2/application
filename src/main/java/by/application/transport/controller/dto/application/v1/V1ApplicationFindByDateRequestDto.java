package by.application.transport.controller.dto.application.v1;

import lombok.Data;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

/**
 * @author Maksim Maksimovich
 */
@Data
public class V1ApplicationFindByDateRequestDto {

    @PastOrPresent
    private LocalDateTime startDate;

    @PastOrPresent
    private LocalDateTime finishDate;
}
