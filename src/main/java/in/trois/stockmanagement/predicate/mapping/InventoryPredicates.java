package in.trois.stockmanagement.predicate.mapping;

import in.trois.stockmanagement.constants.Status;
import in.trois.stockmanagement.entity.Inventory;
import in.trois.stockmanagement.entity.Inventory_;
import in.trois.stockmanagement.searchbean.mapping.InventorySearchBean;

import in.trois.stockmanagement.utils.ValidationUtils;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class InventoryPredicates {
    public static Specification<Inventory> createPredicate(InventorySearchBean searchBean) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get(Inventory_.STATUS), Status.ALLOWED));

            if (ValidationUtils.isValid(searchBean.getCategoryId())) {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get(Inventory_.CATEGORY_ID), searchBean.getCategoryId()));
            }

            if (ValidationUtils.isValid(searchBean.getProductCode())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get(Inventory_.PRODUCT_CODE)),
                                searchBean.getProductCode().toLowerCase() + "%"));
            }

            if (ValidationUtils.isValid(searchBean.getProductName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get(Inventory_.PRODUCT_NAME)),
                                searchBean.getProductName().toLowerCase() + "%"));
            }

            return ValidationUtils.isValid(predicates)
                    ? criteriaBuilder.and(predicates.toArray(new Predicate[0]))
                    : null;
        };
    }
}
