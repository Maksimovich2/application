package by.application.transport.controller.convertor.v1;

import by.application.transport.controller.dto.application.v1.V1ApplicationResponseDto;
import by.application.transport.controller.dto.application.v1.V1ApplicationSaveRequestDto;
import by.application.transport.controller.dto.application.v1.V1ApplicationUpdateRequestDto;
import by.application.transport.service.dto.application.ApplicationResponseDto;
import by.application.transport.service.dto.application.ApplicationSaveRequestDto;
import by.application.transport.service.dto.application.ApplicationUpdateRequestDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Maksim Maksimovich
 */
@Component
public class V1ApplicationConvertor {
    private final ModelMapper modelMapper;

    public V1ApplicationConvertor() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    public ApplicationSaveRequestDto convertSaveDtoToServiceSaveDto(V1ApplicationSaveRequestDto v1ApplicationSaveRequestDto) {
        return modelMapper.map(v1ApplicationSaveRequestDto, ApplicationSaveRequestDto.class);
    }

    public ApplicationUpdateRequestDto convertUpdateDtoToServiceUpdateDto(V1ApplicationUpdateRequestDto v1ApplicationUpdateRequestDto) {
        return modelMapper.map(v1ApplicationUpdateRequestDto, ApplicationUpdateRequestDto.class);
    }

    public V1ApplicationResponseDto convertServiceResponseDtoToResponseDto(ApplicationResponseDto application) {
        return modelMapper.map(application, V1ApplicationResponseDto.class);
    }
}
