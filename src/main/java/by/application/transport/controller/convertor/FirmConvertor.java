package by.application.transport.controller.convertor;

import by.application.transport.controller.dto.firm.FirmResponseDto;
import by.application.transport.controller.dto.firm.FirmSaveDto;
import by.application.transport.controller.dto.firm.FirmUpdateDto;
import by.application.transport.entity.Firm;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author Maksim Maksimovich
 */
@Component
public class FirmConvertor {
    private final ModelMapper modelMapper;

    @Autowired
    private ApplicationConvertor applicationConvertor;

    public FirmConvertor() {
        modelMapper = new ModelMapper();
        modelMapper.typeMap(FirmSaveDto.class, Firm.class)
                .setPostConverter(convertSaveDtoToEntity());
        modelMapper.typeMap(FirmUpdateDto.class, Firm.class)
                .setPostConverter(convertUpdateDtoToEntity());
        modelMapper.typeMap(Firm.class, FirmResponseDto.class)
                .setPostConverter(convertEntityToResponseDto());
    }

    private Converter<Firm, FirmResponseDto> convertEntityToResponseDto() {
        return mappingContext -> {
            FirmResponseDto destination = mappingContext.getDestination();
            Firm source = mappingContext.getSource();
            destination.setId(source.getId());
            destination.setName(source.getName());
            destination.setPhone(source.getPhone());
            destination.setApplications(source.getApplications()
                    .stream()
                    .map(applicationConvertor::convertEntityToDtoResponse)
                    .collect(Collectors.toList()));
            return destination;
        };
    }

    private Converter<FirmUpdateDto, Firm> convertUpdateDtoToEntity() {
        return mappingContext -> {
            FirmUpdateDto source = mappingContext.getSource();
            Firm destination = mappingContext.getDestination();
            destination.setId(source.getId());
            destination.setName(source.getName());
            destination.setPhone(source.getPhone());
            return destination;
        };
    }

    private Converter<FirmSaveDto, Firm> convertSaveDtoToEntity() {
        return mappingContext -> {
            Firm destination = mappingContext.getDestination();
            FirmSaveDto source = mappingContext.getSource();
            destination.setName(source.getName());
            destination.setPhone(source.getPhone());
            return destination;
        };
    }

    public Firm convertSaveDtoToEntity(FirmSaveDto firmSaveDto){
        return modelMapper.map(firmSaveDto, Firm.class);
    }

    public Firm convertUpdateDtoToEntity(FirmUpdateDto firmUpdateDto){
        return modelMapper.map(firmUpdateDto, Firm.class);
    }

    public FirmResponseDto convertEntityToResponseDto(Firm firm){
        return modelMapper.map(firm, FirmResponseDto.class);
    }
}
