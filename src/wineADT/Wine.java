package wineADT;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import foodPairing.FoodMatchesLibrary;

/**
 *  Public class for all wine objects.
 * 
 * @author Alexander Samaha
 * 
 * @version Last modified 14/04/2019.
 *
 */
public class Wine {
	// declaring the characteristics of our set.
	private String country;
	private String description;
	private String[] taste_notes;
	private String designation;
	private Integer rating;
	private Double price;
	private String province;
	private String[] region;
	private String variety;
	private String winery;
	private Integer unique_id;
	private String[] food_matches;
	
	// Some of these wine bottles have a review and reviewer. Not yet working.
	private String reviewer;
	private String twitter;
	
	/**
	 *  Constructor of a wine object.
	 * 
	 * @param items Contains String information of a wine bottle.
	 * @param taste Contains all the taste notes of a wine bottle.
	 * @param region Contains the region of a wine bottle.
	 * @param rating Contains the rating of a wine bottle.
	 * @param id Unique ID of a wine bottle.
	 * @param price Price of the wine bottle.
	 * @param reviewer Reviewer of the wine bottle.
	 */
	public Wine(String[] items, String[] taste, String[] region, Integer rating, Integer id, Double price, String[] reviewer) {
		country = items[0];
		description = items[1];
		designation = items[2];
		province = items[3];
		winery = items[4];
		variety = items[5];
		taste_notes = taste;
		this.region = region;
		this.rating = rating;
		this.price = price;
		unique_id = id;
		this.reviewer = reviewer[0];
		this.twitter = reviewer[1];
	}
	
	/**
	 * Set the foods that go with this wine
	 */
	public void set_food_matches() {
		this.food_matches = FoodMatchesLibrary.foodRecommend(this);
	}
	
	/**
	 *  Method accesses the rating of a wine bottle.
	 * 
	 * @return returns Integer relating to rating.
	 */
	public Integer get_rating() {
		return rating;
	}
	
	/**
	 *  Method accesses the price of a wine bottle.
	 * 
	 * @return returns Double relating to price.
	 */
	public Double get_price() {
		return price;
	}
	
	/**
	 *  Method accesses the geographical characteristic of a bottle.
	 * 
	 * @return returns a String with the region(s), province and country of a wine bottle.
	 */
	public String get_geo() {
		String location = new String();
		for (int i = 0; i < region.length; i++) {
			location += region[i] + ", ";
		}
		return location + ", " + province + ", " + country;
	}
	
	/**
	 *  Method accesses the original winery where the bottle originates.
	 * 
	 * @return returns String relating to winery.
	 */
	public String get_winery() {
		return winery;
	}
	
	/**
	 *  Method accesses the description of a wine as given by an expert.
	 * 
	 * @return returns String relating to description.
	 */
	public String get_description() {
		return description;
	}
	
	/**
	 *  Method accesses the designation of a wine bottle (name).
	 * 
	 * @return returns String relating to designation.
	 */
	public String get_designation() {
		return designation;
	}
	
	/**
	 *  Method accesses the variety of a wine bottle.
	 * 
	 * @return returns String relating to variety of a wine bottle.
	 */
	public String get_variety() {
		return variety;
	}
	
	/**
	 *  Method gives a string of all the taste notes of a wine.
	 * 
	 * @return A string with the taste notes of a wine.
	 */
	public String get_taste_notes() {
		String note_string = new String();
		for (int i = 0; i < taste_notes.length; i++){
			if (i + 1 >= taste_notes.length)
				note_string += taste_notes[i];
			else {
				note_string += taste_notes[i] + ", ";
			}
		}
		return note_string;
	}
	
	/**
	 * Method inserts taste notes from patterns in TasteNoteLibrary.
	 */
	public void insert_taste_notes() {
		String pattern = "";
		String description = "";
		ArrayList<String> tasters = new ArrayList<String>();
		for (int i = 0; i < TasteNoteLibrary.get_patterns().length; i++) {
			pattern = TasteNoteLibrary.get_patterns()[i];
			description = this.description.toLowerCase();
			Pattern pt = Pattern.compile(pattern);
			Matcher st = pt.matcher(description);
			int count = 0;
			while (st.find()) {
				count++;
			}
			if (count > 0) {
				tasters.add(pattern);
			}
			
		}
		/*
		if (tasters.isEmpty()) {
			tasters.add("no_notes");
		}
		*/
		
		String[] new_notes = new String[tasters.size()];
		new_notes = tasters.toArray(new_notes);
		this.taste_notes = new_notes;
	}
	
	/**
	 *  Method accesses the raw array containing all the taste notes.
	 * 
	 * @return A String array with all the taste notes of a wine bottle.
	 */
	public String[] get_taste_noteslist() {
		return taste_notes;
	}
	
	/**
	 *  Method accesses the country of origin of a wine bottle.
	 * 
	 * @return String corresponding to country of a wine object.
	 */
	public String get_country() {
		return country;
	}
	
	/**
	 * Retrieves the list of food matches.
	 * 
	 * @return A String array corresponding to food matches.
	 */
	public String[] get_food_matches() {
		return food_matches;
	}
	
	/**
	 * Transforms list into string format for ease of viewing.
	 * 
	 * @return A string corresponding to food matches.
	 */
	public String toString_food_matches() {
		String current = "[";
		for (int i = 0; i < this.get_food_matches().length; i++) {
			if (this.get_food_matches().length - 1 == i) {
				current += this.get_food_matches()[i] + "]";
			}
			else {
				current += this.get_food_matches()[i] + ", ";
			}
		}
		return current;
	}
	
	/**
	 *  Method accesses the province of origin of a wine bottle.
	 * 
	 * @return String corresponding to province of a wine bottle.
	 */
	public String get_province() {
		return province;
	}
	
	/**
	 *  Method accesses the name of the expert who reviewed the wine bottle.
	 * 
	 * @return returns a String relating to a name.
	 */
	public String get_reviewer_name() {
		return reviewer;
	}
	
	/**
	 *  Method accesses the uniqueID of a wine bottle.
	 * 
	 * @return An Integer comparable of an ID number for each wine bottle.
	 */
	public Integer get_uniqueID() {
		return unique_id;
	}
	
	/**
	 *  Method accesses the reviewer's name and twitter tag.
	 * 
	 * @return A string corresponding to name and twitter tag.
	 */
	public String get_name_and_twitter() {
		String combined_name = new String();
		combined_name = reviewer + ", " + twitter;
		return combined_name;
	}
	
	/**
	 * Return the string representation of the wine
	 * @return the string representation of the wine
	 */
	public String toString() {
		String temp = unique_id + ": " + designation 
					  + ", Taste Note - [" + get_taste_notes() 
					  + "], " + variety 
					  + ", $" + price 
					  + ", Rating - " + rating + ", "
					  + description + ", "
					  + winery + ", "
				      + "Location - [" + get_geo() + "]";
		return temp;
	}
	
}
