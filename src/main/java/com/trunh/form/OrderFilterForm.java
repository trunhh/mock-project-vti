package com.trunh.form;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class OrderFilterForm {
    @NonNull
    private int accountId;
    private int minCost;
    private int maxCost;
    private Date startDate;
    private Date endDate;
}
