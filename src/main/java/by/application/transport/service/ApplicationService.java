package by.application.transport.service;

import by.application.transport.entity.Application;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Maksim Maksimovich
 */
public interface ApplicationService {
    void save(Application application);

    void update(Application application);

    List<Application> findAll();

    List<Application> findByDate(LocalDate startDate, LocalDate finishDate);
}
