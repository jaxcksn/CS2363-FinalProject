package FlightReservationSystem.util;

/**
 * Utility class to represent a pair tuple, with
 * generic types. This is in an immutable data type.
 */
public final class Pair<T1, T2> {
    private final T1 x;
    private final T2 y;

    public Pair(T1 x, T2 y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x value of the Pair
     * @return X value of the pair
     */
    public T1 getX() {
        return x;
    }

    /**
     * Returns the y value of the Pair
     * @return Y value of the pair
     */
    public T2 getY() {
        return y;
    }
}
