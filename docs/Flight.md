# Class Flight [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java)  



Access: public  
Description:  
 > Represents a scheduled flight for the airline this is probably the most used in the entire program. Flights by design are immutable.  

package: FlightReservationSystem  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>FlightReservationSystem.util.Tuple</li>
  </ul>  
</details>  

## Member Variables

#### String ident [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L)



+ Access: public  
+ Modifiers: final 

#### Airport departs [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L)



+ Access: public  
+ Modifiers: final 

####   [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L20)

 > /** The airport the flight arrives to*/  

+ Access: public  
+ Modifiers: final 

#### String departTime [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L)



+ Access: public  
+ Modifiers: final 

#### String arrivalTime [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L)



+ Access: public  
+ Modifiers: final 

#### Reservable[][] reservations [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L)



+ Access: private  
+ Modifiers: final 

#### double flightDistance [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L)



+ Access: private  
+ Modifiers: final 

## Methods

### getReservations [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L56)

+ Description: Gets all the reservations   
+ Access: public  
+ return: Reservable[][]  

This method has no parameters.  


### getSeatmapDimensions [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L64)

+ Description: Gets the dimensions of the reservation array.   
+ Access: public  
+ return: Integer>  

This method has no parameters.  


### getReservation [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L74)

+ Description: Gets a reservation at a location in the reservations array.   
+ Access: public  
+ return: Reservable  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| getAt | Integer> | The row and column where the reservation should be pulled from  |  


### setReservation [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L86)

+ Description: Sets a location in the reservations array to the given reservable. This overload is just for readability.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| updatedReservable | Reservable | The reservable to place in the array.  |  
| resIndex | Integer> | Where the reservation should be updated at.  |  


### setReservation [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L102)

+ Description: Sets a location in the reservations array to the given reservable. This overload uses the seat number in the reservable.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| updatedReservable | Reservable |  |  


### getIdent [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L111)

+ Description: Getter for the ident param. Used by JavaFX.   
+ Access: public  
+ return: String  

This method has no parameters.  


### getDeparts [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L119)

+ Description: Getter for the departs param. Used by JavaFX.   
+ Access: public  
+ return: String  

This method has no parameters.  


### getArrives [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L127)

+ Description: Getter for the arrives param. Used by JavaFX.   
+ Access: public  
+ return: String  

This method has no parameters.  


### getDepartTime [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L135)

+ Description: Getter for the departTime param. Used by JavaFX.   
+ Access: public  
+ return: String  

This method has no parameters.  


### getArrivalTime [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L143)

+ Description: Getter for the arrivalTime param. Used by JavaFX.   
+ Access: public  
+ return: String  

This method has no parameters.  


### getFlightDistance [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlight.java#L151)

+ Description: Getter for the flightDistance param. Used by JavaFX.   
+ Access: public  
+ return: int  

This method has no parameters.  


