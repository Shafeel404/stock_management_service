package in.trois.stockmanagement.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import in.trois.stockmanagement.entity.AbstractEntity;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractDto implements Serializable {


	@JsonIgnore
	protected String createdBy;

	@JsonIgnore
	protected Long createdAt;

	// @JsonIgnore
	protected String lastModifiedBy;

	// @JsonIgnore
	protected Long lastModifiedAt;

	@Parameter(hidden = true)
	private List<String> errors;

	public abstract <T extends AbstractEntity> T toEntity();

	@Parameter(hidden = true)
	public abstract boolean isValid(HttpMethod httpMethod);

	protected void addError(String errorParamKey, Object errorParamValue) {
		if (errors == null) {
			errors = new ArrayList<>();
		}
		errors.add("Please enter the field " + errorParamKey);
	}

	protected void addError(String errorMessage) {
		if (errors == null) {
			errors = new ArrayList<>();
		}
		errors.add(errorMessage);
	}

	protected void addError(List<String> errorList) {
		if (errors == null) {
			errors = new ArrayList<>();
		}
		errors.addAll(errorList);
	}
}
