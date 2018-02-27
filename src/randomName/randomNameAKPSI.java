package randomName;

import java.util.ArrayList;
import java.util.Arrays;

/***********************************************************************
 * This class is the base to random generating the current active 
 * chapter memebers. Brothers are to be assigned to keep up with inner 
 * relations of the fraternity.
 * 
 * @author Atone Joryman
 * @version February 2nd,2018
 *
 **********************************************************************/
public class randomNameAKPSI {
	/** array of names **/
	private String[] name;

	/** keep count of who is in attendance **/
	private int count;

	/** the assigned brothers of the week **/
	private ArrayList<String> pairs;

	/** brothers that are left when assigned **/
	private int brosLeft;

	/** flag source when there is one person **/
	private boolean flag;

	/** flag source when there is no more people **/
	private boolean flag2;

	/*******************************************************************
	 * This method can be used to manually add names if need be
	 * 
	 * @param name
	 *            of the brothers in chapter
	 ******************************************************************/
	public randomNameAKPSI(String[] name) {
		this.name = name;
		this.count = name.length;

		setCount(name.length);
		this.brosLeft = count;

		Arrays.sort(name);

	}

	/*******************************************************************
	 * This constructor will set an array of names and sort them
	 ******************************************************************/
	public randomNameAKPSI() {

		String[] brothers = { "Atone Joryman", "Jasmine Brown",
				"Reshonda Williams", "Jake Kendall Jr.", "Antwan Smith",
				"Emily Mazzullo", "Alisha Jackson", "Ashley Harris",
				"Destiny Adams", "Dominique Burt",
				"Jean Ortiz-Espinoza", "Joi France", "Justice Jackson",
				"Kieran J Barkley", "Mark Anthony", "Mary Kate Klein",
				"Richard Myers", "Sierra Carpenter", "Skyla Davis",
				"Tevin Shackelford", "Zaria Reeder-Wesley",
				"Kenyana Jones", "A'shanae Carson", "Paulasia Sims",
				"Angel McIntosh" };

		name = brothers;
		// get number of brothers
		setCount(name.length);
		// apply initial count
		this.brosLeft = 0;

		// sort by first name
		Arrays.sort(brothers);
	}

	/*******************************************************************
	 * this method will return a random gen of students from the array 
	 * name
	 * 
	 * @return String ArrayList
	 ******************************************************************/
	public ArrayList<String> generate() {
		// list the pairs of people of type string
		pairs = new ArrayList<String>();
		
		brosLeft+=2;
		
		
		
		if ((getCount() -brosLeft) == 1) {
			setisOne(true);
			return pairs;
		}
		// otherwise if none make true
		if ((getCount() - brosLeft) == 0) {
			setisZero(true);
			//return pairs;
		}

		String randomName = name[(int) (Math.random() * name.length)];
		if (randomName != null)
			// once cleared make this part of array null
			makeNull(randomName);
		String randomName2 = name[(int) (Math.random() * name.length)];

		if (randomName2 != null)
			makeNull(randomName2);
		// keep looping until not null
		while (randomName == (null)) {

			randomName = name[(int) (Math.random() * name.length)];
			makeNull(randomName);

		}

		while (randomName2 == (null)) {

			randomName2 = name[(int) (Math.random() * name.length)];
			makeNull(randomName2);
		}
		

		// otherwise add to pairs
		pairs.add(randomName + " and " + randomName2 + " ");
				
		return pairs;
	}

	

	/*******************************************************************
	 * This method will take in an string and find the name in array of 
	 * brothers and make it null
	 * 
	 * @param name
	 *            to be found
	 ******************************************************************/
	public void makeNull(String name) {
		// find name in array
		for (int n = 0; n < this.name.length; n++) {
			// make null when found
			if (this.name[n] == name) {
				this.name[n] = null;
			}
		}
	}

	/*******************************************************************
	 * accessor method to receive the array used in GUI
	 * 
	 * @return name of type array
	 ******************************************************************/
	public String[] getName() {
		return name;
	}

	/*******************************************************************
	 * accessor method to receive the count of brothers used in GUI
	 * 
	 * @return count of type int
	 ******************************************************************/
	public int getCount() {
		return count;
	}

	/*******************************************************************
	 * this mutator method will update the count of brothers
	 * 
	 * @param count
	 *            of type int
	 ******************************************************************/
	public void setCount(int count) {
		this.count = count;
	}

	/*******************************************************************
	 * this accessor method will get if there is one brother
	 * 
	 * @return flag of type boolean
	 ******************************************************************/
	public boolean getIsOne() {
		return flag;
	}
	
	/*******************************************************************
	 * This mutator method will set the flag to what is true or not
	 * 
	 * @param b
	 *            either true or false if one
	 ******************************************************************/
	public void setisOne(boolean b) {
		this.flag = b;

	}
	
	/*******************************************************************
	 * this accessor method will get if there is no brothers
	 * 
	 * @return flag of type boolean
	 ******************************************************************/
	public boolean getIsZero() {
		return flag2;
	}
	
	/*******************************************************************
	 * This mutator method will set the flag to what is true or not
	 * 
	 * @param b
	 *            either true or false if zero
	 ******************************************************************/
	public void setisZero(boolean b) {
		this.flag2 = b;

	}
	

}
