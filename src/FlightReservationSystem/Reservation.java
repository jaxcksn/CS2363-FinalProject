package FlightReservationSystem;

/**
 * Represents a seat reservation.
 * @author Josh Ruegge
 */
public class Reservation extends Reservable {
    /** The beverage order manager for the seat */
    private final BeverageOrder order;

    /**
     * Constructor for the Reservation, calls the Reservable super constructor.
     * @param seat The seat of the reservation.
     * @param passenger The passenger occuping that seat.
     */
    public Reservation(Seat seat, Person passenger) {
        super(seat, passenger);
        order = new BeverageOrder(seat);
    }

    /**
     * Getter for the beverage order manager.
     * @return The beverage order manager.
     */
    public BeverageOrder getBeverageOrder() {
        return order;
    }
}