package com.sscarlett.big_ambitions_companion.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductDisplay {
    private Integer productId;
    private String name;
    private Double cost;
    private Double value;
    private Double quantity;
    private List<IdValue> displays;
    private List<Integer> importers;
}
