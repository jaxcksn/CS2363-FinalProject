package FlightReservationSystem.util;

/**
 * Utility class to represent a pair tuple, with
 * generic types. This is in an immutable data type.
 *
 * We'll use a record here. Since it's basically what we want, and includes a bunch
 * of useful classes.
 *
 * @see <a href="https://www.baeldung.com/java-record-keyword">Java 14 Record Keyword</a>
 */
public record Tuple<T1,T2>(T1 x, T2 y) {}