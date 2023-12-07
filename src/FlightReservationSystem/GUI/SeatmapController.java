package FlightReservationSystem.GUI;
import FlightReservationSystem.*;
import FlightReservationSystem.util.Tuple;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.css.PseudoClass;


public class SeatmapController {
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

    private ControllerListener updateListener;

    private final String[] colLabels = {"A","B","C","D","E"};

    private Flight activeFlight = null;

    private Tuple<Integer,Integer> lastLoc = new Tuple<>(-1,-1);

    public static final PseudoClass FILLED_PSEUDO = PseudoClass.getPseudoClass("filled");
    public static final PseudoClass SELECT_PSEUDO = PseudoClass.getPseudoClass("selected");

    @FXML
    private Button lastButtonPressed;

    private void setIsFormEditable(boolean b) {

            firstName.setDisable(!b);
            lastName.setDisable(!b);
            age.setDisable(!b);
            beverageSelection.setDisable(!b);
            bookButton.setDisable(false);
            if(b) {
                //The form should be editable
                formHeader.setText("Book Seat Reservation");
                bookButton.setText("Book Seat");
                bookButton.setOnAction(event -> onBookButton());

            } else {
                formHeader.setText("View Seat Reservation");
                bookButton.setText("Edit Seat");
                bookButton.setOnAction(event -> onEditButton());
            }

    }

    private void onEditButton() {
        setIsFormEditable(true);
    }

    private void onBookButton() {
        if(validateForm()) {
            updateFlight(updateCurrentReservation(),lastLoc);
            setIsFormEditable(false);
        }

    }

    private Reservable updateCurrentReservation() {
        Reservation current = (Reservation) activeFlight.getReservation(lastLoc);
        //We'll try to update the drink.
        if (current == null) {
            return bookNewReservation();
        }

        try {
            current.getBeverageOrder().setBeverageSelection(beverageSelection.getSelectionModel().getSelectedItem());
        } catch (FlightReservationException e) {
            try {
                current.getBeverageOrder().setBeverageSelection("None");
            } catch (FlightReservationException ex) {
                throw new RuntimeException(ex);
            }
        }



        Person passenger = new Passenger(firstName.getText(),lastName.getText(),
                age.getValueFactory().getValue(),
                ((Passenger) current.getPassenger()).getFrequentFlyerNumber(),
                ((Passenger) current.getPassenger()).getAirlineMiles(),
                ((Passenger) current.getPassenger()).getEmergencyContact());
        current.setPassenger(passenger);
        return current;
    }


    private Reservation bookNewReservation() {
        lastButtonPressed.pseudoClassStateChanged(FILLED_PSEUDO,true);
        Reservation newReservation = new Reservation(new Seat(lastLoc.x(), lastLoc.y()),
                new Passenger(firstName.getText().toUpperCase(),lastName.getText().toUpperCase(),
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

        return newReservation;
    }

    public void setFlight(Flight flight) {
        activeFlight = flight;
        flightIdent.setText(flight.ident);
        routeText.setText(Airport.getRouteString(flight.departs,flight.arrives));
        departTime.setText("Departs at "+flight.departTime);
        distanceLabel.setText(flight.getFlightDistance()+" miles");
    }

    public void setFlightUpdater(ControllerListener listener) {
        this.updateListener = listener;
    }

    private void updateFlight(Reservable updatedReservation, Tuple<Integer,Integer> reservationLocation) {
        if(updateListener != null && activeFlight != null) {
            try {
                activeFlight.setReservation(updatedReservation,reservationLocation);
            } catch (FlightReservationException e) {
                throw new RuntimeException(e);
            }

            updateListener.onFlightUpdated(activeFlight);
        }
    }

    @FXML
    public void initialize() {
        firstName.setDisable(true);
        lastName.setDisable(true);
        age.setDisable(true);
        bookButton.setDisable(true);
        beverageSelection.setDisable(true);
        age.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,120,18));
    }

    public void addSeats() throws Exception {
        if (activeFlight == null) {
            throw new Exception();
        }


        int rows = activeFlight.getSeatmapDimensions().x() + 1;
        int columns = activeFlight.getSeatmapDimensions().y() + 1;

        for (int row = 1; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                    if(col == 5 && row==12) {
                        //Emergency Exit Row has no seat on E
                        continue;
                    }

                    if(col == 2) {
                        Label label = new Label(String.valueOf(row));
                        label.setId("RowLabel");
                        seatGrid.add(label,col,row);
                        continue;
                    }

                    Button seatButton = new Button();
                    Tuple<Integer, Integer> resLocation = new Tuple<>(row - 1, (col > 2 ? col - 1: col));
                    seatButton.setOnAction(event -> handleSeatAction(event,resLocation));
                    seatButton.setId(row+"-"+col);
                    Reservable activeReservation = activeFlight.getReservation(resLocation);

                    if(activeReservation==null) {
                        seatButton.pseudoClassStateChanged(FILLED_PSEUDO,false);
                    } else {
                        seatButton.pseudoClassStateChanged(FILLED_PSEUDO, true);
                    }


                    seatButton.setPrefSize(10,10);
                    if(col > 2) {
                        seatButton.setId(getSeatLabel(row, col-1));
                    } else {
                        seatButton.setId(getSeatLabel(row, col));
                    }
                    seatGrid.add(seatButton, col, row);


                }
            }
    }

    private static String getSeatLabel(int row, int col) {
        return (row-1)+"-"+col;
    }


    private boolean validateForm() {
        if(firstName.getText().isEmpty() || lastName.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private static Tuple<Integer,Integer> parseID(String id) {
        String[] splitID = id.split("-");
        return new Tuple<>(Integer.valueOf(splitID[0]),Integer.valueOf(splitID[1]));
    }

    private void handleSeatAction(ActionEvent event, Tuple<Integer,Integer> loc) {
        if(lastButtonPressed != null) {
            lastButtonPressed.pseudoClassStateChanged(SELECT_PSEUDO, false);
        }

        lastButtonPressed = (Button) event.getSource();
        lastButtonPressed.pseudoClassStateChanged(SELECT_PSEUDO,true);
        this.lastLoc = loc;
        Reservable reservation = activeFlight.getReservation(loc);
        seatLabel.setText("Seat "+(loc.x()+1)+colLabels[loc.y()]);
        seatSubLabel.setText("Reservation Information:");
        if(reservation == null) {
           setIsFormEditable(true);
           firstName.setText("");
           lastName.setText("");
           age.getValueFactory().setValue(0);
           beverageSelection.setItems(BeverageOrder.getForRow(loc.x()));
           seatClass.setText(Seat.calculateSeatClass(loc.x()));
        } else {
            setIsFormEditable(false);
            firstName.setText(reservation.getPassenger().getFirstName());
            lastName.setText(reservation.getPassenger().getLastName());
            age.getValueFactory().setValue(reservation.getPassenger().getAge());
            beverageSelection.setItems(((Reservation) reservation).getBeverageOrder().getBeverageList());
            beverageSelection.getSelectionModel()
                    .select(((Reservation) reservation).getBeverageOrder().getBeverageSelection());
            seatClass.setText(reservation.getSeat().getSeatClass());
        }
    }


}
