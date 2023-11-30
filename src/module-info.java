module FlightReservationSystem {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;
    requires java.desktop;
    opens FlightReservationSystem to javafx.graphics, javafx.fxml, javafx.base;
    opens FlightReservationSystem.GUI to javafx.fxml, javafx.graphics, javafx.base;
}