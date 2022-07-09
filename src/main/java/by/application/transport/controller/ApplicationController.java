package by.application.transport.controller;

import by.application.transport.controller.convertor.ApplicationConvertor;
import by.application.transport.controller.dto.application.ApplicationFindByDateRequestDto;
import by.application.transport.controller.dto.application.ApplicationResponseDto;
import by.application.transport.controller.dto.application.ApplicationUpdateDto;
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
@RequestMapping("/application")
@Slf4j
public class ApplicationController {
    private final ApplicationService applicationService;
    private final ApplicationConvertor applicationConvertor;

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid ApplicationUpdateDto applicationUpdateDto) {
        applicationService.update(applicationConvertor.convertUpdateDtoToEntity(applicationUpdateDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-by-date")
    public ResponseEntity<List<ApplicationResponseDto>> findByDate(@RequestBody @Valid ApplicationFindByDateRequestDto dto) {
        return ResponseEntity.ok(applicationService
                .findByDate(dto.getStartDate(), dto.getFinishDate())
                .stream()
                .map(applicationConvertor::convertEntityToDtoResponse)
                .collect(Collectors.toList()));
    }
}
