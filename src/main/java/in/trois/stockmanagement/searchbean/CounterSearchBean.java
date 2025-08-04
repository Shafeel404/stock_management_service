package in.trois.stockmanagement.searchbean;

import in.trois.stock.auth.lib.service.searchbean.AbstractSearchBean;

import org.springframework.data.domain.Sort;

public class CounterSearchBean extends AbstractSearchBean {

    public CounterSearchBean() {
        dataSort = Sort.by(Sort.Order.asc("id"));
    }
}
