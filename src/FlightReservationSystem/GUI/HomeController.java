package FlightReservationSystem.GUI;

import FlightReservationSystem.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

/**
 * This controls the Home view for the program. It is closely linked with
 * the homePage.fxml file, so it's recommended you have both files close by.
 *
 * Because of the nature of controllers, and their habit for being less readable,
 * I will try my best to comment this.
 *
 * @author Jackson Casey
 */
public class HomeController implements ControllerListener {
    //Anything that starts with @FXML is a link to a certain node in the FXML file.
    @FXML
    public TableView<Flight> flightTable;
    @FXML
    public TableColumn departCol;
    @FXML
    public TableColumn departTimeCol;
    @FXML
    public TableColumn arriveTimeCol;
    @FXML
    public TableColumn arriveCol;
    @FXML
    public TableColumn identCol;
    @FXML
    public Button viewButton;
    @FXML
    public TableColumn distanceCol;

    /** The main observable flight list used to show all the flights. */
    ObservableList<Flight> oFlightList;

    @Override
    /*
      This is part of the implementation of the listener.
      see ControllerListener for more details
     */
    public void onFlightUpdated(Flight updatedFlight) {
        // We'll update the respective flight in the list.
        for (int i = 0; i < oFlightList.size(); i++) {
            // This next line is why identifiers for flights should be unique.
            if(oFlightList.get(i).getIdent().equals(updatedFlight.getIdent())) {
                oFlightList.set(i,updatedFlight);
                break;
            }
        }
    }

    @FXML
    /*
     * Initializes the controller, this called after the constructor, and is a function
     * that exists in every controller.
     */
    public void initialize() {
        //The next group of lines sets the TableView column values from Flight object properties
        identCol.setCellValueFactory(new PropertyValueFactory<>("ident"));
        departCol.setCellValueFactory(new PropertyValueFactory<>("departs"));
        arriveCol.setCellValueFactory(new PropertyValueFactory<>("arrives"));
        departTimeCol.setCellValueFactory(new PropertyValueFactory<>("departTime"));
        arriveTimeCol.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        distanceCol.setCellValueFactory(new PropertyValueFactory<>("flightDistance"));

        //We'll also set what happens when we push the viewButton node.
        viewButton.setOnAction(event -> showSeatmapView());
    }

    /**
     * This method is used to set up all the needed data values for the view,
     * and is called before the view is actually created.
     * @param flightList The initial flight list.
     */
    public void setupHomeView(List<Flight> flightList) {
        //We need to make this list Observable for the TableView to use.
        oFlightList = FXCollections.observableList(flightList);
        //Now we add it to the flightTable node
        flightTable.getItems().addAll(oFlightList);
    }

    /**
     * This method shows the seatmap view for the selected flight in the table to the user.
     * It's pretty standard FXML loading, but see the {@link SeatmapController} for the functions
     * called from it.
     */
    private void showSeatmapView() {
        //This is flight the user has selected flight.
        Flight selectedFlight = flightTable.getSelectionModel().getSelectedItem();

        if(selectedFlight != null) {
            // selectedFlight will be null if nothing is selected, so we should only try
            // to load if we actually have something selected.
            try {
                //Create a loader for the seatmap view's FXML file
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
                        HomeController.class.getResource("seatmapView.fxml")));
                //Set the root node.
                Parent root = loader.load();
                //Get the controller for the seatmap view
                SeatmapController controller = (SeatmapController) loader.getController();
                //Set the flight for the seatmap
                controller.setFlight(selectedFlight);
                //Set the updater function to this class, so we can pass data back.
                controller.setFlightUpdater(this);
                try {
                    //We'll attempt to add the seats.
                    controller.addSeats();
                } catch (Exception e) {
                    //This is a fatal error condition.
                    throw new RuntimeException(e);
                }

                //Create a new stage.
                Stage stage = new Stage();
                //Set the title of the stage
                stage.setTitle("View "+selectedFlight.ident);
                //Set the root node to the FXML we loaded.
                stage.setScene(new Scene(root,700,400));
                //Show the view.
                stage.show();
            } catch (Exception e) {
                //If we can't load the FXML that is a fatal error.
                throw new RuntimeException(e);
            }
        }
    }



}
