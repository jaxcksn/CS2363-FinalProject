package FlightReservationSystem;

abstract public class Reservable {
    private Seat seat;
    private Person passenger;

    public Reservable(Seat seat, Person passenger) {
        this.seat = seat;
        this.passenger = passenger;

    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Person getPassenger() {
        return passenger;
    }

    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }
}
