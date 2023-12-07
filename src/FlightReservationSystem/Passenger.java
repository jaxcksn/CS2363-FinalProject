package FlightReservationSystem;

import java.util.Random;

/**
 * Represents a passenger for the airline.
 * @author Nestor Banda
 */
public class Passenger implements Person {

    /** The passengers first name */
    private final String firstName;
    /** The passengers last name */
    private final String lastName;
    /** The age of the passenger */
    private final int age;
    /** The passengers Frequent Flyer number */
    private final String frequentFlyerNumber;
    /** The number of miles the passenger has */
    private int airlineMiles;
    /** The emergency contact of the passenger */
    private final Contact emergencyContact;

    // Constructor to initialize the Passenger object with all information
    public Passenger(String firstName, String lastName, int age, String frequentFlyerNumber, int airlineMiles,
                     Contact emergencyContact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.frequentFlyerNumber = frequentFlyerNumber;
        this.airlineMiles = airlineMiles;
        this.emergencyContact = emergencyContact;
    }


    public Passenger(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.frequentFlyerNumber = null;
        this.airlineMiles = 0;
        this.emergencyContact = null;
    }

    /**
     * Creates a random Frequent Flyer number
     * @author Jackson Casey
     * @param prefix The characters to prefix to the number.
     * @return A new frequent flyer number.
     */
    public static String createFFN(String prefix) {
        Random random = new Random();
        StringBuilder frequentFlyerNumber = new StringBuilder();
        frequentFlyerNumber.append(prefix);
        for(int i = 0; i < 10; i++) {
            int randomDigit = random.nextInt(10);
            frequentFlyerNumber.append(randomDigit);
        }

        return frequentFlyerNumber.toString();
    }

    // Override methods from the Person interface to provide implementations
    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public int getAge() {
        return age;
    }

    // Additional method to get the frequent flyer number
    public String getFrequentFlyerNumber() {
        return frequentFlyerNumber;
    }

    // Additional method to get the airline miles
    public int getAirlineMiles() {
        return airlineMiles;
    }

    public void addAirlineMiles(int milesToAdd) {
        airlineMiles += milesToAdd;
    }

    // Getter method for emergency contact
    public Contact getEmergencyContact() {
        return emergencyContact;
    }
}