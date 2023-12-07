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

public class HomeController implements ControllerListener {
    @FXML
    public TableView flightTable;
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


    ObservableList<Flight> oFlightList;

    @Override
    public void onFlightUpdated(Flight updatedFlight) {
        for (int i = 0; i < oFlightList.size(); i++) {
            if(oFlightList.get(i).getIdent().equals(updatedFlight.getIdent())) {
                oFlightList.set(i,updatedFlight);
                break;
            }
        }
    }

    @FXML
    public void initialize() {
        //The next group of lines sets the TableView column values from Flight object properties
        identCol.setCellValueFactory(new PropertyValueFactory<>("ident"));
        departCol.setCellValueFactory(new PropertyValueFactory<>("departs"));
        arriveCol.setCellValueFactory(new PropertyValueFactory<>("arrives"));
        departTimeCol.setCellValueFactory(new PropertyValueFactory<>("departTime"));
        arriveTimeCol.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        distanceCol.setCellValueFactory(new PropertyValueFactory<>("flightDistance"));

        viewButton.setOnAction(event -> showFlightView());
    }

    public void setupHomeView(List<Flight> flightList) {
        //We need to make this list Observable for the TableView to use.
        oFlightList = FXCollections.observableList(flightList);
        flightTable.getItems().addAll(oFlightList);
    }

    private void showFlightView() {
        Flight selectedFlight = (Flight) flightTable.getSelectionModel().getSelectedItem();

        if(selectedFlight != null) {
            try {

                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
                        HomeController.class.getResource("seatmapView.fxml")));
                Parent root = loader.load();

                SeatmapController controller = (SeatmapController) loader.getController();
                controller.setFlight(selectedFlight);
                controller.setFlightUpdater(this);
                try {
                    controller.addSeats();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Stage stage = new Stage();
                stage.setTitle("View "+selectedFlight.ident);
                stage.setScene(new Scene(root,700,400));
                stage.show();



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
