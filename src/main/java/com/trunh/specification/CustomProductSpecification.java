package com.trunh.specification;

import com.trunh.entity.Product;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

@SuppressWarnings("serial")
@Data
public class CustomProductSpecification implements Specification<Product> {
    @NonNull
    private String field;

    @NonNull
    private Object value;


    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (field.equalsIgnoreCase("name")) {
            return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
        }else if (field.equalsIgnoreCase("gender")) {
            return criteriaBuilder.equal(root.get("gender"), Product.ProductGender.toEnum(value.toString()));
        }else if (field.equalsIgnoreCase("category")) {
            return criteriaBuilder.equal(root.get("category"), Product.ProductCategory.toEnum(value.toString()));
        }else if (field.equalsIgnoreCase("size")) {
            return criteriaBuilder.equal(root.get("size"), Product.ProductSize.toEnum(value.toString()));
        }else if (field.equalsIgnoreCase("minPrice")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), (int)value);
        }else if (field.equalsIgnoreCase("maxPrice")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), (int)value);
        }
        return null;
    }
}
