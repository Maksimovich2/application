package by.application.transport.service.dto.firm;

import by.application.transport.service.dto.application.ApplicationResponseDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
@Data
public class FirmResponseDto {
    private UUID uuid;
    private String name;
    private String phone;
    private List<ApplicationResponseDto> applications;
}
