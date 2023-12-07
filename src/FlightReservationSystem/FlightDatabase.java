package FlightReservationSystem;

import FlightReservationSystem.util.Tuple;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class is where the airline would implement their own connection
 * to their flight database and pull flights. Which is outside the scope of this project.
 * For this demonstration we'll just create a list of {@link Flight Flights}.
 *
 * @author Jackson Casey
 */
public final class FlightDatabase {
    /** This is the list of all flights */
    public final List<Flight> flightList;
    /** A list of male first names */
    private final List<String> maleNames;
    /** A list of female first names */
    private final List<String> femaleNames;
    /** A list of last names */
    private final List<String> lastNames;
    /** A flag to see if the names loaded properly */
    private boolean didLoadNames;

    /** This is the default male name */
    private static final Tuple<String,String> defaultMaleName = new Tuple<>("John","Doe");
    /** This is the default female name */
    private static final Tuple<String, String> defaultFemaleName = new Tuple<>("Jane","Doe");

    /**
     * The constructor for the flight database, this should load all the information needed.
     */
    public FlightDatabase() {
        // Create temporary lists
        List<String> firstNamesM;
        List<String> firstNamesF;
        List<String> lNames;

        //We'll create the flight list now
        flightList = new ArrayList<>();
        //This is where the airline would connect to their own database.
        //For demonstration purposes, we'll just add some sample ones.
        flightList.add(new Flight("TXN-12","HOU","DAL","0400Z","0507Z"));
        flightList.add(new Flight("TXN-432","HOU","ELP","0615Z","0722Z"));
        flightList.add(new Flight("TXN-6","DAL","HOU","0530Z","0637Z"));
        flightList.add(new Flight("TXN-34","LBB","DAL","0430Z","0515Z"));
        flightList.add(new Flight("TXN-23","LBB","HOU","1200Z","1345Z"));
        flightList.add(new Flight("TXN-45","SAT","LBB","0500Z","0530Z"));
        flightList.add(new Flight("TXN-300","AUS","LBB","0500Z","0530Z"));
        flightList.add(new Flight("TXN-99","ELP","DAL","0500Z","0530Z"));

        try {
            //We'll now load in our name text files, and create a list from them.
            firstNamesM = listFromFile("FirstNames_M.txt");
            firstNamesF = listFromFile("FirstNames_F.txt");
            lNames = listFromFile("LastNames.txt");

            //We'll set this flag to true.
            didLoadNames = true;
        } catch (Exception e) {
            /*
            I decided not to make this a fatal exception. Instead of exiting here, we'll just use a default name for
            male and female passengers.
             */
            firstNamesM = null;
            firstNamesF = null;
            lNames = null;

            didLoadNames = false;
            e.printStackTrace();
            System.err.println("Could not load names from database files. ALl random reservations will use a default " +
                    "name.");
        }

        // Set the param versions of the list to the temporary ones we used.
        maleNames = firstNamesM;
        femaleNames = firstNamesF;
        lastNames = lNames;

        //We would replace this with a database connection here.
        generateRandomReservations();
    }

    /**
     *
     *
     * @param random We should just pass an existing random object to this function
     * @return A tuple of strings, with each name
     */
    private Tuple<String,String> generateRandomName(Random random) {
        if((femaleNames == null) || (maleNames == null) || (lastNames == null)) {
            // This should only show up in debug testing.
            return new Tuple<>("John","Doe");
        }

        if(random.nextBoolean()) {
            if(!didLoadNames) {
                // Only if the names database did not load properly.
                return defaultFemaleName;
            }

            //Generate a female name
            return new Tuple<>(femaleNames.get(random.nextInt(femaleNames.size())),
                    lastNames.get(random.nextInt(lastNames.size())));
        } else {
            if(!didLoadNames) {
                // Only if the names database did not load properly.
                return defaultMaleName;
            }

            //Generate a male name
            return new Tuple<>(maleNames.get(random.nextInt(maleNames.size())),
                    lastNames.get(random.nextInt(lastNames.size())));
        }

    }


    /**
     *
     * @param fileName The file to read from, should be in the same directory as this class in the source code.
     * @return A list of each line from the file.
     * @throws FlightReservationException If the file was not found.
     */
    private static List<String> listFromFile(String fileName) throws FlightReservationException {
        //We'll get a file and load it as an input stream.
        InputStream file = FlightDatabase.class.getResourceAsStream(fileName);
        if(file == null) {
            //File is null if resource was not found, which is an error condition.
            throw new FlightReservationException("Database file "+fileName+" was not found as a resource.");
        }
        //Create the buffered reader for the file.
        BufferedReader reader = new BufferedReader(new InputStreamReader(file,StandardCharsets.UTF_8));
        //Now we'll use this handy function to put each line into a list.
        return reader.lines().collect(Collectors.toList());
    }

    /**
     * This is demonstration function to generate a bunch of random reservations for seats
     * on all the flights in the flightList param.
     *
     * In the real world, the reservations would come from the database.
     */
    private void generateRandomReservations() {
        // We'll need a random instance, we'll pass it to any other functions that need one.
        Random random = new Random();
        // Loop through each flight.
        for(Flight flight: flightList) {
            //Pick a random number of reservations, and start looping through each.
            for (int i = 0; i < random.nextInt(50, 76); i++) {
                //Pick a random row
                int row = random.nextInt(30);
                //Pick a random column
                int col = random.nextInt(5);

                if(flight.getReservation(new Tuple<>(row,col)) != null) {
                    //There is already a reservation, lets not waste time generation a random reservation.
                    continue;
                }
                //Generate a random name
                Tuple<String, String> randomName = generateRandomName(random);
                //Set the reservation in the flight.
                flight.setReservation(new Reservation(new Seat(row, col),
                        new Passenger(randomName.x(), randomName.y(), random.nextInt(18, 81),
                                Passenger.createFFN("TX"),0,null)));
            }
        }
    }

}
