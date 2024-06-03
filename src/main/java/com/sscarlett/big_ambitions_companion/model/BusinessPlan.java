package com.sscarlett.big_ambitions_companion.model;

import lombok.Data;

import java.util.List;

@Data
public class BusinessPlan {
    private Integer businessId;
    private String businessName;
    private Integer businessCap;
    private List<Product> productList;
    private List<Display> displayList;
}
