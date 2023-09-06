package ss5.th.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class Tel implements Validator {
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Tel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Tel tel = (Tel) target;
        String number = tel.getNumber();
        if( number.equals("")) {
            errors.rejectValue("number", "validate.empty");
        } else if (!number.startsWith("0")){
            errors.rejectValue("number", "validate.startsWith");
        } else if (number.length()>11 || number.length()<10){
            errors.rejectValue("number", "validate.length");
        } else if (!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("number", "validate.matches");
        }
    }
}

