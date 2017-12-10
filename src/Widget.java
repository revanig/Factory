import java.util.ArrayList;
import java.util.HashMap;

import WidgetFactory.Part;

public class Widget {
	private String widgetName;
	private ArrayList<String> partIds;
	private HashMap<String, Integer> partCounter;
	private ArrayList<Part> part;
	// each widget is made of the name and parts 
	public Widget (String widgetName) {
		this.setWidgetName(widgetName);
	}

	public String getWidgetName() {
		return widgetName;
	}

	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}


	public ArrayList<String> getPartIds() {
		return partIds;
	}

	public void setPartIds(ArrayList<String> partIds) {
		this.partIds = partIds;
	}
	
	public HashMap<String, Integer> getPartCounter() {
		return partCounter;
	}

	public void setPartCounter(HashMap<String, Integer> partCounter) {
		this.partCounter = partCounter;
	}
	
	public ArrayList<Part> getPart() {
		return part;
	}
	public void setPart(ArrayList<Part> part) {
		this.part = part;
	}
	
	
}
