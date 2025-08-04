
package in.trois.stockmanagement.utils;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author vikas
 *
 */
public class ValidationUtils {

	private static final Pattern mobilePattern = Pattern.compile("\\d{10}");

	private static final Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");

	/**
	 *
	 * @param orginalString
	 * @return
	 */
	public static boolean isValid(String orginalString) {
		return (orginalString != null) && (!orginalString.equals("null")) && (!orginalString.strip().isEmpty());
	}

	/**
	 *
	 * @param targetCollection
	 * @return
	 */
	public static boolean isValid(Collection<?> targetCollection) {
		return (targetCollection != null && !targetCollection.isEmpty());
	}

	/**
	 *
	 * @param targetMap
	 * @return
	 */
	public static boolean isValid(Map<?, ?> targetMap) {
		return (targetMap != null && !targetMap.isEmpty());
	}

	/**
	 *
	 * @param targetObject
	 * @return
	 */
	public static boolean isValid(Object targetObject) {
		return targetObject != null;
	}

	/**
	 *
	 * @param targetArray
	 * @return
	 */
	public static boolean isValid(Integer[] targetArray) {
		return targetArray != null && targetArray.length > 0;
	}

	/**
	 *
	 * @param targetArray
	 * @return
	 */
	public static boolean isValid(Long[] targetArray) {
		return targetArray != null && targetArray.length > 0;
	}

	/**
	 *
	 * @param longString
	 * @return
	 */
	public static boolean isValidLong(String longString) {
		try {
			Long.parseLong(longString.strip());
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 *
	 * @param longValue
	 * @return
	 */
	public static boolean isValidLong(Long longValue) {
		try {
			return longValue != null && longValue > 0;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 *
	 * @param longValue
	 * @return
	 */
	public static boolean isValid(Long longValue) {
		try {
			return longValue != null && longValue > 0;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 *
	 * @param intString
	 * @return
	 */
	public static boolean isValidInteger(String intString) {
		try {
			Integer.parseInt(intString.strip());
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 *
	 * @param intValue
	 * @return
	 */
	public static boolean isValidInteger(Integer intValue) {
		try {
			return intValue != null && intValue > 0;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 *
	 * @param intValue
	 * @return
	 */
	public static boolean isValid(Integer intValue) {
		try {
			return intValue != null && intValue > 0;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 *
	 * @param intValue
	 * @return
	 */
	public static boolean isValidIntegerIncZero(Integer intValue) {
		try {
			return intValue != null && intValue >= 0;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 *
	 * @param pageNumber
	 * @return
	 */
	public static boolean isValidPageNumber(Integer pageNumber) {
		return pageNumber != null && pageNumber >= 0;
	}

	/**
	 *
	 * @param pageSize
	 * @return
	 */
	public static boolean isValidPageSize(Integer pageSize) {
		return pageSize != null && pageSize > 0;
	}

	/**
	 *
	 * @param doubleValue
	 * @return
	 */
	public static boolean isValidDouble(Double doubleValue) {
		try {
			return doubleValue != null && doubleValue > 0;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 *
	 * @param doubleString
	 * @return
	 */
	public static boolean isValidDouble(String doubleString) {
		try {
			Double.parseDouble(doubleString.strip());
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 *
	 * @param mobileNo
	 * @return
	 */
	public static boolean isValidMobile(String mobileNo) {
		return mobilePattern.matcher(mobileNo).matches();
	}

	public static boolean isValidEmail(String emailId) {
		return emailPattern.matcher(emailId).matches();
	}
}
