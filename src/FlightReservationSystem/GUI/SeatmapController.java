package FlightReservationSystem.GUI;
import FlightReservationSystem.*;
import FlightReservationSystem.util.Tuple;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.css.PseudoClass;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * This is most complicated class in the package, the controller for the seatmapView.fxml file.
 * It has a lot of moving parts, but it shouldn't be too hard to follow.
 *
 * @author Jackson Casey
 */
public class SeatmapController {
    /* ========================================= */
    //Everything in this section of code are refs
    //to nodes defined in the FXML file.
    @FXML
    public Label flightIdent;
    @FXML
    public Label routeText;
    @FXML
    public Label departTime;
    @FXML
    public Label seatLabel;
    @FXML
    public Label seatSubLabel;
    @FXML
    public Label formHeader;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public Spinner<Integer> age;
    @FXML
    public Label seatClass;
    @FXML
    public Button bookButton;
    @FXML
    public Label distanceLabel;
    @FXML
    private GridPane seatGrid;
    @FXML
    private ComboBox<String> beverageSelection;
    @FXML
    private Button lastButtonPressed;
    @FXML
    private Button infobtn;
    /* ========================================= */

    /** A reference to the listener for the home controller, so we can update values. */
    private ControllerListener homeControllerListener;

    /** A list of labels to use for seat columns */
    private final String[] COL_LABELS = {"A","B","C","D","E"};

    /** The flight that this controller was created to show */
    private Flight activeFlight = null;
    /** The last location updated by the controller */
    private Tuple<Integer,Integer> lastLoc = new Tuple<>(-1,-1);

    /** A pseudo-class used for dynamic CSS styling, this one is for seats that are filled. */
    public static final PseudoClass FILLED_PSEUDO = PseudoClass.getPseudoClass("filled");
    /** A pseudo-class used for dynamic CSS styling, this one is for seats that are selected. */
    public static final PseudoClass SELECT_PSEUDO = PseudoClass.getPseudoClass("selected");


    /**
     * Sets if the user should be allowed to edit the reservation form, changes
     * various labels based on editable status.
     * @param b Should the user be allowed to edit?
     */
    private void setIsFormEditable(boolean b) {
            //We'll set the disabled status of form fields based on b.
            firstName.setDisable(!b);
            lastName.setDisable(!b);
            age.setDisable(!b);
            beverageSelection.setDisable(!b);
            infobtn.setDisable(!b);
            bookButton.setDisable(false);
            if(b) {
                //The form should be editable.
                //Change labels
                formHeader.setText("Book Seat Reservation");
                bookButton.setText("Book Seat");
                //Change button action
                bookButton.setOnAction(event -> onBookButton());
            } else {
                //Form should not be editable.
                //Change
                formHeader.setText("View Seat Reservation");
                bookButton.setText("Edit Seat");
                //Change button action
                bookButton.setOnAction(event -> onEditButton());
            }

    }

    /**
     * This method is called when the form is not editable, and the form
     * button is pressed.
     *
     * We change the function to prevent accidental reservation editing, adding
     * an extra press needed to make changes.
     */
    private void onEditButton() {
        //We'll allow the form to be edited now.
        setIsFormEditable(true);
    }

    /**
     * This method is called when the form is editable, and the form
     * button is pressed.
     */
    private void onBookButton() {
        //We'll check if the form is filled first.
        if(validateForm()) {
            //Call the updateFlight method
            updateFlight(updateCurrentReservation(),lastLoc);
            //We no longer need to edit the form.
            setIsFormEditable(false);
        }

    }

