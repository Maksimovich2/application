package by.application.transport.controller.dto.firm.v1;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Maksim Maksimovich
 */
@Data
public class V1FirmUpdateRequestDto {
    private Long id;

    @NotBlank(message = "field name cannot be blank")
    @Size(min = 1)
    @NotEmpty(message = "field name cannot be empty")
    private String name;

    @NotBlank(message = "field phone cannot be blank")
    @NotEmpty(message = "field phone cannot be empty")
    private String phone;
}
