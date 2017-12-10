import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Parse {
	
	static public ArrayList<Widget> readSpec(String file) throws IOException {
		//Widget
		ArrayList<Widget> widgets = new ArrayList<Widget>();
		try {
	 		//Get the scanner instance
			Scanner scanner = new Scanner(new File(file));
			while(scanner.hasNextLine()){
				HashMap <String, Integer> partCounter = new HashMap <String, Integer>();
				String line = scanner.nextLine();
				String[] values = line.split(":|,");				
			
				ArrayList<String> parts = new ArrayList<String>();
				Widget widget = new Widget(values[0].trim());
				
				for (String value : values) {
					if (value != values[0]) {
						String part = new String(value.trim());
						if (!partCounter.containsKey(part)){
							partCounter.put(part, 1);
						} else {
							partCounter.put(part, partCounter.get(part) + 1 );
						}
						parts.add(part);				
					}				
				}
				widget.setPartCounter(partCounter);
				widget.setPartIds(parts);
				widgets.add(widget);

			}
			scanner.close();
		
		}
		catch (FileNotFoundException fe) 
		{
			fe.printStackTrace();
		}
		
		return widgets;
		
	}

	public static ArrayList<Widget> readOrder(String file) {
		ArrayList<Widget> widgets = new ArrayList<Widget>();
		try {
	 		//Get the scanner instance
			Scanner scanner = new Scanner(new File(file));
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				Widget widget = new Widget(line.trim());
				widgets.add(widget);
			}
			scanner.close();
		
		}
		catch (FileNotFoundException fe) 
		{
			fe.printStackTrace();
		}
		return widgets;
	}
	
}
