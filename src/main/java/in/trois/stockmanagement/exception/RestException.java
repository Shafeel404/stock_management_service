package in.trois.stockmanagement.exception;


import in.trois.stockmanagement.constants.response.ResultCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class RestException extends RuntimeException implements Serializable {

    private String loggerMessage;

    private Object[] args;

    private Object additionalData;

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    private Integer errorCode;

    private ResultCode resultCode;

    public RestException(String loggerMessage) {
        super(loggerMessage);
    }

    public RestException(String loggerMessage, Object[] args) {
        super(loggerMessage);
        this.args = args;
    }

    public RestException(String loggerMessage, Object additionalData) {
        super(loggerMessage);
        this.additionalData = additionalData;
    }

    public RestException(String loggerMessage, HttpStatus status) {
        super(loggerMessage);
        this.status = status;
    }

    public RestException(ResultCode resultCode, HttpStatus status, String loggerMessage) {
        super(loggerMessage);
        this.resultCode = resultCode;
        this.status = status;
    }

    public RestException(String loggerMessage, HttpStatus status, Integer errorCode) {
        super(loggerMessage);
        this.status = status;
        this.errorCode = errorCode;
    }

    public RestException(String loggerMessage, Object[] args, HttpStatus status) {
        super(loggerMessage);
        this.args = args;
        this.status = status;
    }

    public RestException(String loggerMessage, Object additionalData, HttpStatus status) {
        super(loggerMessage);
        this.additionalData = additionalData;
        this.status = status;
    }

    public RestException(String loggerMessage, Throwable ex, HttpStatus status) {
        super(loggerMessage, ex);
        this.status = status;
    }

    public RestException(String loggerMessage, Throwable ex) {
        super(loggerMessage, ex);
    }

    public RestException(Throwable ex) {
        super(ex);
    }
}
