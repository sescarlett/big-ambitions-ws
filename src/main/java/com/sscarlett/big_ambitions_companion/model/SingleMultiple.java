package com.sscarlett.big_ambitions_companion.model;

import lombok.Data;

import java.util.List;

@Data
public class SingleMultiple {
     private List<IdValue> singles;
     private List<IdNameValueName> multiples;
}
