package by.application.transport.service.impl;

import by.application.transport.entity.Application;
import by.application.transport.repository.ApplicationRepository;
import by.application.transport.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Maksim Maksimovich
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public void save(Application application) {
        applicationRepository.save(application);
        log.info("application successfully saved");
    }

    @Override
    @Transactional
    public void update(Application application) {
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
    public List<Application> findByDate(LocalDateTime startDate, LocalDateTime finishDate) {
        return applicationRepository.findApplicationByOrderTimeAfterAndOrderTimeBefore(startDate, finishDate);
    }
}
