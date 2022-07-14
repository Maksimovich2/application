package by.application.transport.repository;

import by.application.transport.entity.Firm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
public interface FirmRepository extends JpaRepository<Firm, Long> {
    Optional<Firm> findFirmByName(String name);

    Optional<Firm> findFirmByPhone(String phone);

    Optional<Firm> findFirmByUuid(UUID uuid);
}
