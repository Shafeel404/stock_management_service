package in.trois.stockmanagement.predicate;

import in.trois.stock.auth.lib.service.constants.Status;
import in.trois.stock.auth.lib.service.utils.ValidationUtils;
import in.trois.stockmanagement.entity.User;
import in.trois.stockmanagement.entity.User_;
import in.trois.stockmanagement.searchbean.UserSearchBean;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserPredicates {
    public static Specification<User> createPredicate(UserSearchBean searchBean) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.notEqual(root.get(User_.USERNAME), "super.admin"));
            predicates.add(criteriaBuilder.equal(root.get(User_.STATUS), Status.ALLOWED));

            return ValidationUtils.isValid(predicates)
                    ? criteriaBuilder.and(predicates.toArray(new Predicate[0]))
                    : null;
        };
    }
}
