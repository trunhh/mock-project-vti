package com.trunh.specification;

import com.trunh.entity.Order;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;

@SuppressWarnings("serial")
@Data
public class CustomOrderSpecification implements Specification<Order> {
    @NonNull
    private String field;

    @NonNull
    private Object value;


    @Override
    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (field.equalsIgnoreCase("accountId")) {
            return criteriaBuilder.equal(root.get("account").get("id"), (int)value);
        }else if (field.equalsIgnoreCase("minCost")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("totalCost"), (int)value);
        }else if (field.equalsIgnoreCase("maxCost")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get("totalCost"), (int)value);
        }else if (field.equalsIgnoreCase("minDate")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createdAt"), (Date)value);
        }else if (field.equalsIgnoreCase("maxDate")) {
            return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createdAt"), (Date)value);
        }
        return null;
    }

}
