package by.application.transport.service;

import by.application.transport.service.dto.application.ApplicationResponseDto;
import by.application.transport.service.dto.application.ApplicationSaveRequestDto;
import by.application.transport.service.dto.application.ApplicationUpdateRequestDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
public interface ApplicationService {
    void save(ApplicationSaveRequestDto applicationDto);

    void update(ApplicationUpdateRequestDto applicationDto);

    List<ApplicationResponseDto> findAll();

    List<ApplicationResponseDto> findByDate(LocalDate startDate, LocalDate finishDate);

    ApplicationResponseDto findByUuid(UUID uuid);
}
