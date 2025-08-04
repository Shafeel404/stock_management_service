package in.trois.stockmanagement.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import in.trois.stockmanagement.constants.response.ResultCode;
import in.trois.stockmanagement.response.AbstractResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author vikas
 *
 * @param <T>
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class RestResponse<T> extends AbstractResponse {

	/**
	 *
	 */
	private static final long serialVersionUID = 5439900501886662433L;

	protected T payLoad;

	/**
	 *
	 * @param resultCode
	 * @param resultString
	 */
	public RestResponse(ResultCode resultCode, String resultString) {
		super(resultCode, resultString);
	}

	public RestResponse(ResultCode resultCode, String resultString, T payLoad) {
		super(resultCode, resultString);
		this.payLoad = payLoad;
	}

}
