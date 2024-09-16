package org.test.hospitalmanagementsystem.ProblemSolving;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

//Key used in the HASHMAP
@Getter
@Setter
class SideKey {

    int fromKey;
    int lengthKey;

    public SideKey(int fromKey, int lengthKey) {
        this.fromKey = fromKey;
        this.lengthKey = lengthKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SideKey sideKey = (SideKey) o;
        return fromKey == sideKey.fromKey && lengthKey == sideKey.lengthKey;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromKey, lengthKey);
    }

}
