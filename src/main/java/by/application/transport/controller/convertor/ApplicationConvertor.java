package by.application.transport.controller.convertor;

import by.application.transport.controller.dto.application.v1.V1ApplicationResponseDto;
import by.application.transport.controller.dto.application.v1.V1ApplicationSaveRequestDto;
import by.application.transport.controller.dto.application.v1.V1ApplicationUpdateRequestDto;
import by.application.transport.entity.Application;
import by.application.transport.entity.Firm;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Maksim Maksimovich
 */
@Component
public class ApplicationConvertor {
    private final ModelMapper modelMapper;

    public ApplicationConvertor() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(V1ApplicationSaveRequestDto.class, Application.class)
                .setPostConverter(convertSaveDtoToEntity());
        modelMapper.typeMap(V1ApplicationUpdateRequestDto.class, Application.class)
                .setPostConverter(convertUpdateDtoToEntity());
        modelMapper.typeMap(Application.class, V1ApplicationResponseDto.class)
                .setPostConverter(convertEntityToDtoResponse());
    }

    private Converter<Application, V1ApplicationResponseDto> convertEntityToDtoResponse() {
        return mappingContext -> {
            Application source = mappingContext.getSource();
            V1ApplicationResponseDto destination = mappingContext.getDestination();
            destination.setApplicationStatus(source.getApplicationStatus());
            destination.setId(source.getId());
            destination.setOrderPrice(source.getOrderPrice());
            destination.setOrderTime(source.getOrderTime().toLocalDate());
            destination.setCarId(source.getCarId());
            destination.setClientId(source.getClientId());
            destination.setFirmId(source.getFirm().getId());
            destination.setUserDriverId(source.getUserDriverId());
            return destination;
        };
    }


    private Converter<V1ApplicationUpdateRequestDto, Application> convertUpdateDtoToEntity() {
        return mappingContext -> {
            Application destination = mappingContext.getDestination();
            V1ApplicationUpdateRequestDto source = mappingContext.getSource();
            destination.setId(source.getId());
            destination.setApplicationStatus(source.getApplicationStatus());
            destination.setOrderPrice(source.getOrderPrice());
            Firm firm = new Firm();
            firm.setId(source.getFirmId());
            destination.setFirm(firm);
            destination.setUserDriverId(source.getUserDriverId());
            destination.setClientId(source.getClientId());
            destination.setCarId(source.getCarId());
            return destination;
        };
    }

    private Converter<V1ApplicationSaveRequestDto, Application> convertSaveDtoToEntity() {
        return mappingContext -> {
            Application destination = mappingContext.getDestination();
            V1ApplicationSaveRequestDto source = mappingContext.getSource();
            destination.setApplicationStatus(source.getApplicationStatus());
            destination.setOrderPrice(source.getOrderPrice());
            Firm firm = new Firm();
            firm.setId(source.getFirmId());
            destination.setFirm(firm);
            destination.setUserDriverId(source.getUserDriverId());
            destination.setClientId(source.getClientId());
            destination.setCarId(source.getCarId());
            return destination;
        };
    }

    public Application convertSaveDtoToEntity(V1ApplicationSaveRequestDto v1ApplicationSaveRequestDto) {
        return modelMapper.map(v1ApplicationSaveRequestDto, Application.class);
    }

    public Application convertUpdateDtoToEntity(V1ApplicationUpdateRequestDto v1ApplicationUpdateRequestDto) {
        return modelMapper.map(v1ApplicationUpdateRequestDto, Application.class);
    }

    public V1ApplicationResponseDto convertEntityToDtoResponse(Application application) {
        return modelMapper.map(application, V1ApplicationResponseDto.class);
    }
}
