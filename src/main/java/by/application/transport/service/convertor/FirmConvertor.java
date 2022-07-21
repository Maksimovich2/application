package by.application.transport.service.convertor;

import by.application.transport.entity.Firm;
import by.application.transport.service.dto.firm.FirmResponseByNameDto;
import by.application.transport.service.dto.firm.FirmResponseDto;
import by.application.transport.service.dto.firm.FirmSaveRequestDto;
import by.application.transport.service.dto.firm.FirmUpdateRequestDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Maksim Maksimovich
 */
@Component
public class FirmConvertor {
    private final ModelMapper modelMapper;

    public FirmConvertor() {
        modelMapper = new ModelMapper();
    }

    public Firm convertSaveDtoToEntity(FirmSaveRequestDto firmDto) {
        return modelMapper.map(firmDto, Firm.class);
    }

    public Firm convertUpdateDtoToEntity(FirmUpdateRequestDto firmDto) {
        return modelMapper.map(firmDto, Firm.class);
    }

    public FirmResponseDto convertEntityToResponseDto(Firm firm) {
        return modelMapper.map(firm, FirmResponseDto.class);
    }

    public FirmResponseByNameDto convertEntityToResponseDtoByName(Firm firm){
        return modelMapper.map(firm, FirmResponseByNameDto.class);
    }

    public Firm convertServiceDtoFindByNameToEntity(FirmResponseByNameDto responseDto){
        return modelMapper.map(responseDto, Firm.class);
    }
}
