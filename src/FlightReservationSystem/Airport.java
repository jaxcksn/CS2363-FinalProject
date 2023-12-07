package FlightReservationSystem;

/**
 * Represents an Airfield or Airport, has a variety of utility methods to help the UI
 * show information about the airfield.
 *
 * This class is mostly immutable.
 * @author Jackson Casey
 */
public final class Airport {
    /**
     * This is a record used to represent a lat and lon coordinate.
     * @param lat The latitude
     * @param lon The longitude
     *
     * @author Jackson Casey
     */
    public record Coordinates(Double lat, Double lon) {}


    /** The iata code for the airfield.<br/>
     * <a href="https://en.wikipedia.org/wiki/IATA_airport_code">More Information on Iata Codes</a>
     */
    private final String iataCode;
    /** The full name of the airfield */
    private final String fullName;
    /** The name of the city the airport is in */
    private final String cityName;
    /** The geographical location of the airfield */
    private final Coordinates location;


    /**
     * Public getter method for the iataCode
     * @return The iata code for the airport.
     */
    public String iataCode() {
        return iataCode;
    }


    /**
     * Returns an airport object for commonly used Iata codes by the airlines.
     * @param iataCode The known iata code
     * @return A new Airport object, if cityName and fullName are "Unknown" then you'll have to create the aiport via
     * the
     * constructor.
     */
    public static Airport fromIATA(String iataCode) {
        String fullName;
        Coordinates coordinates;
        String cityName;

        switch(iataCode) {
            case "HOU":
                fullName = "William P Hobby Airport";
                cityName = "Houston";
                coordinates = new Coordinates(29.6457998,-95.2772316);
                break;
            case "DAL":
                fullName = "Dallas Love Field Airport";
                cityName = "Dallas";
                coordinates = new Coordinates(32.8459447,-96.8508767);
                break;
            case "LBB":
                fullName = "Lubbock Preston Smith International Airport";
                cityName = "Lubbock";
                coordinates = new Coordinates(33.6636667,-101.8205556);
                break;
            case "AUS":
                fullName = "Austin-Bergstrom International Airport";
                cityName = "Austin";
                coordinates = new Coordinates(30.1945272,-97.6698761);
                break;
            case "SAT":
                fullName = "San Antonio International Airport";
                cityName = "San Antonio";
                coordinates = new Coordinates(29.5339583,-98.4690569);
                break;
            case "ELP":
                fullName = "El Paso International Airport";
                cityName = "El Paso";
                coordinates = new Coordinates(31.8073333,-106.3763611);
                break;
            default:
                //If we don't recognize the code.
                fullName = "Unknown";
                coordinates = null;
                cityName = "Unknown";
        }

        return new Airport(iataCode,fullName,coordinates,cityName);
    }

    /**
     * Constructor for an Airport object
     * @param iataCode The iata code of the airport
     * @param fullName The fullname of the airfield
     * @param location Pass in the coordinates of the airfield, see {@link FlightReservationSystem.Airport.Coordinates}
     * @param cityName The short name of the city the airfield is in.
     */
    public Airport(String iataCode, String fullName, Coordinates location, String cityName) {
        this.iataCode = iataCode;
        this.fullName = fullName;
        this.location = location;
        this.cityName = cityName;

    }

    /**
     * Utility method for making a stylized string representing a route between two airports.
     * @param departure The departure airport
     * @param arrival The arrival airport
     * @return A string of the following format: DepatureIataCode - ArrivalIataCode
     */
    public static String getRouteString(Airport departure, Airport arrival) {
        return departure.cityName+" to "+arrival.cityName;
    }

    /**
     * Gets the distance between two airfields, in miles.
     * This uses Haversine formula to calculate the distance, which accounts for the earths curvature.
     *
     * @param departure The departure airport
     * @param arrival The arrival airport
     * @return The distance between the airfields, in miles.
     */
    public static double getRouteDistance(Airport departure, Airport arrival) {
        //Get needed distances and measures
        double dLat = Math.toRadians(arrival.location.lat() - departure.location.lat());
        double dLon = Math.toRadians(arrival.location.lon() - departure.location.lon());
        double arrivalLatRadians = Math.toRadians(arrival.location.lat());
        double departureLatRadians = Math.toRadians(departure.location.lat());

        //Inner part of square root
        double x = Math.pow(Math.sin(dLat/2),2) + Math.pow(Math.sin(dLon/2),2)
                        * Math.cos(departureLatRadians) * Math.cos(arrivalLatRadians);

        //Calculate rest of equation.
        double y = 2 * Math.asin(Math.sqrt(x));

        //3958.8 is the radius of the earth in miles.
        return 3958.8 * y;
    }
}
