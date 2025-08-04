package in.trois.stockmanagement.constants;

/**
 *
 * @author vikas
 *
 */
public interface RestExceptionCodes {

	String UNSUPPORTED_API = "API not supported in this version";

	String ALREADY_EXISTS = "Data already exist";

	String NOT_EXIST = "No data exist";

	String INVALID_REQUEST = "Request invalid";

	String UNIQUE_NAME_VIOLATION = "Name already exist";

	String UNIQUE_CODE_VIOLATION = "Code already exist";

	String INVALID_USER = "UserName and Password doesnot match";

	String PARENT_AND_RESOURCE_SAME = "Resource cannot be its own parent";

	String RESOURCE_EXIST_IN_MODULE = "Resource id is already added in this module";

	String CHILD_VALIDATION = "The parent you given is the child of this resource , please change the parent";

	String ATTACHMENT_ID_NOT_VALID = "The attachment is not valid";

	String COMPLAINT_STATUS_NOT_UPDATABLE = "The complaint status cannot be updated or deleted";

	String ACCESS_UNAUTHORIZED = "Unauthorized access";

	String ACCESS_DENIED = "Access denied";

	String ACCESS_DENIED_NO_DATA_ACCESS = "Data access not configured";

	String PRINCIPAL_NOT_FOUND = "Authorization principal not found";

	String INVALID_SUBJECT_CLAIM = "Invalid authorization subject claim";

	String COMPLAINT_TYPE_ESCALTION_MATRIX_MAPPING = "Mapping exists for this complaint type in escalation matrix .Cannot be deleted";

	String ROLE_NOT_ASSIGNED = "Role is not assigned";

	String TARGETREF_ID_NOT_VALID = "The target id not valid";

	String ROLE_NOT_FOUND = "The role is not present";

	String ROLE_NOT_UPDATABLE = "The role cannot be updated because it is already assigned to a user";

	String ROLE_NOT_DELETABLE = "The role cannot be deleted because it is already assigned to a user";

	String USER_NAME_ALREADY_EXISTS = "This username is already registered";

	String MULTIPLE_ROLE_NOT_ALLOWED = "This user is having assigned roles";

	String USER_NOT_COMPLAINT_HANDLER = "This user is not a handler of this complaint";

	String COMPLAINT_REJECT_RESTRICTED_NO_NEXT_LEVEL = "Cannot reject complaint since no next level officer configured";

	String BAD_REQUEST = "Bad request";

	String COMPLAINT_RESOLVE_RESTRICTED_NO_NEXT_LEVEL = "Cannot resolve complaint since no next level officer configured";

	String COMPLAINT_REJECT_RESTRICTED_INVALID_STATUS = "Complaint cannot be rejected";

	String COMPLAINT_RESOLVE_RESTRICTED_INVALID_STATUS = "Complaint cannot be resolved";

	String COMPLAINT_REASSIGN_RESTRICTED_SAME_LSGI = "Cannot reassign complaint to same ward/lsgi";

	String COMPLAINT_REJECT_REQUEST_ALREADY_PROCESSED = "This request is already processed";

	String COMM_PERMISSION_DENIED = "You are not allowed to perform this operation";

	String OPERATION_NOT_ALLOWED = "Restricted operation";

	String OTP_GENERATOR_NOT_INITIALIZED = "OTP generator not initialized";

	String OTP_KEY_INVALID = "OTP key invalid";

	String OTP_SEND_FAILED = "Failed to sent OTP";

	String INVALID_OTP = "This OTP is invalid";

	String OTP_EXPIRED = "This OTP is expired";

	String OTP_PROCESS_REQUIRE_AUTHENTICATION = "This OTP process requires authentication";

	String TEMPLATE_NOT_CONFIGURED = "Template not configured";

	String LOOKUP_CATEGORY_MAPPED_TO_LOOKUP = "This lookup category is mapped to a lookup. Can't delete";

	String TRAINING_ORGANIZATION_MAPPED_TO_USER_PROFILE = "This training organization is mapped to a user profile. Can't delete";

	String COMPLAINT_CATEGORY_MAPPED_TO_COMPLAINT_TYPE = "This complaint category is mapped to a complaint type.Can't delete";

	String DESIGNATION_MAPPED_TO_USER = "This designation is mapped to a user. Can't delete";

	String VENDOR_CATEGORY_MAPPED_TO_VENDOR = "This vendor category is mapped to a vendor. Can't delete";

	String COMPLAINT_WARD_MAPPING_EXISTS = "Complaint escalation already configured for this ward";

	String SAVE_BEFORE_SUBMISSION = "Please save the application before submission";

	String NOT_SUBMITTED = "Only Pending with DPMU can be sent for verfication";

	String CEILING_NOT_DEFINED = "The ceiling has not been configured for the ulb";

	String GRANT_APPLICATION_UPDATE_NOT_ALLOWED = "Application cannot be updated as it is not with the ULB";

	String MOBILE_AND_ALTERNATIVE_NUMBERS_SAME = "Mobile Number and Alternative Number can't be same";

	String USER_NOT_DPMU = "The user is not the current handler";

	String GRANT_UTILIZATION_APPLICATION_EXIST = "This ULB has already submitted application for this financial year";

	String GRANT_UTILIZATION_APPLICATION_SAVE_NO_SUMMARY_LIST = "Please save the utilization summary before submission";

	String NO_POPULATION_FOR_YEAR = "Cannot calculate GAC because population for this year is not assigned";

	String MOBILE_NUMBER_VALIDATION = "Please enter a valid Mobile Number";
}
