package FlightReservationSystem;

/**
 * Represents a seat on the airplane and it's location.
 * @author Josh Ruegge
 */
public class Seat {
    /** The row of the seat */
    public int row;
    /** The column of the seat. */
    public int col;
    /** The class of the seat */
    private final String seatClass;

    /**
     * Constructor for the seat.
     * @param row The row of the seat
     * @param col The column of the seat
     */
    public Seat(int row, int col) {
        this.row = row;
        this.col = col;

        seatClass = calculateSeatClass(row);
    }


    /**
     * Method to assign seat class based on row, first three rows will be Business, next 5 will be Premium,
     * and rest will be Economy.
     * @param row The row of the seat
     * @return The class of the seat.
     */
    public static String calculateSeatClass(int row) {
        if(row < 3) {
            return "Business";
        } else if(row < 8) {
            return "Premium";
        } else {
            return "Economy";
        }
    }

    /**
     * Getter for the seat class param
     * @return The value of the seat class param
     */
    public String getSeatClass() {
        return seatClass;
    }
}