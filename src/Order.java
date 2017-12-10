import java.util.ArrayList;
import java.util.HashMap;

public class Order {
	private ArrayList<Widget> widgetName;
	private HashMap<String, Integer> partCounter;

	public Order(ArrayList<Widget> widgets) {
		this.setWidget(widgets);
	}

	public ArrayList<Widget> getWidget() {
		return widgetName;
	}

	public void setWidget(ArrayList<Widget> widget) {
		this.widgetName = widget;
	}

	public HashMap<String, Integer> getPartCounter() {
		return partCounter;
	}

	public void setPartCounter(HashMap<String, Integer> partCounter) {
		this.partCounter = partCounter;
	}



}
