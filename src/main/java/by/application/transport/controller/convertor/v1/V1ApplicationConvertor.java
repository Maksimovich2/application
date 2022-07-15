package by.application.transport.controller.convertor.v1;

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
public class V1ApplicationConvertor {
    private final ModelMapper modelMapper;

    public V1ApplicationConvertor() {
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
            destination.setUuid(source.getUuid());
            destination.setOrderPrice(source.getOrderPrice());
            destination.setOrderTime(source.getOrderTime().toLocalDate());
            destination.setCarUuid(source.getCarId());
            destination.setClientUuid(source.getClientId());
            destination.setFirmUuid(source.getFirm().getUuid());
            destination.setUserDriverUuid(source.getUserDriverId());
            return destination;
        };
    }


    private Converter<V1ApplicationUpdateRequestDto, Application> convertUpdateDtoToEntity() {
        return mappingContext -> {
            Application destination = mappingContext.getDestination();
            V1ApplicationUpdateRequestDto source = mappingContext.getSource();
            destination.setUuid(source.getId());
            destination.setApplicationStatus(source.getApplicationStatus());
            destination.setOrderPrice(source.getOrderPrice());
            Firm firm = new Firm();
            firm.setUuid(source.getFirmUuid());
            destination.setFirm(firm);
            destination.setUserDriverId(source.getUserDriverUuid());
            destination.setClientId(source.getClientUuid());
            destination.setCarId(source.getCarUuid());
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
            firm.setUuid(source.getFirmUuid());
            destination.setFirm(firm);
            destination.setUserDriverId(source.getUserDriverUuid());
            destination.setClientId(source.getClientUuid());
            destination.setCarId(source.getCarUuid());
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
