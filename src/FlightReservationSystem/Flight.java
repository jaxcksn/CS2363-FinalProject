package FlightReservationSystem;


import FlightReservationSystem.util.Tuple;

/**
 * Represents a scheduled flight for the airline, this is probably
 * the most used class in the entire program.
 *
 * Flights, by design, are immutable.
 *
 * @author Jackson Casey
 */
public final class Flight {
    /** The unique identifier for the flight */
    public final String ident;
    /** The airport the flight departs from */
    public final Airport departs;
    /** The airport the flight arrives to*/
    public final Airport arrives;
    /** The time the flight departs */
    public final String departTime;
    /** The time the flight arrives */
    public final String arrivalTime;
    /** This is the seat map, a 2d array of reservable */
    private final Reservable[][] reservations;
    /** The distance of the flight, in miles */
    private final double flightDistance;

    /**
     * Constructor for a new flight.
     *
     * @param ident The unique identifier for the flight.
     * @param departs The airport the flight departs from
     * @param arrives The airport the flight is arriving to
     * @param departTime The zulu time string that the flight takes off
     * @param arrivalTime The zulu time string that the flight lands
     */
    public Flight(String ident, String departs, String arrives, String departTime, String arrivalTime) {
        this.ident = ident;
        this.departs = Airport.fromIATA(departs);
        this.arrives = Airport.fromIATA(arrives);
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.flightDistance = Airport.getRouteDistance(this.departs,this.arrives);

        //We'll just use 28 rows and 5 columns since that is an economy layout A220-300
        //This could be changed dynamically.
        this.reservations = new Reservable[30][5];
    }

    /**
     * Gets all the reservations
     * @return The 2D array of reservable
     */
    public Reservable[][] getReservations() {
        return reservations;
    }

    /**
     * Gets the dimensions of the reservation array.
     * @return A Pair with the dimensions of the seat map
     */
    public Tuple<Integer, Integer> getSeatmapDimensions() {
        return new Tuple<>(reservations.length, reservations[0].length);
    }

    /**
     * Gets a reservation at a location in the reservations array.
     *
     * @param getAt The row and column where the reservation should be pulled from
     * @return A reservable
     */
    public Reservable getReservation(Tuple<Integer,Integer> getAt) {
        return reservations[getAt.x()][getAt.y()];
    }

    /**
     * Sets a location in the reservations array to the given reservable. This overload is just for
     * readability.
     *
     * @param updatedReservable The reservable to place in the array.
     * @param resIndex Where the reservation should be updated at.
     * @throws FlightReservationException When the seat doesn't match the given index.
     */
    public void setReservation(Reservable updatedReservable, Tuple<Integer,Integer> resIndex) throws FlightReservationException {
        if((resIndex.x() != updatedReservable.getSeat().row ) || (resIndex.y() != updatedReservable.getSeat().col)) {
            //This is to prevent a logic error.
            throw new FlightReservationException("Seat values in Reservable does not match index passed to " +
                    "setReservation function.");
        }

        reservations[resIndex.x()][resIndex.y()] = updatedReservable;
    }

    /**
     * Sets a location in the reservations array to the given reservable. This overload
     * uses the seat number in the reservable.
     *
     * @param updatedReservable The reservable to place in the array.
     */
    public void setReservation(Reservable updatedReservable) {
        reservations[updatedReservable.getSeat().row][updatedReservable.getSeat().col] = updatedReservable;
    }


    /**
     * Getter for the ident param. Used by JavaFX.
     * @return The ident param value
     */
    public String getIdent() {
        return ident;
    }

    /**
     * Getter for the departs param. Used by JavaFX.
     * @return The departs param value
     */
    public String getDeparts() {
        return departs.iataCode();
    }

    /**
     * Getter for the arrives param. Used by JavaFX.
     * @return The arrives param value
     */
    public String getArrives() {
        return arrives.iataCode();
    }

    /**
     * Getter for the departTime param. Used by JavaFX.
     * @return The departTime param value
     */
    public String getDepartTime() {
        return departTime;
    }

    /**
     * Getter for the arrivalTime param. Used by JavaFX.
     * @return The arrivalTime param value
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Getter for the flightDistance param. Used by JavaFX.
     * @return The flightDistance param value
     */
    public int getFlightDistance() {return (int) Math.round(flightDistance);}
}
