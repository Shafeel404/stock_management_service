package in.trois.stockmanagement.constants;

/** @author vikas */
public interface ResultString {

    String COMM_OPERATION_SUCCESS = "Operation success";

    String COMM_OPERATION_FAILURE = "Operation failed";

    String NO_DATA_AVAILABLE = "No data available";

    String INVALID_REQUEST = "Request invalid";

    String INTERNAL_SERVER_ERROR = "An unexpected error occurred. Please try again later.";

    String UNSUPPORTED_API = "API not supported in this version";

    String ALREADY_EXISTS = "Data already exist";

    String NOT_EXIST = "No data exist";

    String ACCESS_UNAUTHORIZED = "Unauthorized access";

    String ACCESS_DENIED = "Access denied";

    String ACCESS_DENIED_NO_DATA_ACCESS = "Data access not configured";

    String PRINCIPAL_NOT_FOUND = "Authorization principal not found";

    String INVALID_SUBJECT_CLAIM = "Invalid authorization subject claim";

    String OPERATION_NOT_ALLOWED = "Restricted operation";

    String COMM_DEFAULT_PASSWORD_CHANGE = "Change default password";

    String COMM_DEFAULT_LOGIN = "Default login";
}
