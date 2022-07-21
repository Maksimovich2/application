package by.application.transport.controller.v1;

import by.application.transport.controller.convertor.v1.V1ApplicationConvertor;
import by.application.transport.controller.dto.application.v1.V1ApplicationResponseDto;
import by.application.transport.controller.dto.application.v1.V1ApplicationSaveRequestDto;
import by.application.transport.controller.dto.application.v1.V1ApplicationUpdateRequestDto;
import by.application.transport.service.ApplicationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
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
    private final V1ApplicationConvertor v1ApplicationConvertor;

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid V1ApplicationUpdateRequestDto v1ApplicationUpdateRequestDto) {
        applicationService.update(v1ApplicationConvertor
                .convertUpdateDtoToServiceUpdateDto(v1ApplicationUpdateRequestDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<V1ApplicationResponseDto>> findByDate(
            @RequestParam("startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("finishDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finishDate) {
        return ResponseEntity.ok(applicationService
                .findByDate(startDate, finishDate)
                .stream()
                .map(v1ApplicationConvertor::convertServiceResponseDtoToResponseDto)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Void> createNewApplication(@RequestBody @Valid V1ApplicationSaveRequestDto v1ApplicationSaveRequestDto) {
        applicationService.save(v1ApplicationConvertor
                .convertSaveDtoToServiceSaveDto(v1ApplicationSaveRequestDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<V1ApplicationResponseDto> findByUuid(@PathVariable UUID uuid){
        return ResponseEntity.ok(v1ApplicationConvertor
                .convertServiceResponseDtoToResponseDto(applicationService.findByUuid(uuid)));
    }
}
