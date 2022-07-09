package by.application.transport.controller.dto.firm;

import by.application.transport.controller.dto.application.ApplicationResponseDto;
import lombok.Data;

import java.util.List;

/**
 * @author Maksim Maksimovich
 */
@Data
public class FirmResponseDto {
    private Long id;
    private String name;
    private String phone;
    private List<ApplicationResponseDto> applications;
}
