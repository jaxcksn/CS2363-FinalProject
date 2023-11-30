package FlightReservationSystem;

import FlightReservationSystem.util.Pair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class is where the airline would implement their own connection
 * to their flight database and pull flights. Which is outside of the scope of this project.
 * For this demonstration we'll just create a list of flights.
 */
public final class FlightDatabase {
    /**
     * This is the list of all flights
     */
    public final List<Flight> flightList;

    private final List<String> maleNames;
    private final List<String> femaleNames;
    private final List<String> lastNames;

    public FlightDatabase() {
        List<String> firstNamesM;
        List<String> firstNamesF;
        List<String> lNames;

        flightList = new ArrayList<>();
        flightList.add(new Flight("TXN-12","HOU","DAL","0400Z","0507Z"));
        flightList.add(new Flight("TXN-432","HOU","DAL","0615Z","0722Z"));
        flightList.add(new Flight("TXN-6","DAL","HOU","0530Z","0637Z"));
        flightList.add(new Flight("TXN-34","LBB","DAL","0430Z","0515Z"));
        flightList.add(new Flight("TXN-23","LBB","HOU","1200Z","1345Z"));
        flightList.add(new Flight("TXN-45","SAT","LBB","0500Z","0530Z"));

        try {
            InputStream file = getClass().getResourceAsStream("FirstNames_M.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(file), StandardCharsets.UTF_8));
            firstNamesM = reader.lines().collect(Collectors.toList());

            file = getClass().getResourceAsStream("FirstNames_F.txt");
            reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(file), StandardCharsets.UTF_8));
            firstNamesF = reader.lines().collect(Collectors.toList());

            file = getClass().getResourceAsStream("LastNames.txt");
            reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(file), StandardCharsets.UTF_8));
            lNames = reader.lines().collect(Collectors.toList());

        } catch (Exception e) {
            firstNamesM = null;
            firstNamesF = null;
            lNames = null;
            //TODO: Replace with exception.
            e.printStackTrace();
        }


        maleNames = firstNamesM;
        femaleNames = firstNamesF;
        lastNames = lNames;
        generateRandomReservations();
    }

    private Pair<String,String> generateRandomName(Random random) {
        if((femaleNames == null) || (maleNames == null) || (lastNames == null)) {
            // This should only show up in debug testing.
            return new Pair<>("John","Doe");
        }

        if(random.nextBoolean()) {
            //Generate a female name
            return new Pair<>(femaleNames.get(random.nextInt(femaleNames.size())),
                    lastNames.get(random.nextInt(lastNames.size())));
        } else {
            //Generate a male name
            return new Pair<>(maleNames.get(random.nextInt(maleNames.size())),
                    lastNames.get(random.nextInt(lastNames.size())));
        }

    }

    /**
     * This is demonstration function to generate a bunch of random reservations for seats
     * on all the flights in the flightList param.
     * In the real world, the reservations would come from the database.
     */
    private void generateRandomReservations() {
        Random random = new Random();
        for(Flight flight: flightList) {
            for(int i = 0; i < random.nextInt(50,76); i++) {
                int row = random.nextInt(30);
                int col = random.nextInt(5);

                Pair<String,String> randomName = generateRandomName(random);
                flight.setReservation(new Reservation(new Seat(row,col),
                        new Passenger(randomName.getX(), randomName.getY(), random.nextInt(18,81))));
            }
        }

        return;
    }

}
