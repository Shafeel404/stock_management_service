/*
 * Copyright (c) 2018 by C-DAC(T). All rights reserved.
 *
 * @Author Dipuraj D S
 * @Since 26-Feb-2018 6:17:26 PM
 * @Version 3.0
 * @File <Client> Request.java
 */
package in.trois.stockmanagement.request;

import in.trois.stockmanagement.utils.ValidationUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 *
 * @author vikas
 *
 * @param <T>
 */
@NoArgsConstructor
@ToString
public @Data class Request<T> implements Serializable {

	public static enum SOURCE_TYPE {
		MOBILE, WEB;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 2467164618615108395L;

	private SOURCE_TYPE sourceType = SOURCE_TYPE.WEB;

	private T payLoad;

	public Request(T payLoad) {
		this.payLoad = payLoad;
	}

	/**
	 *
	 * @return
	 */
	public boolean isValid() {
		return ValidationUtils.isValid(payLoad) && ValidationUtils.isValid(sourceType);
	}
}
