package by.application.transport.repository;

import by.application.transport.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Maksim Maksimovich
 */
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findApplicationsByOrderTimeGreaterThanEqualAndOrderTimeLessThanEqual(LocalDate after, LocalDate before);
}
