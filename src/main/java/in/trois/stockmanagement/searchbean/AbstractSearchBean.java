package in.trois.stockmanagement.searchbean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Getter
@Setter
public abstract class AbstractSearchBean implements Serializable {


	@NotNull
	@Min(0)
	protected Integer pageNo = 0;
	@NotNull
	@Min(1)
	protected Integer pageSize = 25;
	@JsonIgnore
	@NotNull
	protected Sort dataSort;
}
