package in.trois.stockmanagement.searchbean.master;


import in.trois.stockmanagement.searchbean.AbstractSearchBean;
import org.springframework.data.domain.Sort;

public class CategorySearchBean extends AbstractSearchBean {

    public CategorySearchBean() {
        dataSort = Sort.by(Sort.Order.asc("id"));
    }
}
