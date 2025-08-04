package in.trois.stockmanagement.request;

import static org.springframework.http.HttpMethod.PATCH;

import in.trois.stock.auth.lib.service.dto.AbstractDto;
import in.trois.stock.auth.lib.service.utils.ValidationUtils;
import in.trois.stockmanagement.entity.Counter;

import lombok.Data;

import org.springframework.http.HttpMethod;

import java.util.UUID;

@Data
public class CounterDto extends AbstractDto {

    private UUID id;

    private String name;

    private Integer counterNumber;

    private Integer status;

    @Override
    @SuppressWarnings("unchecked")
    public Counter toEntity() {
        Counter entity = new Counter();
        entity.setId(UUID.randomUUID());
        entity.setCounterName(name);
        entity.setCounterNumber(counterNumber);
        return entity;
    }

    @Override
    public boolean isValid(HttpMethod httpMethod) {
        if (httpMethod.equals(PATCH)) {
            if (!ValidationUtils.isValid(name)) {
                addError("counterName", name);
            }
            if (!ValidationUtils.isValid(counterNumber)) {
                addError("counterNumber", counterNumber);
            }
        }
        return getErrors() == null || getErrors().isEmpty();
    }
}
