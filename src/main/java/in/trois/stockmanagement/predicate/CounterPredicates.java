package in.trois.stockmanagement.predicate;

import in.trois.stockmanagement.constants.Status;
import in.trois.stockmanagement.entity.Counter;
import in.trois.stockmanagement.entity.Counter_;
import in.trois.stockmanagement.searchbean.CounterSearchBean;

import in.trois.stockmanagement.utils.ValidationUtils;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CounterPredicates {
    public static Specification<Counter> createPredicate(CounterSearchBean searchBean) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get(Counter_.STATUS), Status.ALLOWED));

            return ValidationUtils.isValid(predicates)
                    ? criteriaBuilder.and(predicates.toArray(new Predicate[0]))
                    : null;
        };
    }
}
