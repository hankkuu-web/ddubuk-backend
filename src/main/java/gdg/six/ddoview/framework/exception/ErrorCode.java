package gdg.six.ddoview.framework.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    INVALID_PARAMETER(400, "COMMON_001", "Invalid parameters.")
    , INTERNAL_SERVER_ERROR(500, "COMMON_002", "Internal server Error. Please try again later.")
    , INVALID_TOKEN_ERROR(400, "COMMON_003", "Token is invalid.")
    , TOKEN_EXPIRED_ERROR(403, "COMMON_004", "Token is expired.")
    , TOKEN_NOT_FOUND_ERROR(400, "COMMON_005", "Token is not found.")
    , PASSWORD_NOT_MATCHED(403, "COMMON_006", "Password not matched.")
    , API_KEY_NOT_FOUND(404, "COMMON_007", "API Key is not found.")
    , INVALID_API_KEY(400, "COMMON_008", "API Key is required.");


    private final int status;
    private final String code;
    private final String message;

    public int getStatus() {
        return this.status;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.resolve(this.status);
    }

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

}
