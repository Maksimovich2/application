package by.application.transport.service.impl;

import by.application.transport.entity.Firm;
import by.application.transport.exception.NoDataFoundException;
import by.application.transport.repository.FirmRepository;
import by.application.transport.service.FirmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Maksim Maksimovich
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FirmServiceImpl implements FirmService {
    private final FirmRepository firmRepository;

    @Override
    @Transactional
    public void save(Firm firm) {
        firmRepository.save(firm);
        log.info("firm successfully saved by: " + firm.getName() + " name");
    }

    @Override
    @Transactional
    public void update(Firm firm) {
        firmRepository.save(firm);
        log.info("firm successfully updated by: " + firm.getName() + " name");
    }

    @Override
    @Transactional(readOnly = true)
    public Firm findByName(String name) {
        log.info("finding firm by: " + name + " name started");
        return firmRepository.findFirmByName(name)
                .orElseThrow(() -> new NoDataFoundException("firm not found by: " + name));
    }
}