    /**
     * Updates the currently selected reservation with the form data.
     * @return The updated reservation
     */
    private Reservable updateCurrentReservation() {
        //Get the current reservation at the seat we have selected.
        Reservation current = (Reservation) activeFlight.getReservation(lastLoc);

        if (current == null) {
            //We'll need to create a new reservation instead.
            return bookNewReservation();
        }

        try {
            //We'll try to set to the selected drink
            current.getBeverageOrder().setBeverageSelection(beverageSelection.getSelectionModel().getSelectedItem());
        } catch (FlightReservationException e) {
            try {
                //We should just be able to set it to none.
                current.getBeverageOrder().setBeverageSelection("None");
            } catch (FlightReservationException ex) {
                //This is a fatal error condition, something is very wrong.
                throw new RuntimeException(ex);
            }
        }


        //Create a new passenger with the new information, preserving the frequent flyer number and miles.
        Person passenger = new Passenger(firstName.getText(),lastName.getText(),
                age.getValueFactory().getValue(),
                ((Passenger) current.getPassenger()).getFrequentFlyerNumber(),
                ((Passenger) current.getPassenger()).getAirlineMiles(),
                ((Passenger) current.getPassenger()).getEmergencyContact());
        //Now we'll actually update passenger in the reservation.
        current.setPassenger(passenger);
        //Return the updated reservation
        return current;
    }

    /**
     * Creates a new reservation from the form data.
     * @return A new reservation
     */
    private Reservation bookNewReservation() {
        lastButtonPressed.pseudoClassStateChanged(FILLED_PSEUDO,true);
        Reservation newReservation = new Reservation(new Seat(lastLoc.x(), lastLoc.y()),
                new Passenger(titleCase(firstName.getText()),titleCase(lastName.getText()),
                        age.getValueFactory().getValue()));
        try {
            newReservation.getBeverageOrder().setBeverageSelection(beverageSelection.getSelectionModel().getSelectedItem());
        } catch (FlightReservationException e) {
            try {
                newReservation.getBeverageOrder().setBeverageSelection("None");
            } catch (FlightReservationException ex) {
                throw new RuntimeException(ex);
            }

        }

        if(((Passenger) newReservation.getPassenger()).getFrequentFlyerNumber() != null){
            Passenger passenger = (Passenger) newReservation.getPassenger();
            passenger.addAirlineMiles(activeFlight.getFlightDistance());
        }

        return newReservation;
    }

    /**
     * Sets the flight for the view, must be called before most of the other functions.
     * @param flight The flight to set for the view
     */
    public void setFlight(Flight flight) {
        activeFlight = flight;

        //Set the labels in the flight information section
        flightIdent.setText(flight.ident);
        routeText.setText(Airport.getRouteString(flight.departs,flight.arrives));
        departTime.setText("Departs at "+flight.departTime);
        distanceLabel.setText(flight.getFlightDistance()+" miles");
    }

    /**
     * Sets the HomeController listener to one passed to the function
     * @param listener The HomeController that should listen to this controller.
     */
    public void setFlightUpdater(ControllerListener listener) {
        this.homeControllerListener = listener;
    }

    /**
     * Converts a given string to title Case
     * @param input String to convert to title Case
     * @return A string in title case
     */
    public static String titleCase(String input) {
        //Convert to all lowercase
        String lowered = input.toLowerCase();
        //Get the first character
        char firstChar = lowered.charAt(0);
        //Create a string builder for the string
        StringBuilder titled = new StringBuilder(lowered);
        //Change the first character to the uppercase version
        titled.setCharAt(0,Character.toUpperCase(firstChar));
        //Return the string
        return titled.toString();
    }

    /**
     * This method updates the reservation in the active flight, and then sends the updated flight back to the home
     * controller to persist the changes.
     *
     * @param updatedReservation The changed reservation
     * @param reservationLocation The seat for the reservation
     */
    private void updateFlight(Reservable updatedReservation, Tuple<Integer,Integer> reservationLocation) {
        //We'll make sure these are true just for sanity.
        if(homeControllerListener != null && activeFlight != null) {
            try {
                //Update the reservation.
                activeFlight.setReservation(updatedReservation,reservationLocation);
            } catch (FlightReservationException e) {
                //This is a fatal error, there is no reason the location and seat shouldn't match.
                throw new RuntimeException(e);
            }

            //Send the flight back to the home controller.
            homeControllerListener.onFlightUpdated(activeFlight);
        }
    }

