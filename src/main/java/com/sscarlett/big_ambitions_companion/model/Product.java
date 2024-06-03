package com.sscarlett.big_ambitions_companion.model;

import lombok.Data;

@Data
public class Product {
    private Integer productId;
    private String name;
    private Integer value;
    private Integer quantity;
}
