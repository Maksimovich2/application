package by.application.transport.controller.v1;

import by.application.transport.controller.convertor.ApplicationConvertor;
import by.application.transport.controller.dto.application.v1.V1ApplicationFindByDateRequestDto;
import by.application.transport.controller.dto.application.v1.V1ApplicationResponseDto;
import by.application.transport.controller.dto.application.v1.V1ApplicationSaveRequestDto;
import by.application.transport.controller.dto.application.v1.V1ApplicationUpdateRequestDto;
import by.application.transport.service.ApplicationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maksim Maksimovich
 */
@RestController
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "application-api")
@RequestMapping("/applications")
@Slf4j
public class V1ApplicationController {
    private final ApplicationService applicationService;
    private final ApplicationConvertor applicationConvertor;

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid V1ApplicationUpdateRequestDto v1ApplicationUpdateRequestDto) {
        applicationService.update(applicationConvertor.convertUpdateDtoToEntity(v1ApplicationUpdateRequestDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-by-date")
    public ResponseEntity<List<V1ApplicationResponseDto>> findByDate(@RequestBody @Valid V1ApplicationFindByDateRequestDto dto) {
        return ResponseEntity.ok(applicationService
                .findByDate(dto.getStartDate(), dto.getFinishDate())
                .stream()
                .map(applicationConvertor::convertEntityToDtoResponse)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Void> createNewApplication(@RequestBody @Valid V1ApplicationSaveRequestDto v1ApplicationSaveRequestDto) {
        applicationService.save(applicationConvertor.convertSaveDtoToEntity(v1ApplicationSaveRequestDto));
        return ResponseEntity.ok().build();
    }
}
