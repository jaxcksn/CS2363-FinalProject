# Class Seat [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Seat.java)  



Access: public  
Description:  
 > Represents a seat on the airplane and it's location.  

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

#### int row [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Seat.java#L)



+ Access: public  

#### int col [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Seat.java#L)



+ Access: public  

#### String seatClass [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Seat.java#L)



+ Access: private  
+ Modifiers: final 

## Methods

### calculateSeatClass [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Seat.java#L34)

+ Description: Method to assign seat based on row first three rows will be Business next 5 will be Premium and rest will be Economy.   
+ Access: public  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| row | int | The row of the seat  |  


### getSeatClass [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/Seat.java#L48)

+ Description: Getter for the seat param   
+ Access: public  
+ return: String  

This method has no parameters.  


