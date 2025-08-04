package in.trois.stockmanagement.utils;


import in.trois.stockmanagement.constants.ResultString;
import in.trois.stockmanagement.constants.response.ResultCode;
import in.trois.stockmanagement.response.AbstractResponse;
import in.trois.stockmanagement.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public class ResponseBuilder {

    private RestResponse<Object> restResponse;

    private ErrorResponse errorResponse;

    private HttpStatus status;

    public ResponseBuilder withData(Object data) {
        this.restResponse =
                new RestResponse<>(
                        ResultCode.COMM_OPERATION_SUCCESS, ResultString.COMM_OPERATION_SUCCESS);
        restResponse.setPayLoad(data);
        return this;
    }

    public ResponseBuilder success() {
        this.restResponse =
                new RestResponse<>(
                        ResultCode.COMM_OPERATION_SUCCESS, ResultString.COMM_OPERATION_SUCCESS);
        return this;
    }

    public ResponseBuilder withError(String errorMessage) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.restResponse = new RestResponse<>(ResultCode.COMM_OPERATION_FAILURE, errorMessage);
        return this;
    }

    public ResponseBuilder withError(HttpStatus httpStatus, String errorMessage) {
        this.status = httpStatus;
        String errMsg =
                switch (httpStatus) {
                    case BAD_REQUEST -> ResultString.INVALID_REQUEST;
                    case INTERNAL_SERVER_ERROR -> ResultString.INTERNAL_SERVER_ERROR;
                    default -> ResultString.COMM_OPERATION_FAILURE;
                };
        this.errorResponse = new ErrorResponse(httpStatus, errMsg, errorMessage);
        return this;
    }

    public ResponseBuilder withError(Integer errorCode, String errorMessage) {
        this.status = HttpStatus.UNAUTHORIZED;
        this.errorResponse = new ErrorResponse(errorCode, errorMessage);
        return this;
    }

    public ResponseBuilder withError(HttpStatus httpStatus, Set<String> errorMessage) {
        this.status = httpStatus;
        String errMsg =
                switch (httpStatus) {
                    case BAD_REQUEST -> ResultString.INVALID_REQUEST;
                    case INTERNAL_SERVER_ERROR -> ResultString.INTERNAL_SERVER_ERROR;
                    default -> ResultString.COMM_OPERATION_FAILURE;
                };
        this.errorResponse = new ErrorResponse(httpStatus, errMsg, errorMessage);
        return this;
    }

    public ResponseBuilder withError(HttpStatus httpStatus) {
        this.status = httpStatus;
        String errMsg =
                switch (httpStatus) {
                    case BAD_REQUEST -> ResultString.INVALID_REQUEST;
                    case INTERNAL_SERVER_ERROR -> ResultString.INTERNAL_SERVER_ERROR;
                    default -> ResultString.COMM_OPERATION_FAILURE;
                };
        this.errorResponse = new ErrorResponse(httpStatus, errMsg, errMsg);
        return this;
    }

    public ResponseEntity<AbstractResponse> build() {
        return restResponse != null
                ? ResponseEntity.ok(restResponse)
                : ResponseEntity.status(status).body(errorResponse);
    }
}
