package in.trois.stockmanagement.predicate.master;

import in.trois.stock.auth.lib.service.constants.Status;
import in.trois.stock.auth.lib.service.utils.ValidationUtils;
import in.trois.stockmanagement.entity.master.Category;
import in.trois.stockmanagement.entity.master.Category_;
import in.trois.stockmanagement.searchbean.master.CategorySearchBean;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CategoryPredicates {
    public static Specification<Category> createPredicate(CategorySearchBean searchBean) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get(Category_.STATUS), Status.ALLOWED));

            return ValidationUtils.isValid(predicates)
                    ? criteriaBuilder.and(predicates.toArray(new Predicate[0]))
                    : null;
        };
    }
}
