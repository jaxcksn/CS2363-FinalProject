package FlightReservationSystem;


import FlightReservationSystem.util.Pair;

public final class Flight {
    public final String ident;
    public final Airport departs;
    public final Airport arrives;

    public final String departTime;
    public final String arrivalTime;

    private Reservable[][] reservations;

    public Flight(String ident, String departs, String arrives, String departTime, String arrivalTime) {
        this.ident = ident;
        this.departs = new Airport(departs);
        this.arrives = new Airport(arrives);
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        //We'll just use 28 rows and 5 columns since that is an economy layout A220-300
        this.reservations = new Reservable[30][5];
    }

    /**
     *
     * @return The 2D array of reservables
     */
    public Reservable[][] getReservations() {
        return reservations;
    }

    public Pair<Integer, Integer> getSeatmapDimensions() {
        return new Pair<>(reservations.length, reservations[0].length);
    }

    public void setReservations(Reservable[][] reservables) {
        this.reservations = reservables;
    }

    public Reservable getReservation(Pair<Integer,Integer> getAt) {
        //TODO: Implement Search by Seat
        return reservations[getAt.getX()][getAt.getY()];
    }

    public void setReservation(Reservable updatedReservable, Pair<Integer,Integer> resIndex) {
        //TODO: Implement logic to make sure seatRow and seatCol matches reservation seat.
        reservations[resIndex.getX()][resIndex.getY()] = updatedReservable;
    }

    public void setReservation(Reservable updatedReservable) {
        reservations[updatedReservable.getSeat().row][updatedReservable.getSeat().col] = updatedReservable;
    }

    //GETTERS AND SETTERS NEEDED FOR JAVAFX

    public String getIdent() {
        return ident;
    }

    public String getDeparts() {
        return departs.iataCode;
    }

    public String getArrives() {
        return arrives.iataCode;
    }

    public String getDepartTime() {
        return departTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }
}
