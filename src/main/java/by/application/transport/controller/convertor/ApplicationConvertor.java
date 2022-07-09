package by.application.transport.controller.convertor;

import by.application.transport.controller.dto.application.ApplicationResponseDto;
import by.application.transport.controller.dto.application.ApplicationSaveDto;
import by.application.transport.controller.dto.application.ApplicationUpdateDto;
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
        modelMapper.typeMap(ApplicationSaveDto.class, Application.class)
                .setPostConverter(convertSaveDtoToEntity());
        modelMapper.typeMap(ApplicationUpdateDto.class, Application.class)
                .setPostConverter(convertUpdateDtoToEntity());
    }


    private Converter<ApplicationUpdateDto, Application> convertUpdateDtoToEntity() {
        return mappingContext -> {
            Application destination = mappingContext.getDestination();
            ApplicationUpdateDto source = mappingContext.getSource();
            destination.setId(source.getId());
            destination.setApplicationStatus(source.getApplicationStatus());
            destination.setOrderPrice(source.getOrderPrice());
            destination.setOrderTime(source.getOrderTime());
            Firm firm = new Firm();
            firm.setId(source.getFirmId());
            destination.setFirm(firm);
            destination.setUserDriverId(source.getUserDriverId());
            destination.setClientId(source.getClientId());
            destination.setCarId(source.getCarId());
            return destination;
        };
    }

    private Converter<ApplicationSaveDto, Application> convertSaveDtoToEntity() {
        return mappingContext -> {
            Application destination = mappingContext.getDestination();
            ApplicationSaveDto source = mappingContext.getSource();
            destination.setApplicationStatus(source.getApplicationStatus());
            destination.setOrderPrice(source.getOrderPrice());
            destination.setOrderTime(source.getOrderTime());
            Firm firm = new Firm();
            firm.setId(source.getFirmId());
            destination.setFirm(firm);
            destination.setUserDriverId(source.getUserDriverId());
            destination.setClientId(source.getClientId());
            destination.setCarId(source.getCarId());
            return destination;
        };
    }

    public Application convertSaveDtoToEntity(ApplicationSaveDto applicationSaveDto) {
        return modelMapper.map(applicationSaveDto, Application.class);
    }

    public Application convertUpdateDtoToEntity(ApplicationUpdateDto applicationUpdateDto) {
        return modelMapper.map(applicationUpdateDto, Application.class);
    }

    public ApplicationResponseDto convertEntityToDtoResponse(Application application) {
        return modelMapper.map(application, ApplicationResponseDto.class);
    }
}
