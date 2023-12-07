package FlightReservationSystem;

/**
 * Represents any object that is reservable by the reservation system.
 * This is abstract, and should never be used directly.
 * @author Jackson Casey
 */
abstract public class Reservable {
    /** The seat the reservation occupies */
    private Seat seat;
    /** The passenger occupying the seat */
    private Person passenger;

    /**
     * Constructor for the reservable
     * @param seat The seat the reservation occupies
     * @param passenger The passenger occupying the seat.
     */
    public Reservable(Seat seat, Person passenger) {
        this.seat = seat;
        this.passenger = passenger;

    }

    /**
     * Getter for the seat param
     * @return The value of the seat param
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Setter for the seat param
     * @param seat The value to set seat to.
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /**
     * Getter for the passenger param
     * @return The value of the passenger param
     */
    public Person getPassenger() {
        return passenger;
    }

    /**
     * Setter for the passenger param
     * @param passenger The value to set passenger to.
     */
    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }
}
