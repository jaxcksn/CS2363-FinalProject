# Class HomeController [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemGUI/HomeController.java)  



Access: public  
Description:  
 > This controls the Home view for the program. It is closely linked with the homePage.fxml file so it's recommended you have both files close by. Because of the nature of controllers and their habit for being less readable I will try my best to comment this.  

Interfaces:  
- ControllerListener  

package: FlightReservationSystem.GUI  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>FlightReservationSystem.Flight</li>
<li>javafx.collections.FXCollections</li>
<li>javafx.collections.ObservableList</li>
<li>javafx.fxml.FXML</li>
<li>javafx.fxml.FXMLLoader</li>
<li>javafx.scene.Parent</li>
<li>javafx.scene.Scene</li>
<li>javafx.scene.control.Button</li>
<li>javafx.scene.control.TableColumn</li>
<li>javafx.scene.control.TableView</li>
<li>javafx.scene.control.cell.PropertyValueFactory</li>
<li>javafx.stage.Stage</li>
<li>java.util.List</li>
<li>java.util.Objects</li>
  </ul>  
</details>  

## Member Variables


## Methods

### onFlightUpdated [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemGUI/HomeController.java#L55)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| updatedFlight | Flight |  |  


### initialize [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemGUI/HomeController.java#L71)

+ Description:   
+ Access: public  
+ return: void  

This method has no parameters.  


### setupHomeView [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemGUI/HomeController.java#L89)

+ Description: This method is used to set up all the needed data values for the view and is called before the view is actually created.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| flightList | List<Flight> |  |  


### showSeatmapView [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystemGUI/HomeController.java#L101)

+ Description:   
+ Access: private  
+ return: void  

This method has no parameters.  


