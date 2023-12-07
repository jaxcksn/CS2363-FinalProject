# Class SeatmapController [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java)  

Access: public  
Description:  
 > This is most complicated in the the controller for the seatmapView.fxml file. It has a lot of moving parts but it shouldn't be too hard to follow.  

package: FlightReservationSystem.GUI  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>FlightReservationSystem.*</li>
<li>FlightReservationSystem.util.Tuple</li>
<li>javafx.event.ActionEvent</li>
<li>javafx.fxml.FXML</li>
<li>javafx.scene.control.*</li>
<li>javafx.scene.layout.GridPane</li>
<li>javafx.css.PseudoClass</li>
  </ul>  
</details>  

## Member Variables
#### ControllerListener homeControllerListener [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L)



+ Access: private  

####  Flight activeFlight  [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L)



+ Access: private  

####  Integer> lastLoc  [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L)



+ Access: private  

####  PseudoClass FILLED_PSEUDO  [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L)



+ Access: public  
+ Modifiers: static final 

####  PseudoClass SELECT_PSEUDO  [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L)



+ Access: public  
+ Modifiers: static final 

## Methods

### setIsFormEditable [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L74)

+ Description: Sets if the user should be allowed to edit the reservation form changes various labels based on editable status.   
+ Access: private  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| b | boolean |  |  


### onEditButton [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L106)

+ Description:   
+ Access: private  
+ return: void  

This method has no parameters.  


### onBookButton [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L115)

+ Description:   
+ Access: private  
+ return: void  

This method has no parameters.  


### updateCurrentReservation [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L130)

+ Description: Updates the currently selected reservation with the form data.   
+ Access: private  
+ return: Reservable  

This method has no parameters.  


### bookNewReservation [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L169)

+ Description: Creates a new reservation from the form data.   
+ Access: private  
+ return: Reservation  

This method has no parameters.  


### setFlight [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L192)

+ Description: Sets the flight for the view must be called before most of the other functions.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| flight | Flight |  |  


### setFlightUpdater [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L206)

+ Description: Sets the HomeController listener to one passed to the function   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| listener | ControllerListener |  |  


### updateFlight [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L217)

+ Description: This method updates the reservation in the active flight and then sends the updated flight back to the home controller to persist the changes.   
+ Access: private  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| updatedReservation | Reservable | The changed reservation  |  
| reservationLocation | Integer> |  |  


### initialize [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L234)

+ Description:   
+ Access: public  
+ return: void  

This method has no parameters.  


### addSeats [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L250)

+ Description: This method adds all the seat buttons on top of the image dynamically based on the flight details. It also sets the actions and pseudo-classes for the buttons.   
+ Access: public  
+ return: void  

This method has no parameters.  


### getSeatLabel [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L319)

+ Description: A quick utility method for generating a seat label ID   
+ Access: private  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| row | int | The row of the seat  |  
| col | int | The column of the seat.  |  


### validateForm [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L327)

+ Description: Makes sure all the form fields that you can type into are filled.   
+ Access: private  
+ return: boolean  

This method has no parameters.  


### handleSeatAction [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/GUI/SeatmapController.java#L337)

+ Description: This is perhaps the most complicated method in the project it's the actual method that is called when you press on a seat button.   
+ Access: private  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| event | ActionEvent | The click event that called the method  |  
| loc | Integer> |  |  


