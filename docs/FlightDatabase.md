# Class FlightDatabase [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlightDatabase.java)  



Access: public  
Description:  
 > This is where the airline would implement their own connection to their flight database and pull flights. Which is outside the scope of this project. For this demonstration we'll just create a list of  

package: FlightReservationSystem  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>FlightReservationSystem.util.Tuple</li>
<li>java.io.BufferedReader</li>
<li>java.io.InputStream</li>
<li>java.io.InputStreamReader</li>
<li>java.nio.charset.StandardCharsets</li>
<li>java.util.ArrayList</li>
<li>java.util.List</li>
<li>java.util.Random</li>
<li>java.util.stream.Collectors</li>
  </ul>  
</details>  

## Member Variables

#### List<Flight> flightList [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlightDatabase.java#L)



+ Access: public  
+ Modifiers: final 

#### List<String> maleNames [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlightDatabase.java#L)



+ Access: private  
+ Modifiers: final 

#### List<String> femaleNames [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlightDatabase.java#L)



+ Access: private  
+ Modifiers: final 

#### List<String> lastNames [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlightDatabase.java#L)



+ Access: private  
+ Modifiers: final 

#### boolean didLoadNames [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlightDatabase.java#L)



+ Access: private  

####  String> defaultMaleName  [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlightDatabase.java#L)



+ Access: private  
+ Modifiers: static final 

####  String> defaultFemaleName  [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlightDatabase.java#L)



+ Access: private  
+ Modifiers: static final 

## Methods

### generateRandomName [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlightDatabase.java#L98)

+ Description:   
+ Access: private  
+ return: String>  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| random | Random | We should just pass an existing random object to this function  |  


### listFromFile [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlightDatabase.java#L133)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: A list of each line from the file.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| fileName | String | The file to read from should be in the same directory as this in the source code.  |  


### generateRandomReservations [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemFlightDatabase.java#L152)

+ Description:   
+ Access: private  
+ return: void  

This method has no parameters.  


