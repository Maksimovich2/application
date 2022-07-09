package by.application.transport.controller;

import by.application.transport.controller.convertor.ApplicationConvertor;
import by.application.transport.controller.convertor.FirmConvertor;
import by.application.transport.controller.dto.application.ApplicationSaveDto;
import by.application.transport.controller.dto.firm.FirmResponseDto;
import by.application.transport.controller.dto.firm.FirmSaveDto;
import by.application.transport.controller.dto.firm.FirmUpdateDto;
import by.application.transport.service.ApplicationService;
import by.application.transport.service.FirmService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Maksim Maksimovich
 */
@RestController
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "firm-api")
@RequestMapping("/firm")
@Slf4j
public class FirmController {
    private final FirmService firmService;
    private final ApplicationService applicationService;
    private final ApplicationConvertor applicationConvertor;
    private final FirmConvertor firmConvertor;

    @PostMapping
    public ResponseEntity<Void> saveNewFirm(@RequestBody @Valid FirmSaveDto firmSaveDto) {
        firmService.save(firmConvertor.convertSaveDtoToEntity(firmSaveDto));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateInfoAboutFirm(@RequestBody @Valid FirmUpdateDto firmUpdateDto){
        firmService.update(firmConvertor.convertUpdateDtoToEntity(firmUpdateDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<FirmResponseDto> findByName(@RequestParam String firmName){
        return ResponseEntity.ok(firmConvertor
                .convertEntityToResponseDto(firmService
                        .findByName(firmName)));
    }

    @PostMapping("/create-new-application")
    public ResponseEntity<Void> createNewApplication(@RequestBody @Valid ApplicationSaveDto applicationSaveDto) {
        applicationService.save(applicationConvertor.convertSaveDtoToEntity(applicationSaveDto));
        return ResponseEntity.ok().build();
    }
}
