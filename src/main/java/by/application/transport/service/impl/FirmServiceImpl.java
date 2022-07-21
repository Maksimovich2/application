package by.application.transport.service.impl;

import by.application.transport.entity.Firm;
import by.application.transport.exception.DataValidException;
import by.application.transport.exception.NoDataFoundException;
import by.application.transport.repository.FirmRepository;
import by.application.transport.service.FirmService;
import by.application.transport.service.convertor.FirmConvertor;
import by.application.transport.service.dto.firm.FirmResponseByNameDto;
import by.application.transport.service.dto.firm.FirmSaveRequestDto;
import by.application.transport.service.dto.firm.FirmUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FirmServiceImpl implements FirmService {
    private final FirmRepository firmRepository;
    private final FirmConvertor firmConvertor;

    @Override
    @Transactional
    public void save(FirmSaveRequestDto firmDto) {
        validNewFirmOnDuplicateData(firmConvertor.convertSaveDtoToEntity(firmDto));
        firmRepository.save(firmConvertor.convertSaveDtoToEntity(firmDto));
        log.info("firm successfully saved by: " + firmDto.getName() + " name");
    }

    @Override
    @Transactional
    public void update(FirmUpdateRequestDto firmDto) {
        firmRepository.save(firmConvertor.convertUpdateDtoToEntity(firmDto));
        log.info("firm successfully updated by: " + firmDto.getName() + " name");
    }

    @Override
    @Transactional(readOnly = true)
    public FirmResponseByNameDto findByName(String name) {
        log.info("finding firm by: " + name + " name started");
        return firmConvertor
                .convertEntityToResponseDtoByName(firmRepository.findFirmByName(name)
                .orElseThrow(() -> new NoDataFoundException("firm not found by: " + name)));
    }

    @Override
    public FirmResponseByNameDto findByUuid(UUID uuid) {
        return firmConvertor
                .convertEntityToResponseDtoByName(firmRepository.findFirmByUuid(uuid)
                .orElseThrow(() -> new NoDataFoundException("firm not found")));
    }

    private void validNewFirmOnDuplicateData(Firm firm) {
        if (firmRepository.findFirmByName(firm.getName()).isPresent()) {
            log.warn("this firm by name: " + firm.getName() + " already exists");
            throw new DataValidException("firm already exists with same name");
        }
        if (firmRepository.findFirmByPhone(firm.getPhone()).isPresent()) {
            log.warn("this firm by phone number: " + firm.getPhone() + " already exists");
            throw new DataValidException("firm already exists with same phone number");
        }
    }
}
