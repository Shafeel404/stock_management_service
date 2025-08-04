/*
 * Copyright (c) 2018 by C-DAC(T). All rights reserved.
 *
 * @Author Dipuraj D S
 * @Since 26-Feb-2018 5:40:44 PM
 *
 */
package in.trois.stockmanagement.constants.response;


import in.trois.stockmanagement.constants.ResultString;

/**
 *
 * @author vikas
 *
 */
public enum ResultCode {

	COMM_OPERATION_SUCCESS(ResultString.COMM_OPERATION_SUCCESS),
	COMM_OPERATION_FAILURE(ResultString.COMM_OPERATION_FAILURE);

	private final String value;

	private ResultCode(String value) {
		this.value = value;
	}

	/**
	 *
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 *
	 * @param code
	 * @return
	 */
	public static ResultCode getTypeByValue(String code) {
		for (ResultCode status : ResultCode.values()) {
			if (code == status.value) {
				return status;
			}
		}
		return null;
	}

}
