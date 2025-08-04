package in.trois.stockmanagement.response;


import in.trois.stockmanagement.constants.response.ResultCode;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class AbstractResponse implements Serializable {

    @NotBlank protected ResultCode resultCode;

    protected String resultString;

    public AbstractResponse(ResultCode resultCode, String resultString) {
        this.resultCode = resultCode;
        this.resultString = resultString;
    }
}
