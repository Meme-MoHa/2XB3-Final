package wineADT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Delivers the method that reads our dataset.
 * 
 * @author Alexander Samaha
 * 
 * @version Last modified 14/04/2019.
 *
 */
public class Read {
	public static Wine[] wines;
	public static Wine[] idSorted;
	
	/**
	 * Initializer function that set up the two static variables of the class
	 */
	public static void init() {
		wines = read();
		read_tasteNotes(wines);
		idSorted = duplicate(wines);
	}
	
	/**
	 * Reads files from dataset and creates a wine ADT object for the wine bottle.
	 * 
	 * @return An array containing wine objects.
	 * @throws IOException fails if there is a file error.
	 */
	private static Wine[] read() {
		// We can add files we would like to parse in the following array. We use an array list
		// because it allows us to add dynamically.
		String[] file_adr = { "data/winemag-data_first150k.txt", "data/winemag-data-130k-v2.csv" };
		ArrayList<Wine> arr_list = new ArrayList<Wine>();
		
		int k = 0;
		while (k < file_adr.length) {
			boolean flag = false;
			if (file_adr[k].endsWith(".csv")) {
				flag = true;
			}
			File f = new File(file_adr[k]);
			Scanner sc = null;
			try {
				sc = new Scanner(f, "UTF-8");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sc.nextLine();
			Integer id_count = 0;
			if(!flag) {
				while (sc.hasNextLine()) {
					String scanned = sc.nextLine();
					// if there is a blank line, skip it before a fail.
					if (scanned.isEmpty()) {
						scanned = sc.nextLine();
					}
					// use this instead of StringTokenizer because it won't skip empty fields.
					String[] st = scanned.split(",");
					
					/* was put here to make sure all fields show up.
					String toString = "";
					for (int i = 0; i < st.length; i++) {
						toString += st[i] + ", ";
					}
					*/
	
					String country = st[1];
					String description = "";
					// This piece grabs our entire description! this paragraph has our delimiters so it gets split.
					int count = 0;
					for (int i = 2; i < (st.length - 10) + 2; i++) {
						if (st[i].endsWith("\"")) {
							description += st[i];
						}
						else {
							description += st[i] + ", ";
						}
						count++;
						
					}
					
					String designation = st[count+2];
					
					// next two fields will fail if the field is empty, so make sure we assign it something.
					Integer points = !(st[count+3].isEmpty()) ? Integer.parseInt(st[count+3]) : -1;
					
					Double price = !(st[count+4].isEmpty()) ? Double.parseDouble(st[count+4]) : -1.0;
					
					String province = st[count+5];
					String[] region = {
							st[count+6],
							st[count+7]
					};
					String variety = st[count+8];
					String winery = st[count+9];
					//System.out.println(id_count);
					// unique ID system because some wine bottles have empty names.
					Integer unique_id = id_count++;
					
					String[] items = {
							country,
							description,
							designation,
							province,
							winery,
							variety
					};
					
					String[] reviewer = {
							"",
							""
					};
					
					String[] taste = {};
					// Object constructor.
					Wine curr_obj = new Wine(items, taste, region, points, unique_id, price, reviewer);
					
					arr_list.add(curr_obj);
					
				}
				
				k++;
				sc.close();
			}
			else {
				while (sc.hasNextLine()) {
					String scanned = sc.nextLine();
					// if there is a blank line, skip it before a fail.
					if (scanned.isEmpty()) {
						scanned = sc.nextLine();
					}
					// use this instead of StringTokenizer because it won't skip empty fields.
					String[] st = scanned.split(",");
					//System.out.println(arr_list.size());
					id_count = arr_list.size();
					/* was put here to make sure all fields show up.
					String toString = "";
					for (int i = 0; i < st.length; i++) {
						toString += st[i] + ", ";
					}
					*/
					//System.out.println(st[0]);
					/*if(Integer.parseInt(st[0]) == 30350) {
						break;
					}
					*/
					String country = st[1];
					String description = "";
					// This piece grabs our entire description! this paragraph has our delimiters so it gets split.
					int count = 0;
					for (int i = 2; i < (st.length - 12) + 2; i++) {
						if (st[i].endsWith("\"")) {
							description += st[i];
						}
						else {
							description += st[i] + ", ";
						}
						count++;
						
					}
					
					String designation = st[count+2];
					
					// next two fields will fail if the field is empty, so make sure we assign it something.
					Integer points = !(st[count+3].isEmpty()) ? Integer.parseInt(st[count+3]) : -1;
					
					Double price = !(st[count+4].isEmpty()) ? Double.parseDouble(st[count+4]) : -1.0;
					
					String province = st[count+5];
					String[] region = {
							st[count+6],
							st[count+7]
					};
					String taster_name = st[count+8];
					String taster_handle = st[count+9];
					String variety = st[count+10];
					String winery = st[count+11];
					//System.out.println(id_count);
					// unique ID system because some wine bottles have empty names.
					Integer unique_id = id_count++;
					
					String[] items = {
							country,
							description,
							designation,
							province,
							winery,
							variety
					};
					String[] reviewer = {
							taster_name,
							taster_handle
					};
					
					String[] taste = {};
					// Object constructor.
					Wine curr_obj = new Wine(items, taste, region, points, unique_id, price, reviewer);
					
					arr_list.add(curr_obj);
					
				}
				
				k++;
				sc.close();
			}
		}
		// We no longer need an array list. we have our size required. Put into an array.
		Wine[] array_wines = new Wine[arr_list.size()];
		array_wines = arr_list.toArray(array_wines);
		
		return array_wines;
	
	}
	
	/**
	 * Inserts the taste note (from pattern in TasteNoteLibrary) for the wines
	 * @param array_wines Wines which the taste note is being inserted
	 */
	public static void read_tasteNotes(Wine[] array_wines) {
		for (int i = 0; i < array_wines.length; i++) {
			array_wines[i].insert_taste_notes();
			// System.out.println(array_wines[i].get_taste_notes());
		}
		return;
	}
	
	//Duplicate an array of wines
	private static Wine[] duplicate(Wine[] wine) {
		Wine[] temp = new Wine[wine.length];
		for (int i = 0; i < wine.length; i++)
			temp[i] = wine[i];
		return temp;
	}
	
	/*
	public static void main(String[] args) {
		Wine[] array = Read.wines;
		for (int i = 0; i < array.length; i++) {
			if (array[i].get_uniqueID() != i) {
				System.out.println(i);
				break;
				//System.out.println("does not work");
			}
		}
		//System.out.println("works");
	}
	*/
	
	
}
