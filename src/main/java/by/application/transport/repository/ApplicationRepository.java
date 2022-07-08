package by.application.transport.repository;

import by.application.transport.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maksim Maksimovich
 */
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
