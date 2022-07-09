package by.application.transport.service;

import by.application.transport.entity.Application;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Maksim Maksimovich
 */
public interface ApplicationService {
    void save(Application application);

    void update(Application application);

    List<Application> findAll();

    List<Application> findByDate(LocalDateTime startDate, LocalDateTime finishDate);
}
