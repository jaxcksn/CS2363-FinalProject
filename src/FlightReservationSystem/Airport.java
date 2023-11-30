package FlightReservationSystem;

public class Airport {
    public String iataCode;
    public String fullName;


    public Airport(String iataCode) {
        this.iataCode = iataCode;

        switch(iataCode) {
            case "HOU":
                fullName = "Houston Hobby";
                break;
            case "DAL":
                fullName = "Dallas Love Field";
                break;
            case "LBB":
                fullName = "Lubbock Preston Smith International";
                break;
            case "AUS":
                fullName = "Austin-Bergstrom International";
                break;
            case "SAT":
                fullName = "San Antonio International";
                break;
            case "ELP":
                fullName = "El Paso International";
                break;
            default:
                fullName = "Unknown";
        }

    }

    public static String getRouteString(Airport departure, Airport arrival) {
        return departure.iataCode+" - "+arrival.iataCode;
    }
}
