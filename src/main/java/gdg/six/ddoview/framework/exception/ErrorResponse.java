package gdg.six.ddoview.framework.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class ErrorResponse {

    private String timestamp;
    private int status;
    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    private String message;
    private String path;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("errors")
    private List<FieldValidationError> fieldValidationErrors = null;

    private String stacktrace;

    public ErrorResponse() {
        this.timestamp = LocalDateTime.now().toString();
    }

    public ErrorResponse(ErrorCode errorCode, String path, Throwable ex) {
        this.timestamp = LocalDateTime.now().toString();
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.path = path;

        String[] stack = ExceptionUtils.getRootCauseStackTrace(ex);
        this.stacktrace = StringUtils.join(stack, "\n", 0, 5);
    }

    public ErrorResponse(UserDefinedException userDefinedException, HttpServletRequest httpServletRequest) {
        this(userDefinedException.getErrorCode(), httpServletRequest.getRequestURI(), userDefinedException);
        this.error(userDefinedException.getShortName());
    }

    public ErrorResponse(HttpStatus httpStatus, String message, String path) {
        this.timestamp = LocalDateTime.now().toString();
        this.code = null;
        this.status = httpStatus.value();
        this.message = message;
        this.path = path;
    }

//    private ErrorResponse(ErrorCode errorCode, String path, Throwable ex) {
//        this.timestamp = LocalDateTime.now().toString();
//        this.code = errorCode.getCode();
//        this.status = errorCode.getStatus();
//        this.message = errorCode.getMessage();
//        this.path = path;
//
//        String[] stack = ExceptionUtils.getRootCauseStackTrace(ex);
//        this.stacktrace = StringUtils.join(stack, "\n", 0, 5);
//    }

    public static ErrorResponse of(ErrorCode errorCode, String path, Throwable ex) {
        return new ErrorResponse(errorCode, path, ex);
    }

    public ErrorResponse code(String code) {
        this.code = code;
        return this;
    }

    public ErrorResponse status(int status) {
        this.status = status;
        return this;
    }

    public ErrorResponse message(String message) {
        this.message = message;
        return this;
    }

    public ErrorResponse error(String error) {
        this.error = error;
        return this;
    }

    public ErrorResponse errors(Errors errors) {
        setFieldValidationErrors(errors.getFieldErrors());
        return this;
    }

    public ErrorResponse path(String path) {
        this.path = path;
        return this;
    }

    private void setFieldValidationErrors(List<FieldError> fieldErrors) {
        this.fieldValidationErrors = new ArrayList<>();
        fieldErrors.stream()
                .map(FieldValidationError::new)
                .forEach(this.fieldValidationErrors::add);
    }

    @Getter
    public static class FieldValidationError {
        private String field;
        private Object value;
        private String reason;

        public FieldValidationError(FieldError fieldError) {
            this.field = fieldError.getField();
            this.value = fieldError.getRejectedValue();
            this.reason = fieldError.getDefaultMessage();
        }

    }


}

