package by.application.transport.service.impl;

import by.application.transport.entity.Application;
import by.application.transport.entity.Firm;
import by.application.transport.exception.NoDataFoundException;
import by.application.transport.repository.ApplicationRepository;
import by.application.transport.repository.FirmRepository;
import by.application.transport.service.ApplicationService;
import by.application.transport.service.convertor.ApplicationConvertor;
import by.application.transport.service.dto.application.ApplicationResponseDto;
import by.application.transport.service.dto.application.ApplicationSaveRequestDto;
import by.application.transport.service.dto.application.ApplicationUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Maksim Maksimovich
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final FirmRepository firmRepository;
    private final ApplicationConvertor applicationConvertor;

    @Override
    @Transactional
    public void save(ApplicationSaveRequestDto applicationDto) {
        Firm firm = firmRepository.findFirmByUuid(applicationDto.getFirmUuid())
                .orElseThrow(() -> new NoDataFoundException("firm not found"));
        Application application = applicationConvertor.convertSaveDtoToEntity(applicationDto);
        application.setFirm(firm);
        application.setOrderTime(LocalDateTime.now());
        applicationRepository.save(application);
        log.info("application successfully saved");
    }

    @Override
    @Transactional
    public void update(ApplicationUpdateRequestDto applicationDto) {
        Application application = applicationConvertor.convertUpdateDtoToEntity(applicationDto);
        Firm firm = firmRepository.findFirmByUuid(applicationDto.getFirmUuid())
                .orElseThrow(() -> new NoDataFoundException("firm not found"));
        application.setFirm(firm);
        applicationRepository.save(application);
        log.info("application successfully updated");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationResponseDto> findAll() {
        log.info("finding all applications started");
        return applicationRepository.findAll()
                .stream()
                .map(applicationConvertor::convertEntityToDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationResponseDto> findByDate(LocalDate startDate, LocalDate finishDate) {
        return applicationRepository
                .findApplicationsByOrderTimeGreaterThanEqualAndOrderTimeLessThanEqual(startDate.atStartOfDay(), finishDate.atTime(23, 59))
                .stream()
                .map(applicationConvertor::convertEntityToDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationResponseDto findByUuid(UUID uuid) {
        return applicationConvertor
                .convertEntityToDtoResponse(applicationRepository.findApplicationByUuid(uuid)
                        .orElseThrow(() -> new NoDataFoundException("application not found")));
    }
}
