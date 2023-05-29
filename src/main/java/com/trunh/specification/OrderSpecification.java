package com.trunh.specification;

import com.trunh.entity.Order;
import com.trunh.form.OrderFilterForm;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {
    public static Specification<Order> buildWhere(OrderFilterForm oFF){
        Specification<Order> where=null;

        if (oFF == null) return null;

        if (oFF.getAccountId() > 0) {
            CustomOrderSpecification accountId = new CustomOrderSpecification("accountId", oFF.getAccountId());
            where = Specification.where(accountId);
        }

        if (oFF.getMinCost() > 0) {
            CustomOrderSpecification minCost = new CustomOrderSpecification("minCost", oFF.getMinCost());
            if (where == null) where = Specification.where(minCost);
            else where = where.and(minCost);
        }

        if (oFF.getMaxCost() > 0) {
            CustomOrderSpecification maxCost = new CustomOrderSpecification("maxCost", oFF.getMaxCost());
            if (where == null) where = maxCost;
            else where = where.and(maxCost);
        }

        if (oFF.getStartDate() != null) {
            CustomOrderSpecification minDate = new CustomOrderSpecification("minDate", oFF.getStartDate());
            if (where == null) where = minDate;
            else where = where.and(minDate);
        }

        if (oFF.getEndDate() != null) {
            CustomOrderSpecification maxDate = new CustomOrderSpecification("maxDate", oFF.getEndDate());
            if (where == null) where = maxDate;
            else where = where.and(maxDate);
        }
        System.out.println(where);
        return where;
    }
}
