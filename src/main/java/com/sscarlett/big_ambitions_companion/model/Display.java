package com.sscarlett.big_ambitions_companion.model;

import lombok.Data;

@Data
public class Display {
    private Integer displayId;
    private String name;
    private Integer cost;
    private Integer customerCap;
    //used for sql returns
    private Integer quantity;
}
