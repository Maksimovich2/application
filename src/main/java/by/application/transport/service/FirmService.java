package by.application.transport.service;

import by.application.transport.entity.Firm;

import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
public interface FirmService {
    void save(Firm firm);

    void update(Firm firm);

    Firm findByName(String name);

    Firm findByUuid(UUID uuid);
}
