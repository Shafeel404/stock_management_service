package in.trois.stockmanagement.entity;

import in.trois.stockmanagement.payload.DropdownPayload;
import in.trois.stockmanagement.request.AbstractDto;
import in.trois.stockmanagement.request.CounterDto;

import in.trois.stockmanagement.utils.ValidationUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "counter", schema = "stock_management")
public class Counter extends AbstractEntity {

    @Id private UUID id;

    @Column(name = "counter_name")
    private String counterName;

    @Column(name = "counter_number")
    private Integer counterNumber;

    @Override
    public <K extends AbstractDto> void copyFromDTO(K dto) {
        CounterDto counterDto = (CounterDto) dto;
        if (ValidationUtils.isValid(counterDto.getName())) {
            counterName = counterDto.getName();
        }
        if (ValidationUtils.isValid(counterDto.getCounterNumber())) {
            counterNumber = counterDto.getCounterNumber();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public CounterDto toDTO() {
        CounterDto dto = new CounterDto();
        dto.setId(id);
        dto.setName(counterName);
        dto.setCounterNumber(counterNumber);
        return dto;
    }

    @Override
    public DropdownPayload<UUID> toDropDownPayload() {
        DropdownPayload<UUID> payLoad = new DropdownPayload<>();
        payLoad.setId(id);
        payLoad.setName(counterName);
        return payLoad;
    }
}
