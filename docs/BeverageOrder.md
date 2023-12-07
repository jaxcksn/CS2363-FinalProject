# Class BeverageOrder [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/BeverageOrder.java)  
Access: public  
Description:  
 > A manager for a reservations beverage order.  

package: FlightReservationSystem  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>java.util.ArrayList</li>
<li>javafx.collections.FXCollections</li>
<li>javafx.collections.ObservableList</li>
  </ul>  
</details>  

## Member Variables

#### ArrayList<String> beverageOptions [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/BeverageOrder.java#L)
+ Access: private  

#### String beverageSelection [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/BeverageOrder.java#L)

+ Access: private  

## Methods

### getBeverageOptions [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/BeverageOrder.java#L48)

+ Description: Getter for the beverage options list.   
+ Access: public  
+ return: ArrayList<String>  

This method has no parameters.  


### getBeverageList [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/BeverageOrder.java#L57)

+ Description: Gets the beverage options ArrayList as an Observable list for use in JavaFX code.   
+ Access: public  
+ return: ObservableList<String>  

This method has no parameters.  


### getForRow [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/BeverageOrder.java#L66)

+ Description: Gets all the available beverages for a row.   
+ Access: public  
+ Modifiers: static 
+ return: ObservableList<String>  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| row | int | The row to get beverages for  |  


### setBeverageSelection [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/BeverageOrder.java#L91)

+ Description: Sets the selected beverage.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| selection | String | The selected beverage  |  


### getBeverageSelection [[src]](https://github.com/jaxcksn/CS2363-FinalProject/tree/main/src/FlightReservationSystem/BeverageOrder.java#L102)

+ Description: Getter for the beverage selection.   
+ Access: public  
+ return: String  

This method has no parameters.  


