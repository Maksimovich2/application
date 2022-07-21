package by.application.transport.controller.v1;

import by.application.transport.controller.convertor.v1.V1FirmConvertor;
import by.application.transport.controller.dto.firm.v1.V1FirmResponseByNameDto;
import by.application.transport.controller.dto.firm.v1.V1FirmSaveRequestDtoV1;
import by.application.transport.controller.dto.firm.v1.V1FirmUpdateRequestDto;
import by.application.transport.service.FirmService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
@RestController
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "firm-api")
@RequestMapping("/firms")
@Slf4j
public class V1FirmController {
    private final FirmService firmService;
    private final V1FirmConvertor v1FirmConvertor;

    @PostMapping
    public ResponseEntity<Void> saveNewFirm(@RequestBody @Valid V1FirmSaveRequestDtoV1 v1FirmSaveRequestDto) {
        firmService.save(v1FirmConvertor
                .convertSaveDtoToServiceDto(v1FirmSaveRequestDto));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateInfoAboutFirm(@RequestBody @Valid V1FirmUpdateRequestDto v1FirmUpdateRequestDto){
        firmService.update(v1FirmConvertor
                .convertUpdateDtoToServiceDto(v1FirmUpdateRequestDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<V1FirmResponseByNameDto> findByName(@RequestParam String firmName){
        return ResponseEntity.ok(v1FirmConvertor
                .convertEntityToResponseByNameDto(firmService
                        .findByName(firmName)));
    }

    @GetMapping ("/{uuid}")
    public ResponseEntity<V1FirmResponseByNameDto> findByUuid(@PathVariable UUID uuid){
        return ResponseEntity.ok(v1FirmConvertor
                .convertEntityToResponseByNameDto(firmService.findByUuid(uuid)));
    }
}
