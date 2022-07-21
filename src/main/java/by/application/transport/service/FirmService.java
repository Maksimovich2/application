package by.application.transport.service;

import by.application.transport.service.dto.firm.FirmResponseByNameDto;
import by.application.transport.service.dto.firm.FirmSaveRequestDto;
import by.application.transport.service.dto.firm.FirmUpdateRequestDto;

import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
public interface FirmService {
    void save(FirmSaveRequestDto firmDto);

    void update(FirmUpdateRequestDto firmDto);

    FirmResponseByNameDto findByName(String name);

    FirmResponseByNameDto findByUuid(UUID uuid);
}
