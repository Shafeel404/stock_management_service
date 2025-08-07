package in.trois.stockmanagement.exception;


import in.trois.stockmanagement.constants.logging.LogState;
import in.trois.stockmanagement.constants.response.ResultCode;
import in.trois.stockmanagement.logging.LogStringBuilder;
import in.trois.stockmanagement.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
@Hidden
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("RestExceptionHandler.handleMethodArgumentNotValid", ex);
        Set<String> errors = new HashSet<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(
                ex, errorResponse, headers, errorResponse.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("RestExceptionHandler.handleTypeMismatch", ex);
        String error =
                ex.getValue()
                        + " value for "
                        + ex.getPropertyName()
                        + " should be of type "
                        + ex.getRequiredType();
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(errorResponse, headers, errorResponse.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            MissingServletRequestPartException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("RestExceptionHandler.handleMissingServletRequestPart", ex);
        String error = ex.getRequestPartName() + " part is missing";
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(errorResponse, headers, errorResponse.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("RestExceptionHandler.handleMissingServletRequestParameter", ex);
        String error = ex.getParameterName() + " parameter is missing";
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(errorResponse, headers, errorResponse.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("RestExceptionHandler.handleNoHandlerFoundException", ex);
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(errorResponse, headers, errorResponse.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("RestExceptionHandler.handleHttpRequestMethodNotSupported", ex);
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
        ErrorResponse errorResponse =
                new ErrorResponse(
                        HttpStatus.METHOD_NOT_ALLOWED,
                        ex.getLocalizedMessage(),
                        builder.toString());
        return new ResponseEntity<>(errorResponse, headers, errorResponse.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            final WebRequest request) {
        log.error("RestExceptionHandler.handleHttpMediaTypeNotSupported", ex);
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));
        ErrorResponse errorResponse =
                new ErrorResponse(
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                        ex.getLocalizedMessage(),
                        builder.substring(0, builder.length() - 2));
        return new ResponseEntity<>(errorResponse, headers, errorResponse.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        log.error("RestExceptionHandler.handleMethodArgumentTypeMismatch", ex);
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
    }

    @ExceptionHandler({RestException.class})
    public ResponseEntity<ErrorResponse> handleRestException(RestException ex, WebRequest request) {
        log.error("RestExceptionHandler.handleRestException", ex);
        ErrorResponse errorResponse =
                new ErrorResponse(
                        ex.getResultCode() != null
                                ? ex.getResultCode()
                                : ResultCode.COMM_OPERATION_FAILURE,
                        ex.getStatus(),
                        ex.getLocalizedMessage() != null
                                ? ex.getLocalizedMessage()
                                : ex.getLoggerMessage(),
                        ex.getLoggerMessage());
        errorResponse.setAdditionalData(ex.getAdditionalData());
        errorResponse.setArgs(ex.getArgs());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleAll(final Exception ex, final WebRequest request) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();

        if (path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/swagger-resources")) {
            return null; // Let Spring handle it
        }

        log.error("RestExceptionHandler.handleAll", ex);

        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
    }

    @ExceptionHandler({InvalidDataAccessApiUsageException.class})
    public ResponseEntity<ErrorResponse> handleTransientPropertyValueException(
            InvalidDataAccessApiUsageException ex, WebRequest request) {
        log.error("RestExceptionHandler.handleTransientPropertyValueException", ex);
        Set<String> errors = new HashSet<String>();
        errors.add(ExceptionUtils.getRootCauseMessage(ex));
        ErrorResponse errorResponse =
                new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ExceptionUtils.getRootCauseMessage(ex),
                        errors);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error("RestExceptionHandler.handleHttpMessageNotReadable", ex);
        Set<String> errors = new HashSet<String>();
        String message = ex.getMessage();
        String errorMessage = "Please check the format ";
        Pattern p = Pattern.compile("\\[(.*?)\\]");
        Matcher m = p.matcher(message);
        while (m.find()) {
            message = m.group(1);
        }
        errors.add(message + " of invalid type");
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage, errors);
        return handleExceptionInternal(
                ex, errorResponse, headers, errorResponse.getStatus(), request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        Set<String> errors = new HashSet<String>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST, "This request is invalid", errors);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponse> handleDataIntegrityConstraintViolation(
            DataIntegrityViolationException ex, WebRequest request) {
        logger.error(
                LogStringBuilder.getLoggerString(
                        LogState.EXCEPTION,
                        getClass(),
                        "handleDataIntegrityConstraintViolation",
                        null),
                ex);
        Set<String> errors = new HashSet<String>();
        String message = processDataIntegrityException(ex);
        errors.add(message);
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, errors);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
    }

    //    @ExceptionHandler({StatusRuntimeException.class})
    //    public ResponseEntity<ErrorResponse> handleStatusRuntimeException(
    //            StatusRuntimeException ex, WebRequest request) {
    //        log.error("RestExceptionHandler.handleStatusRuntimeException", ex);
    //        String errorMessage;
    //        if (ex.getLocalizedMessage().contains(": ")) {
    //            errorMessage =
    //                    ex.getLocalizedMessage().substring(ex.getLocalizedMessage().indexOf(": ")
    // + 1);
    //        } else {
    //            errorMessage = ex.getLocalizedMessage();
    //        }
    //        ErrorResponse errorResponse =
    //                new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
    //        return new ResponseEntity<>(errorResponse, new HttpHeaders(),
    // errorResponse.getStatus());
    //    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(
            final Exception ex, final WebRequest request) {
        log.error("RestExceptionHandler.handleAccessDeniedException", ex);
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
    }

    public String processDataIntegrityException(DataIntegrityViolationException ex) {
        String rootCause = ExceptionUtils.getRootCause(ex).getMessage();
        String message;
        if (rootCause.contains("inventory_unique")) {
            message = "Inventory already exists for this product.";
        } else if (rootCause.contains("counter_unique")) {
            message = "Counter Name already exists.";
        } else if (rootCause.contains("counter_unique_1")) {
            message = "Counter Number already exists.";
        } else if (rootCause.contains("category_unique")) {
            message = "Category already exists.";
        } else if (rootCause.contains("m_role_unique")) {
            message = "Role already exists.";
        } else if (rootCause.contains("user_unique")) {
            message = "Username already exists.";
        } else {
            message = rootCause.split(":")[2];
        }
        return message;
    }
}
