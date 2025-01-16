package main;

class Tuple<A, B> {
    public final A first;
    public final B second;

    public Tuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}