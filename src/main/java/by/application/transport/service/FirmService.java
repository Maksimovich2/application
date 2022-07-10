package by.application.transport.service;

import by.application.transport.entity.Firm;

/**
 * @author Maksim Maksimovich
 */
public interface FirmService {
    void save(Firm firm);

    void update(Firm firm);

    Firm findByName(String name);
}
