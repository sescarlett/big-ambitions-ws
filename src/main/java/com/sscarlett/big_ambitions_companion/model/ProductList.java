package com.sscarlett.big_ambitions_companion.model;

import lombok.Data;

@Data
public class ProductList {
    private Integer productId;
    private String name;
    private Double value;
    private Double cost;
    private Integer quantity;
    private String displayNames;
    private String importerNames;
}
