package by.application.transport.controller.dto.firm.v1;

import lombok.Data;

import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
@Data
public class V1FirmResponseByNameDto {
    private UUID id;
    private String name;
    private String phone;
}
