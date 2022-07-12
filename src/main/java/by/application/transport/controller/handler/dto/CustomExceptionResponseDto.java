package by.application.transport.controller.handler.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Maksim Maksimovich
 */
@Data
public class CustomExceptionResponseDto {
    private HttpStatus status;
    private String message;
    private long timestamp;
}
