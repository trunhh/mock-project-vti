package com.trunh.specification;

import com.trunh.entity.Product;
import com.trunh.form.ProductFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ProductSpecification {
    public static Specification<Product> buildWhere(ProductFilterForm pFF){
        Specification<Product> where = null;

        if (pFF == null) return null;

        if (!StringUtils.isEmpty(pFF.getName())) {
            CustomProductSpecification name = new CustomProductSpecification("name", pFF.getName());
            where = Specification.where(name);
        }

        if (!StringUtils.isEmpty(pFF.getGender())) {
            CustomProductSpecification gender = new CustomProductSpecification("gender", pFF.getGender());
            if (where == null) where = gender;
            else where = where.and(gender);
        }

        if (!StringUtils.isEmpty(pFF.getCategory())) {
            CustomProductSpecification category = new CustomProductSpecification("category", pFF.getCategory());
            if (where == null) where = category;
            else where = where.and(category);
        }

        if (!StringUtils.isEmpty(pFF.getSize())) {
            CustomProductSpecification size = new CustomProductSpecification("size", pFF.getSize());
            if (where == null) where = size;
            else where = where.and(size);
        }

        if (pFF.getMinPrice()!=0) {
            CustomProductSpecification minPrice = new CustomProductSpecification("minPrice", pFF.getMinPrice());
            if (where == null) where = minPrice;
            else where = where.and(minPrice);
        }

        if (pFF.getMaxPrice()!=0) {
            CustomProductSpecification maxPrice = new CustomProductSpecification("maxPrice", pFF.getMaxPrice());
            if (where == null) where = maxPrice;
            else where = where.and(maxPrice);
        }
        System.out.println(where);
        return where;
    }
}
