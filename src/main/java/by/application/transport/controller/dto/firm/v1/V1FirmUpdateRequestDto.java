package by.application.transport.controller.dto.firm.v1;

import by.application.transport.controller.dto.validate.Phone;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * @author Maksim Maksimovich
 */
@Data
public class V1FirmUpdateRequestDto {
    private UUID uuid;

    @NotBlank(message = "field name cannot be blank")
    @Size(min = 1)
    @NotEmpty(message = "field name cannot be empty")
    private String name;

    @NotBlank(message = "field phone cannot be blank")
    @NotEmpty(message = "field phone cannot be empty")
    @Phone(message = "phone should be start with: +375 and next have 9 numbers")
    private String phone;
}
