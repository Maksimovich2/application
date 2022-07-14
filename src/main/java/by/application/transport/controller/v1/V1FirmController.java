package by.application.transport.controller.v1;

import by.application.transport.controller.convertor.FirmConvertor;
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
    private final FirmConvertor firmConvertor;

    @PostMapping
    public ResponseEntity<Void> saveNewFirm(@RequestBody @Valid V1FirmSaveRequestDtoV1 v1FirmSaveRequestDtoV1) {
        firmService.save(firmConvertor
                .convertSaveDtoToEntity(v1FirmSaveRequestDtoV1));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateInfoAboutFirm(@RequestBody @Valid V1FirmUpdateRequestDto v1FirmUpdateRequestDto){
        firmService.update(firmConvertor
                .convertUpdateDtoToEntity(v1FirmUpdateRequestDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<V1FirmResponseByNameDto> findByName(@RequestParam String firmName){
        return ResponseEntity.ok(firmConvertor
                .convertEntityToResponseByNameDto(firmService
                        .findByName(firmName)));
    }

    @GetMapping ("/find-by-uuid")
    public ResponseEntity<V1FirmResponseByNameDto> findByUuid(@RequestParam UUID uuid){
        return ResponseEntity.ok(firmConvertor
                .convertEntityToResponseByNameDto(firmService.findByUuid(uuid)));
    }
}
