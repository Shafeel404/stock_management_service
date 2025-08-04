package in.trois.stockmanagement.searchbean.mapping;

import in.trois.stock.auth.lib.service.searchbean.AbstractSearchBean;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.domain.Sort;

import java.util.UUID;

@Getter
@Setter
public class InventorySearchBean extends AbstractSearchBean {

    private UUID categoryId;

    private String productCode;

    private String productName;

    public InventorySearchBean() {
        dataSort = Sort.by(Sort.Order.asc("id"));
    }
}
