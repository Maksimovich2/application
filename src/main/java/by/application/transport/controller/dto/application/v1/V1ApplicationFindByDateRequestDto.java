package by.application.transport.controller.dto.application.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

/**
 * @author Maksim Maksimovich
 */
@Data
public class V1ApplicationFindByDateRequestDto {

    @PastOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @PastOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate finishDate;
}
