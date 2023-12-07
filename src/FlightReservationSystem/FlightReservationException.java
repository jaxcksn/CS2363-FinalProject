package FlightReservationSystem;

/**
 * Represents an exception thrown by the flight reservation system
 *
 * @author Jackson Casey
 */
public class FlightReservationException extends Exception {
    /**
     * Constructor for the exception
     * @param reason The reason the exception was thrown.
     */
    public FlightReservationException(String reason) {
            super(reason);
        }
}
