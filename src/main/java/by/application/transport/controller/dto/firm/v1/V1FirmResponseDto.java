package by.application.transport.controller.dto.firm.v1;

import by.application.transport.controller.dto.application.v1.V1ApplicationResponseDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
@Data
public class V1FirmResponseDto {
    private UUID uuid;
    private String name;
    private String phone;
    private List<V1ApplicationResponseDto> applications;
}
