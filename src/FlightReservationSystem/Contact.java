package FlightReservationSystem;

/**
 * Represents the contact information for a Person, this would normally be private
 * and not shown in the FSR
 * @author Nestor Banda
 */
public class Contact implements Person {
    /** The person's first name */
    private String firstName;
    /** The person's last name */
    private String lastName;
    /** The person's age */
    private int age;
    /** The person's phone number */
    private String phoneNumber;
    /** The person's relationship to the passenger */
    private String relation;

    /**
     * Constructor for the contact
     * @param firstName The person's first name
     * @param lastName The person's last name
     * @param age The person's age
     * @param phoneNumber The person's phone number
     * @param relation The person's relationship to the passenger
     */
    public Contact(String firstName, String lastName, int age, String phoneNumber, String relation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.relation = relation;
    }

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
}
