package FlightReservationSystem;

import FlightReservationSystem.GUI.fsrHomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import java.awt.Taskbar;
import java.awt.Toolkit;
import java.awt.Taskbar.Feature;

public class main extends Application {

    public static void showHome(List<Flight> flightList, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(fsrHomeController.class.getResource("fsrHome.fxml")));
        Parent root = loader.load();

        fsrHomeController controller = (fsrHomeController) loader.getController();
        controller.setupHomeView(flightList);

        stage.setTitle("Lonestar Flight Reservation System");
        stage.setScene(new Scene(root, 600,400));
        stage.show();
    }

    @Override
    public void start(Stage stage) {
        var appIcon = new Image(getClass().getResource("icon.png").toExternalForm());
        stage.getIcons().add(appIcon);

        //Set icon on the taskbar/dock
        if (Taskbar.isTaskbarSupported()) {
            var taskbar = Taskbar.getTaskbar();

            if (taskbar.isSupported(Feature.ICON_IMAGE)) {
                final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
                var dockIcon = defaultToolkit.getImage(getClass().getResource("icon.png"));
                taskbar.setIconImage(dockIcon);
            }


        }

        //Initialize the database
        System.out.println("Initializing Flight Database.");
        FlightDatabase flightDatabase = new FlightDatabase();
        try {
            //Show the home JavaFX GUI
            System.out.println("Database initialized.");
            System.out.println("Startup complete. Showing UI.");
            showHome(flightDatabase.flightList,stage);
        } catch (Exception e) {
            //Catch the exception
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        System.out.println("Starting User Interface.");
        launch(args);
    }
}
