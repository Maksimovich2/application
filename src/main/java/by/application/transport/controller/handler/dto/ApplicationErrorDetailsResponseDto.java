package by.application.transport.controller.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author Maksim Maksimovich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationErrorDetailsResponseDto {
    private HttpStatus status;
    private long timestamp;
    private List<String> errors;
}
