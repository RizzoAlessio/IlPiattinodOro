package main;

import java.util.Objects;

public class Tuple<A, B> {

    public final A first;
    public final B second;

    public Tuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Tuple)) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) obj;
        return Objects.equals(first, tuple.first) &&
               Objects.equals(second, tuple.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }


    @Override
    public String toString() {
        return first + ", " + second;
    }

}