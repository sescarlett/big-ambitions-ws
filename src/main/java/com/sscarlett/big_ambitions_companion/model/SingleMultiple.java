package com.sscarlett.big_ambitions_companion.model;

import lombok.Data;

import java.util.List;

@Data
public class SingleMultiple {
     private List<Integer> singles;
     private List<IdNameValueName> multiples;
}
