package com.sscarlett.big_ambitions_companion.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductDisplay {
    private String name;
    private List<IdValue> displays;
}
