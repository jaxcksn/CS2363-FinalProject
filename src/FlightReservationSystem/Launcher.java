package FlightReservationSystem;

/**
 * This class only exists for when we go to package the application into
 * a jar file. It calls the main function in the main class.
 *
 * This is because our actual main class extends Application, and this isn't ideal
 * for jar packaging.
 *
 * @author Jackson Casey
 */
public class Launcher {
    /**
     * The main entry function for the program.
     * @param args The CLI arguments
     */
    public static void main(String[] args) {
        System.out.println("Lonestar Flight Reservation System\nVersion 0.1\n");
        //We'll just call the main class.
        FlightReservationSystem.main(args);
    }
}
