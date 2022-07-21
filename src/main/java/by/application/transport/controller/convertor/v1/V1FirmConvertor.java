package by.application.transport.controller.convertor.v1;

import by.application.transport.controller.dto.firm.v1.V1FirmResponseByNameDto;
import by.application.transport.controller.dto.firm.v1.V1FirmResponseDto;
import by.application.transport.controller.dto.firm.v1.V1FirmSaveRequestDtoV1;
import by.application.transport.controller.dto.firm.v1.V1FirmUpdateRequestDto;
import by.application.transport.service.dto.firm.FirmResponseByNameDto;
import by.application.transport.service.dto.firm.FirmResponseDto;
import by.application.transport.service.dto.firm.FirmSaveRequestDto;
import by.application.transport.service.dto.firm.FirmUpdateRequestDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author Maksim Maksimovich
 */
@Component
public class V1FirmConvertor {
    private final ModelMapper modelMapper;

    @Autowired
    private V1ApplicationConvertor v1ApplicationConvertor;

    public V1FirmConvertor() {
        modelMapper = new ModelMapper();
        modelMapper.typeMap(FirmResponseDto.class, V1FirmResponseDto.class)
                .setPostConverter(convertServiceDtoToResponseDto());
    }

    private Converter<FirmResponseDto, V1FirmResponseDto> convertServiceDtoToResponseDto() {
        return mappingContext -> {
            V1FirmResponseDto destination = mappingContext.getDestination();
            FirmResponseDto source = mappingContext.getSource();
            destination.setUuid(source.getUuid());
            destination.setName(source.getName());
            destination.setPhone(source.getPhone());
            destination.setApplications(source.getApplications()
                    .stream()
                    .map(v1ApplicationConvertor::convertServiceResponseDtoToResponseDto)
                    .collect(Collectors.toList()));
            return destination;
        };
    }

    public FirmSaveRequestDto convertSaveDtoToServiceDto(V1FirmSaveRequestDtoV1 v1FirmSaveRequestDtoV1){
        return modelMapper.map(v1FirmSaveRequestDtoV1, FirmSaveRequestDto.class);
    }

    public FirmUpdateRequestDto convertUpdateDtoToServiceDto(V1FirmUpdateRequestDto v1FirmUpdateRequestDto){
        return modelMapper.map(v1FirmUpdateRequestDto, FirmUpdateRequestDto.class);
    }

    public V1FirmResponseDto convertServiceDtoToResponseDto(FirmResponseDto firmDto){
        return modelMapper.map(firmDto, V1FirmResponseDto.class);
    }

    public V1FirmResponseByNameDto convertEntityToResponseByNameDto(FirmResponseByNameDto firmDto){
        return modelMapper.map(firmDto, V1FirmResponseByNameDto.class);
    }
}
