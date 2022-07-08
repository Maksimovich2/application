package by.application.transport.repository;

import by.application.transport.entity.Firm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maksim Maksimovich
 */
public interface FirmRepository extends JpaRepository<Firm, Long> {
}
