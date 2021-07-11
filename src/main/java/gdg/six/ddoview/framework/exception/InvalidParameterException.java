package gdg.six.ddoview.framework.exception;

import org.springframework.validation.Errors;

public class InvalidParameterException extends UserDefinedException {

    private final Errors errors;

    public InvalidParameterException(Errors errors) {
        super(ErrorCode.INVALID_PARAMETER);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }


}
