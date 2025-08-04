package in.trois.stockmanagement.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vikas
 *
 * @param <T>
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationPayLoad<T> implements Serializable {


	private Integer pageSize;

	private Integer pageNo;

	private Integer totalPages;

	private Long totalRecords;

	private List<T> content = new ArrayList<>();
}
