package FlightReservationSystem.GUI;

import FlightReservationSystem.Flight;

/**
 * We need the {@link SeatmapController} to be able to communicate any changes it makes to the flight
 * back to {@link HomeController}
 *
 * This interface defines the methods needed to accomplish this, and allows us to effectively pass the HomeController
 * to the SeatmapController via a function, and then call the function implemented from below to pass the flight back.
 *
 * @author Jackson Casey
 */
public interface ControllerListener {

    /**
     * The function called when a flight is updated.
     * @param updatedFlight The flight that was updated
     */
    void onFlightUpdated(Flight updatedFlight);
}
