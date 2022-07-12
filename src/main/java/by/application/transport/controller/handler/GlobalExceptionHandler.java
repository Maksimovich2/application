package by.application.transport.controller.handler;

import by.application.transport.controller.handler.dto.ApplicationErrorDetailsResponseDto;
import by.application.transport.controller.handler.dto.CustomExceptionResponseDto;
import by.application.transport.exception.DataValidException;
import by.application.transport.exception.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maksim Maksimovich
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomExceptionResponseDto> handleDataValidException(DataValidException dataValidException) {
        return createResponseForCustomException(HttpStatus.BAD_REQUEST, dataValidException.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<CustomExceptionResponseDto> handleNoDataFoundException(NoDataFoundException noDataFoundException) {
        return createResponseForCustomException(HttpStatus.NOT_FOUND, noDataFoundException.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        log.error(ex.getLocalizedMessage());
        ApplicationErrorDetailsResponseDto errorDetails = new ApplicationErrorDetailsResponseDto(HttpStatus.BAD_REQUEST, System.currentTimeMillis(), errorList);
        return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);
    }

    private ResponseEntity<CustomExceptionResponseDto> createResponseForCustomException(HttpStatus httpStatus, String message) {
        CustomExceptionResponseDto customExceptionResponseDto = new CustomExceptionResponseDto();
        customExceptionResponseDto.setStatus(httpStatus);
        customExceptionResponseDto.setMessage(message);
        customExceptionResponseDto.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(customExceptionResponseDto, httpStatus);
    }

}
