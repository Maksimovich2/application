package by.application.transport.service.convertor;

import by.application.transport.entity.Application;
import by.application.transport.entity.Firm;
import by.application.transport.service.dto.application.ApplicationResponseDto;
import by.application.transport.service.dto.application.ApplicationSaveRequestDto;
import by.application.transport.service.dto.application.ApplicationUpdateRequestDto;
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
        modelMapper.typeMap(ApplicationSaveRequestDto.class, Application.class)
                .setPostConverter(convertSaveDtoToEntity());
    }

    private Converter<ApplicationSaveRequestDto, Application> convertSaveDtoToEntity() {
        return mappingContext -> {
            Application destination = mappingContext.getDestination();
            ApplicationSaveRequestDto source = mappingContext.getSource();
            destination.setApplicationStatus(source.getApplicationStatus());
            Firm firm = new Firm();
            firm.setUuid(source.getFirmUuid());
            destination.setFirm(firm);
            destination.setClientId(source.getClientUuid());
            destination.setUserDriverId(source.getUserDriverUuid());
            destination.setCarId(source.getCarUuid());
            destination.setOrderPrice(source.getOrderPrice());
            return destination;
        };
    }

    public Application convertSaveDtoToEntity(ApplicationSaveRequestDto applicationDto){
        return modelMapper.map(applicationDto, Application.class);
    }

    public Application convertUpdateDtoToEntity(ApplicationUpdateRequestDto applicationDto){
        return modelMapper.map(applicationDto, Application.class);
    }

    public ApplicationResponseDto convertEntityToDtoResponse(Application application){
        return modelMapper.map(application, ApplicationResponseDto.class);
    }
}
