package by.application.transport.controller.convertor.v1;

import by.application.transport.controller.dto.firm.v1.V1FirmResponseByNameDto;
import by.application.transport.controller.dto.firm.v1.V1FirmResponseDto;
import by.application.transport.controller.dto.firm.v1.V1FirmSaveRequestDtoV1;
import by.application.transport.controller.dto.firm.v1.V1FirmUpdateRequestDto;
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
public class V1FirmConvertor {
    private final ModelMapper modelMapper;

    @Autowired
    private V1ApplicationConvertor v1ApplicationConvertor;

    public V1FirmConvertor() {
        modelMapper = new ModelMapper();
        modelMapper.typeMap(Firm.class, V1FirmResponseDto.class)
                .setPostConverter(convertEntityToResponseDto());
    }

    private Converter<Firm, V1FirmResponseDto> convertEntityToResponseDto() {
        return mappingContext -> {
            V1FirmResponseDto destination = mappingContext.getDestination();
            Firm source = mappingContext.getSource();
            destination.setUuid(source.getUuid());
            destination.setName(source.getName());
            destination.setPhone(source.getPhone());
            destination.setApplications(source.getApplications()
                    .stream()
                    .map(v1ApplicationConvertor::convertEntityToDtoResponse)
                    .collect(Collectors.toList()));
            return destination;
        };
    }

    public Firm convertSaveDtoToEntity(V1FirmSaveRequestDtoV1 v1FirmSaveRequestDtoV1){
        return modelMapper.map(v1FirmSaveRequestDtoV1, Firm.class);
    }

    public Firm convertUpdateDtoToEntity(V1FirmUpdateRequestDto v1FirmUpdateRequestDto){
        return modelMapper.map(v1FirmUpdateRequestDto, Firm.class);
    }

    public V1FirmResponseDto convertEntityToResponseDto(Firm firm){
        return modelMapper.map(firm, V1FirmResponseDto.class);
    }

    public V1FirmResponseByNameDto convertEntityToResponseByNameDto(Firm firm){
        return modelMapper.map(firm, V1FirmResponseByNameDto.class);
    }
}
