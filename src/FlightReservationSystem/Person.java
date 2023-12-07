package FlightReservationSystem;

/**
 * The person interface, contains shared methods that anything
 * that represents a person should implement.
 * @author Nestor Banda
 */
public interface Person {
    /**
     * Getter for the first name of the person
     * @return The person's first name
     */
    String getFirstName();
    /**
     * Getter for the last name of the person
     * @return The person's last name
     */
    String getLastName();
    /**
     * Getter for the age of the person
     * @return The person's age
     */
    int getAge();
}
