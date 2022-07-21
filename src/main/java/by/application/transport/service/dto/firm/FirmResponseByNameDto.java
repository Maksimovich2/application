package by.application.transport.service.dto.firm;

import lombok.Data;

import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
@Data
public class FirmResponseByNameDto {
    private UUID uuid;
    private String name;
    private String phone;
}
