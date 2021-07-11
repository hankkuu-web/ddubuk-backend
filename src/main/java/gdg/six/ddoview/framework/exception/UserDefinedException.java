package gdg.six.ddoview.framework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.ClassUtils;

public class UserDefinedException extends RuntimeException{

    private final ErrorCode errorCode;

    public UserDefinedException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getShortName() {
        return ClassUtils.getShortName(getClass());
    }

    public HttpStatus getHttpStatus() {
        return this.errorCode.getHttpStatus();
    }
}
