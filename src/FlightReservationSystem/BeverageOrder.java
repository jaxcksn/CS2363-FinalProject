package FlightReservationSystem;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A manager class for a reservations beverage order.
 * @author Josh Ruegge
 */
public class BeverageOrder {
    /** The options the seat has to order */
    private ArrayList<String> beverageOptions;

    /** The selected beverage */
    private String beverageSelection;

    /**
     * Constructor for the BeverageOrder
     * @param seat The seat where the beverage is being ordered.
     */
    public BeverageOrder(Seat seat) {
        // We'll set the default selection to none.
        beverageSelection = "None";

        beverageOptions = new ArrayList<>();
        switch(seat.getSeatClass()) {
            case "Business":
                beverageOptions.add("Bud Light");
                beverageOptions.add("Heineken");
                beverageOptions.add("Modelo");
            case "Premium":
                beverageOptions.add("Dr. Pepper");
                beverageOptions.add("Pepsi");
                beverageOptions.add("Diet Pepsi");
                beverageOptions.add("Apple Juice");
            default:
                beverageOptions.add("None");
                beverageOptions.add("Water");
        }
    }

    /**
     * Getter for the beverage options list.
     * @return The beverage options list, as an observable list.
     */
    public ArrayList<String> getBeverageOptions() {
        return beverageOptions;
    }

    /**
     * Gets the beverage options ArrayList as an Observable list for use in
     * JavaFX code.
     * @return An observable list version of the beverageOptions param.
     */
    public ObservableList<String> getBeverageList() {
        return FXCollections.observableArrayList(getBeverageOptions());
    }

    /**
     * Gets all the available beverages for a row.
     * @param row The row to get beverages for
     * @return An observable list of beverages.
     */
    public static ObservableList<String> getForRow(int row) {
        ArrayList<String> beverageOptions = new ArrayList<>();
        switch(Seat.calculateSeatClass(row)) {
            case "Business":
                beverageOptions.add("Bud Light");
                beverageOptions.add("Heineken");
                beverageOptions.add("Modelo");
            case "Premium":
                beverageOptions.add("Dr. Pepper");
                beverageOptions.add("Pepsi");
                beverageOptions.add("Diet Pepsi");
                beverageOptions.add("Apple Juice");
            default:
                beverageOptions.add("None");
                beverageOptions.add("Water");
        }

        return FXCollections.observableArrayList(beverageOptions);
    }

    /**
     * Sets the selected beverage.
     * @param selection The selected beverage
     * @throws FlightReservationException If the beverage was not found in the beverage options list.
     */
    public void setBeverageSelection(String selection) throws FlightReservationException {
        if(!beverageOptions.contains(selection)) {
            throw new FlightReservationException("Beverage option not found, or not available for seat.");
        }
        beverageSelection = selection;
    }

    /**
     * Getter for the beverage selection.
     * @return The value of the beverage selection.
     */
    public String getBeverageSelection() {
        return beverageSelection;
    }
}