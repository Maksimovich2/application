package by.application.transport.service.impl;

import by.application.transport.entity.Application;
import by.application.transport.entity.Firm;
import by.application.transport.exception.NoDataFoundException;
import by.application.transport.repository.ApplicationRepository;
import by.application.transport.service.ApplicationService;
import by.application.transport.service.FirmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final FirmService firmService;

    @Override
    @Transactional
    public void save(Application application) {
        application.setOrderTime(LocalDateTime.now());
        Firm firm = firmService.findByUuid(application.getFirm().getUuid());
        application.setFirm(firm);
        applicationRepository.save(application);
        log.info("application successfully saved");
    }

    @Override
    @Transactional
    public void update(Application application) {
        application.setOrderTime(LocalDateTime.now());
        applicationRepository.save(application);
        log.info("application successfully updated");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Application> findAll() {
        log.info("finding all applications started");
        return applicationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Application> findByDate(LocalDate startDate, LocalDate finishDate) {
        return applicationRepository
                .findApplicationsByOrderTimeGreaterThanEqualAndOrderTimeLessThanEqual(startDate.atStartOfDay(), finishDate.atTime(23, 59));
    }

    @Override
    public Application findByUuid(UUID uuid) {
        return applicationRepository.findApplicationByUuid(uuid)
                .orElseThrow(() -> new NoDataFoundException("application not found"));
    }
}
