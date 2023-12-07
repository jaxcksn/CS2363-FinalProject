package FlightReservationSystem;

import FlightReservationSystem.GUI.HomeController;
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

/**
 * This is the main class for the entire application. It gets everything needed
 * ready to show the home page for the user interface.
 *
 * It extends javafx.application.Application
 *
 * @author Jackson Casey
 */
public class FlightReservationSystem extends Application {
    /**
     * This function shows the primary home screen for the application.
     * @param flightList A list of flights to show on the screen
     * @param stage The primary stage of the application
     * @throws IOException If the FXML file can't be loaded
     */
    public static void showHome(List<Flight> flightList, Stage stage) throws IOException {
        // Grab the FXML file for this view
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(HomeController.class.getResource("homePage.fxml")));
        // Load the FXML into a node.
        Parent root = loader.load();

        // Grab the controller class for the home view
        HomeController controller = loader.getController();
        // Pass the flightList into the controller.
        controller.setupHomeView(flightList);

        //Set all the needed stage information.
        stage.setTitle("Lonestar Flight Reservation System");
        //Create a scene from the node we loaded.
        stage.setScene(new Scene(root, 600,400));
        //Show the stage.
        stage.show();
    }

    @Override
    public void start(Stage stage) {
        /*
         * This function overrides the Application start() method so that we can
         * get the primary stage. This function much like the main function but for
         * JavaFX
         */

        //Lets set the app icon on MacOS just for looks
        var appIcon = new Image(getClass().getResource("icon.png").toExternalForm());
        stage.getIcons().add(appIcon);

        //Set icon on the taskbar/dock only for MacOS
        if (Taskbar.isTaskbarSupported()) {
            var taskbar = Taskbar.getTaskbar();

            if (taskbar.isSupported(Feature.ICON_IMAGE)) {
                final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
                var dockIcon = defaultToolkit.getImage(getClass().getResource("icon.png"));
                taskbar.setIconImage(dockIcon);
            }
        }


        System.out.println("Initializing Flight Database.");
        //Get the flightDatabase up and initialized.
        FlightDatabase flightDatabase = new FlightDatabase();
        try {
            System.out.println("Database initialized.");
            System.out.println("Startup complete. Showing UI.");
            //Now we show the JavaFX GUI
            showHome(flightDatabase.flightList,stage);
        } catch (Exception e) {
            //Catch the exception
            e.printStackTrace();
        }

    }

    /**
     * This is the main entry point for the application, it launches the
     * UI for use by everyone.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Starting User Interface.");
        // Launch the application
        launch(args);
    }
}
