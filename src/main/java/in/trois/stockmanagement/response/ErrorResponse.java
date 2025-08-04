package in.trois.stockmanagement.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import in.trois.stockmanagement.constants.ResultString;
import in.trois.stockmanagement.constants.response.ResultCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends AbstractResponse implements Serializable {
    private HttpStatus status;
    private Set<String> errors;
    private Object[] args;
    private Object additionalData;
    private Integer errorCode;

    public ErrorResponse() {
        super(ResultCode.COMM_OPERATION_FAILURE, ResultString.COMM_OPERATION_FAILURE);
    }

    public ErrorResponse(HttpStatus status, String message, String error) {
        super(ResultCode.COMM_OPERATION_FAILURE, message);
        this.status = status;
        errors = new HashSet<>(Arrays.asList(error));
    }

    public ErrorResponse(ResultCode resultCode, HttpStatus status, String message, String error) {
        super(resultCode, message);
        this.status = status;
        errors = new HashSet<>(Arrays.asList(error));
    }

    public ErrorResponse(HttpStatus status, String message) {
        super(ResultCode.COMM_OPERATION_FAILURE, message);
        this.status = status;
    }

    public ErrorResponse(Integer errorCode, String message) {
        super(ResultCode.COMM_OPERATION_FAILURE, message);
        this.errorCode = errorCode;
    }

    public ErrorResponse(HttpStatus status, String message, Set<String> errors) {
        super(ResultCode.COMM_OPERATION_FAILURE, message);
        this.status = status;
        this.errors = errors;
    }

    public ErrorResponse(HttpStatus status, String message, Set<String> errors, Exception ex) {
        super(ResultCode.COMM_OPERATION_FAILURE, message);
        this.status = status;
        this.errors = errors;
    }
}
