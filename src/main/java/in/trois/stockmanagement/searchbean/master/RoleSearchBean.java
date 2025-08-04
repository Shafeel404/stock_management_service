package in.trois.stockmanagement.searchbean.master;


import in.trois.stockmanagement.searchbean.AbstractSearchBean;
import org.springframework.data.domain.Sort;

public class RoleSearchBean extends AbstractSearchBean {

    public RoleSearchBean() {
        dataSort = Sort.by(Sort.Order.asc("id"));
    }
}
