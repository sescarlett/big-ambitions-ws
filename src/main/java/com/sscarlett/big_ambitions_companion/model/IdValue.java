package com.sscarlett.big_ambitions_companion.model;

import lombok.Data;

import java.util.Objects;

@Data
public class IdValue {
    private Integer id;
    private Integer value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdValue idValue = (IdValue) o;
        return Objects.equals(id, idValue.id) &&
                Objects.equals(value, idValue.value);
    }
}
