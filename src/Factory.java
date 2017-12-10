import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WidgetFactory.*;
/**
 * 
 * @author Revani Govender
 * Model a Factory that builds widgets composed of parts
 * WidgetFactory.jar is a warehouse simulator
 * 
 * Customers send a list of widgets and parts that are comprised
 * Example:
 * motorcycle: wheel, wheel, engine, seat, handlebar
 *
 * Another file - 'order file' that contains list of widgets that are needed and the order
 * Example
 * car
 * car
 * truck
 * 
 * This project aims to model a factory that will assemble widgets from the spec and order files
 * Output will be a list of the assembled widgets and the parts used to build them
 * 
 * It is assumed that if a part is not available, an order will be made and then the order will complete as received
 * Orders will be processed in order
 * 
 * Currently the spec and order files are hardcoded here
 * 
 * 
 */

/*
 * Factory work flow:
 * Pre Build warehouse stock 
 * 1. Receive order
 *   1a. parse the order and determine parts needed
 * 2. Assign parts from current inventory
 *   2a. Alert if part not found
 * 3. Build widget
 *   3a. track serial numbers of parts

 */
public class Factory {
							
	public static void main(String[] args) throws PartNotFoundException, IOException {
		//warehouse = collection of Parts
		WidgetFactory.Warehouse warehouse = new WidgetFactory.Warehouse();	
		//the spec and order files are hardcoded here - Could ask for user input
		//User input would require making sure the format of the file is correct
		Order spec = new Order(Parse.readSpec("spec.txt"));
		Order order = new Order(Parse.readOrder("order.txt"));
		HashMap <String, Integer> partCounter = new HashMap <String, Integer>();
		//loop through the order of widgets
		for(Widget widget: spec.getWidget()){	
			//check if parts are available, if not then order them
			for (String part : widget.getPartIds()) {
				if (!warehouse.available(part)) {
					warehouse.order(part);
				}
			}
		}
		
		//for each widget in the order
		for (Widget widgetOrder: order.getWidget()) {
			/* Could potentially track the time taken for each widget built
			 * as well as cost factors
			 */
			ArrayList<Part> partList = new ArrayList<Part>();
			HashMap<String, Integer> partMap = new HashMap<String, Integer>();
			for(Widget widget: spec.getWidget()){
				if (widget.getWidgetName().equals(widgetOrder.getWidgetName())) {
					 partMap = widget.getPartCounter();
				}
			}
			for (Map.Entry<String, Integer> parts : partMap.entrySet()) {	
				for (int i=0; i < parts.getValue(); i ++) {
					if (!partCounter.containsKey(parts.getKey())){
						partCounter.put(parts.getKey(), 1);
					} else {
						partCounter.put(parts.getKey(), partCounter.get(parts.getKey()) + 1 );
					}
					try {
						//retrieve the part and set the part list to be added to the order
						// Would be nice to know the number left in the inventory (subtract part from inventory)
						partList.add(new Part(parts.getKey(), warehouse.retreive(parts.getKey()).getSerialNo()));
					} catch (PartNotFoundException e) {
						//If tracking inventory numbers, could order more parts here.
						System.out.println("Could not retrieve " + parts.getKey() + ".\n"
								+ "Someone should order it so we can make " + widgetOrder.getWidgetName());
					}
				}
		    }
			/*
			 * setting the order partCounter here
			 * this is so this function can change to allow multiple orders at a time and update 
			 * the number of total parts per order
		   	 * 
			*/
			order.setPartCounter(partCounter);
			widgetOrder.setPart(partList);
		}
		
		
		for (Widget widgetOrder: order.getWidget()) {
			System.out.println(widgetOrder.getWidgetName());
			for (Part widgetOrderParts: widgetOrder.getPart()) {
				System.out.println("    " + widgetOrderParts.getId() + " " + widgetOrderParts.getSerialNo());
			}
		}
		System.out.println("\nTotal number of parts used:");
		System.out.println(order.getPartCounter().entrySet());
	}	
				
	
	
}
