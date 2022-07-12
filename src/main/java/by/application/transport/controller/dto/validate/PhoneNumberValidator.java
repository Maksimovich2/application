package by.application.transport.controller.dto.validate;

import by.application.transport.exception.DataValidException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Maksim Maksimovich
 */
public class PhoneNumberValidator implements ConstraintValidator<Phone, String> {

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        if (contactField != null && contactField.matches("(\\+375)[0-9]{9}")){
            return true;
        }
        throw new DataValidException(cxt.getDefaultConstraintMessageTemplate());
    }
}
