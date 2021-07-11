package gdg.six.ddoview.framework;

import gdg.six.ddoview.framework.exception.ErrorResponse;
import gdg.six.ddoview.framework.exception.InvalidParameterException;
import gdg.six.ddoview.framework.exception.UserDefinedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class CommonExceptionHandler extends DefaultHandlerExceptionResolver {

    @ExceptionHandler(UserDefinedException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(HttpServletRequest request, UserDefinedException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception, request);
        log.error(errorResponse.toString());
        return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ErrorResponse> handleInvalidParameterException(HttpServletRequest request, InvalidParameterException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception, request);
        errorResponse.errors(exception.getErrors());
        log.error(errorResponse.toString());
        return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler(ServletException.class)
    public void handleServletException(HttpServletRequest request, HttpServletResponse response, ServletException exception) {
        super.doResolveException(request, response, null, exception);
        log.error(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException exception) {
        return toResponseEntity(HttpStatus.BAD_REQUEST, exception, request);
    }

    private ResponseEntity<ErrorResponse> toResponseEntity(HttpStatus httpStatus, Exception exception, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus, exception.getMessage(), request.getRequestURI());
        log.error(errorResponse.toString());
        return new ResponseEntity<>(errorResponse, httpStatus);
    }


}
