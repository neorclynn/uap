package uap.validator.sys;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import uap.domain.sys.User;

public class UserValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty", "Username is required");
    }
}
