package in.trois.stockmanagement.searchbean;


import lombok.Getter;
import lombok.Setter;

import org.springframework.data.domain.Sort;

import java.util.UUID;

@Getter
@Setter
public class UserSearchBean extends AbstractSearchBean {

    private UUID roleId;

    public UserSearchBean() {
        dataSort = Sort.by(Sort.Order.asc("id"));
    }
}
