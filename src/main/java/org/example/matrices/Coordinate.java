package org.example.matrices;

import java.util.Objects;

public record Coordinate(int i, int j, double value) {

    @Override
    public double value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return i == that.i && j == that.j && Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, value);
    }


}
