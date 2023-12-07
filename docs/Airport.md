# Class Airport [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Airport.java)  



Access: public  
Description:  
 > Represents an Airfield or Airport has a variety of utility methods to help the UI show information about the airfield. This is mostly immutable.  

package: FlightReservationSystem  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
  </ul>  
</details>  

## Member Variables

#### String iataCode [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Airport.java#L)



+ Access: private  
+ Modifiers: final 

#### String fullName [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Airport.java#L)



+ Access: private  
+ Modifiers: final 

#### String cityName [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Airport.java#L)



+ Access: private  
+ Modifiers: final 

#### Coordinates location [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Airport.java#L)



+ Access: private  
+ Modifiers: final 

## Methods

### Coordinates [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Airport.java#L18)

+ Description: This is a record used to represent a lat and lon coordinate.   
+ Access: public  
+ return: record  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| lat | Double | The latitude  |  
| lon | Double | The longitude  |  


### iataCode [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Airport.java#L37)

+ Description: Public getter method for the iataCode   
+ Access: public  
+ return: String  

This method has no parameters.  


### fromIATA [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Airport.java#L49)

+ Description: Returns an airport object for commonly used Iata codes by the airlines.   
+ Access: public  
+ Modifiers: static 
+ return: Airport  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| iataCode | String | The known iata code  |  


### getRouteString [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Airport.java#L116)

+ Description: Utility method for making a stylized string representing a route between two airports.   
+ Access: public  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| departure | Airport | The departure airport  |  
| arrival | Airport | The arrival airport  |  


### getRouteDistance [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Airport.java#L128)

+ Description: Gets the distance between two airfields in miles. This uses Haversine formula to calculate the distance which accounts for the earths curvature.   
+ Access: public  
+ Modifiers: static 
+ return: double  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| departure | Airport | The departure airport  |  
| arrival | Airport | The arrival airport  |  