    @FXML
    public void initialize() {
        //We'll disable everything until a seat is selected.
        firstName.setDisable(true);
        lastName.setDisable(true);
        age.setDisable(true);
        bookButton.setDisable(true);
        beverageSelection.setDisable(true);
        infobtn.setDisable(true);
        infobtn.setOnAction(null);
        //We need to set the constraints for the age spinner.
        age.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,120,18));
    }

    /**
     * Shows a view with some more passenger information.
     * @param person The person who we should view the information for.
     */
    public void viewPassengerInformation(Person person) {
        //We'll need to do a cast.
        Passenger passenger = (Passenger) person;
        //Create a stage
        Stage stage = new Stage();
        //We'll vertically center the info.
        VBox outer = new VBox(10);
        VBox inner = new VBox(10);

        outer.setAlignment(Pos.TOP_LEFT);
        inner.setAlignment(Pos.CENTER_LEFT);
        VBox.setVgrow(inner, Priority.ALWAYS);
        outer.setPadding(new Insets(10,10,10,10));


        //Create all the Labels needed
        Label title =
                new Label("Information for "+titleCase(passenger.getFirstName())+" "+titleCase(passenger.getLastName()));
        Label loyalty = new Label("Frequent Flyer Number: "+(passenger.getFrequentFlyerNumber() != null ?
                passenger.getFrequentFlyerNumber() : "Not Registered"));
        Label miles = new Label("Miles: "+ passenger.getAirlineMiles());
        Label hasEmergencyContact = new Label("Has Emergency Contact: "+ (passenger.getEmergencyContact() == null ?
                "False" : "True"));
        title.setStyle("-fx-font-size: 16px");

        //Add them to their appropriate parent nodes.
        inner.getChildren().addAll(loyalty,miles,hasEmergencyContact);
        outer.getChildren().addAll(title,inner);

        //Create and show the scene.
        Scene scene = new Scene(outer,400,200);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This method adds all the seat buttons on top of the image dynamically based on the flight details.
     * It also sets the actions, and pseudo-classes for the buttons.
     * @throws FlightReservationException If activeFlight is null
     */
    public void addSeats() throws FlightReservationException {
        if (activeFlight == null) {
            throw new FlightReservationException("You must set the active flight before calling addSeats");
        }

        //Get the dimensions of the seatmap
        int rows = activeFlight.getSeatmapDimensions().x() + 1;
        int columns = activeFlight.getSeatmapDimensions().y() + 1;

        //For each row, except the first, which is the label row.
        for (int row = 1; row < rows; row++) {
            // For each column.
            for (int col = 0; col < columns; col++) {
                    if(col == 5 && row==12) {
                        //Emergency Exit Row has no seat on column E
                        //So we'll just move on.
                        continue;
                    }

                    if(col == 2) {
                        //This is the aisle column, and should have the row label in it instead of a button.
                        Label label = new Label(String.valueOf(row));
                        label.setId("RowLabel");
                        //Add the label to the grid.
                        seatGrid.add(label,col,row);
                        //We can continue looping.
                        continue;
                    }

                    //Create a new button
                    Button seatButton = new Button();
                    //Get the seat location of the button, accounting for the aisle row.
                    Tuple<Integer, Integer> resLocation = new Tuple<>(row - 1, (col > 2 ? col - 1: col));
                    //Set the action for the button.
                    seatButton.setOnAction(event -> handleSeatAction(event,resLocation));

                    //See if there is a reservation linked to this button.
                    Reservable activeReservation = activeFlight.getReservation(resLocation);

                    if(activeReservation==null) {
                        //If not, it's not filled, so we remove the pseudo-class.
                        seatButton.pseudoClassStateChanged(FILLED_PSEUDO,false);
                    } else {
                        //If there is a reservation, add the FILLED pseudo-class to it.
                        seatButton.pseudoClassStateChanged(FILLED_PSEUDO, true);
                    }

                    //Make sure the button is the correct size.
                    seatButton.setPrefSize(10,10);
                    //Set the ID of the column to the appropriate seat label.
                    if(col > 2) {
                        //We'll have to remove 1 from the column to account for the aisle column of the grid.
                        seatButton.setId(getSeatLabel(row, col-1));
                    } else {
                        seatButton.setId(getSeatLabel(row, col));
                    }

                    //Finally, add the button to the grid in the right spot.
                    seatGrid.add(seatButton, col, row);
                }
            }
    }

    /**
     * A quick utility method for generating a seat label ID
     * @param row The row of the seat
     * @param col The column of the seat.
     * @return A unique ID for the seat.
     */
    private static String getSeatLabel(int row, int col) {
        return (row-1)+"-"+col;
    }

    /**
     * Makes sure all the form fields that you can type into are filled.
     * @return True if the form is completely filled, false otherwise.
     */
    private boolean validateForm() {
        return !firstName.getText().isEmpty() && !lastName.getText().isEmpty();
    }

    /**
     * This is perhaps the most complicated method in the project, it's the actual method that is called
     * when you press on a seat button.
     * @param event The click event that called the method
     * @param loc The row and column of the button that was clicked.
     */
    private void handleSeatAction(ActionEvent event, Tuple<Integer,Integer> loc) {
        if(lastButtonPressed != null) {
            //Set the SELECT pseudo-class for the last button we pressed to be false
            lastButtonPressed.pseudoClassStateChanged(SELECT_PSEUDO, false);
        }

        // Change lastButtonPressed to be the button that triggered this method.
        lastButtonPressed = (Button) event.getSource();
        // Show it as selected for the user via the SELECT pseudo-class
        lastButtonPressed.pseudoClassStateChanged(SELECT_PSEUDO,true);
        // Set the last location to this seat
        this.lastLoc = loc;

        // Update the label for the seat on the form
        seatLabel.setText("Seat "+(loc.x()+1)+ COL_LABELS[loc.y()]);
        // Change the subtitle label on the form
        seatSubLabel.setText("Reservation Information:");

        //Now get the associated reservation (if it exists)
        Reservable reservation = activeFlight.getReservation(loc);
        if(reservation == null) {

            // If we don't have a reservation, we'll let the user edit the form.
           setIsFormEditable(true);
           //Except for this, since the reservation is null.
           infobtn.setDisable(true);
           infobtn.setOnAction(null);

           // Clear out the form so that it's blank.
           firstName.setText("");
           lastName.setText("");
           age.getValueFactory().setValue(0);

           // Load all the possible beverages
           beverageSelection.setItems(BeverageOrder.getForRow(loc.x()));

           // The seat class label will still need to be set.
           seatClass.setText(Seat.calculateSeatClass(loc.x()));
        } else {
            // To prevent accident changes, we'll not let the user edit.
            setIsFormEditable(false);
            //Except for this.
            infobtn.setDisable(false);
            infobtn.setOnAction(eventInfo -> viewPassengerInformation(reservation.getPassenger()));

            // Fill in the form with the information from the reservation
            firstName.setText(titleCase(reservation.getPassenger().getFirstName()));
            lastName.setText(titleCase(reservation.getPassenger().getLastName()));
            age.getValueFactory().setValue(reservation.getPassenger().getAge());

            // Set beverages combo box to the selected.
            beverageSelection.setItems(((Reservation) reservation).getBeverageOrder().getBeverageList());
            beverageSelection.getSelectionModel()
                    .select(((Reservation) reservation).getBeverageOrder().getBeverageSelection());

            // Set the seat class label
            seatClass.setText(reservation.getSeat().getSeatClass());
        }
    }


}
