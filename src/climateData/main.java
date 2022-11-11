package climateData;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.time.Year;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

public class main {
	private static Scanner input = new Scanner(System.in);
	private static final ArrayList<LocationName> locations = new ArrayList<LocationName>();
	private static final ArrayList<PlaceClass> places = new ArrayList<PlaceClass>();
	private static final ArrayList<EarliestYear> firstYear = new ArrayList<EarliestYear>();
	public static String displaySpecificDataChoice = "";
	public static String displayMinMaxDataChoice = "";
	public static String compareLocationsChoice1 = "";
	public static String compareLocationsChoice2 = "";
	public static String specificStat = "";
	public static int locationIndex1 = 0;
	public static int locationIndex2 = 0;
	public static int dashCount = 0;

	public static void main(String[] args) {
		readIn();// reads the data from the files
		String choice = "";// Initialises the choice variable

		do {// repeats the menu until the condition is made
			System.out.println("\n-- MAIN MENU --");
			System.out.println("1) - View Locations");
			System.out.println("2) - Show The Location With The Min & Max Recorded(Rainfall/Hours of Sun/Temperature)");
			System.out.println("3) - Show The Min & Max Recorded Temperatures Data For A Specific Location");
			System.out.println("4) - Show All Data For Every Location");
			System.out.println("5) - Show All Data For A Specific Location");//feature B
			System.out.println("6) - List weather observations for a specified date");
			System.out.println("7) - Compare Two Locations by a Specific Data Set");//feature H
			System.out.println("Q) - Quit");
			System.out.print("Pick : ");
			choice = input.next().toUpperCase();//// takes the users input and converts it to its upper case variant

			switch (choice) {// decides which path to take dependent on the user input
			case "1": {
				locationFilters();
				mainMenuReturn();// call the display locations function
				break;// exits the switch as the condition is met
			}
			case "2": {// for the MIN/MAX LOCATION MENU
				minMaxMenuSort();
				mainMenuReturn();
				break;
			}
			case "3": {
				displayMinMaxDataNameInput();
				mainMenuReturn();// call the displayMinMaxData function
				break;// exits the switch as the condition is met
			}
			case "4": {
				displayData();
				mainMenuReturn();// call the displayData function
				break;// exits the switch as the condition is met
			}
			case "5": {
				displaySpecificDataNameInput();
				mainMenuReturn();// call the displaySpecificData function
				break;// exits the switch as the condition is met
			}
			case "6": {
				specificDateData();
				mainMenuReturn();
				break;
			}
			case "7": {
				compareLocations();
				mainMenuReturn();
				break;
			}
			}
		} while (!choice.equals("Q"));// Exits the while loop when the user types q/Q (ends the program)
		System.out.println("-- GOODBYE --");
	}
	
	private static void minMaxMenuSort() {
		String choiceS = "";// Initialises the choice2 variable
		System.out.println("\n-- Sort Type: --");
		System.out.println("1) - All Time --");
		System.out.println("2) - Specify Date --");
		System.out.print("Choose : ");
		choiceS = input.next();// takes the users input
		switch (choiceS) {
		case "1": {
			minMaxMenu();
			break;// exits the switch as the condition is met
		}
		case "2": {
			minMaxMenuS();
			break;// exits the switch as the condition is met
		}
		}
	}

	private static void minMaxMenuS() {
		String choice2 = "";// Initialises the choice2 variable
		System.out.println("\n-- MIN/MAX LOCATION MENU --");
		System.out.println("1) - Rainfall");
		System.out.println("2) - Hours of Sun");
		System.out.println("3) - Temperature");
		System.out.print("Choose : ");
		choice2 = input.next();// takes the users input
		switch (choice2) {
		case "1": {
			displayMinMaxRainfallS();

			break;// exits the switch as the condition is met
		}
		case "2": {
			displayMinMaxHoursOfSunDataS();
			break;// exits the switch as the condition is met
		}
		case "3": {
			displayMinMaxTempDataS();
			break;// exits the switch as the condition is met
		}
		}
	}

	private static void displayMinMaxTempDataS() { // WIP
		int currentIndex = 1;// sets the currentIndex variable to 1
		int j = 0; // sets the counting variable j to 0 which counts the location index
		int i = 0; // sets the counting variable i to 0 which counts the object index
		int currentYear = Year.now().getValue();
		float lastMaxTemp = 0;// Initialises the lastMaxTemp variable - holds the previously stored max
								// temperature
		float lastMinTemp = 100000;// Initialises the lastMinTemp variable - holds the previously stored min
								// temperature
		String maxTempFinal = "";// Initialises the maxTempFinal variable - holds the final max temperature
		String minTempFinal = "";// Initialises the minTempFinal variable - holds the final min temperature
		float maxTemp = 0;// Initialises the maxTemp variable - holds the current max temperature
		float minTemp = 0;// Initialises the minTemp variable - holds the current min temperature
		String year = "";
		String yearChoice = "";
		String monthChoice = "";
		String month = "";
		String maxYear = "";// Initialises the maxYear variable - holds the year of the final max
							// temperature
		String minYear = "";// Initialises the minYear variable - holds the year of the final min
							// temperature
		String maxMonth = "";// Initialises the maxMonth variable - holds the month of the final max
								// temperature
		String minMonth = "";// Initialises the minMonth variable - holds the month of the final min
								// temperature
		String lowestTempLocation = "";// Initialises the lowestTempLocation variable - holds the location of the final
										// min temperature
		String highestTempLocation = "";// Initialises the highestTempLocation variable - holds the location of the
										// final max temperature

		System.out.println("Please enter a year preceding: " + currentYear + " (Format: YYYY)");
		yearChoice = input.next();
		System.out.println("Please enter a month (Format: 1-12)");
		monthChoice = input.next();

		while (i < places.size()) {
			if (places.get(i).getIndex() == currentIndex) {
				if(places.get(i).getYear().equals(yearChoice)) {
					
					if(places.get(i).getMonth().equals(monthChoice)) {
						try {// Attempt the following code
							maxTemp = Float.parseFloat(places.get(i).getMaxTemp());// store the maxTemp of the current object at
																					// index i to the maxTemp variable as a
																					// float
						} catch (Exception e) {// if the maxTemp has a null value (etc "---") then run the following code
							maxTemp = 0;// assigns maxTemp the value of 0 to allow the program to skip the null value
						}

						try {// Attempt the following code
							minTemp = Float.parseFloat(places.get(i).getMinTemp());// store the minTemp of the current object at
																					// index i to the minTemp variable as a
																					// float
						} catch (Exception e) {
							minTemp = 10000;// assigns maxTemp the value of 0 to allow the program to skip the null value
						}

						if (maxTemp > lastMaxTemp) {// if the current maxTemp is greater than the previously stored maxTemp then
													// run the following code
							maxTempFinal = String.valueOf(maxTemp);// maxTempFinal is assigned the current maxTemp
							maxYear = places.get(i).getYear();// maxYear is assigned the year the current MaxTemp was recorded
															  // in
							maxMonth = places.get(i).getMonth();// maxMonth is assigned the year the current MaxTemp was
																// recorded in
							maxMonth = PlaceClass.getMonth(maxMonth);
							lastMaxTemp = maxTemp;// assigns the current maxTemp to the lastMaxTemp to continue the iteration
							highestTempLocation = locations.get(j).getName();// stores the location name where the maxTemp was
																				// recordeD
						}
						System.out.println(minTemp);
						if (minTemp < lastMinTemp) {// if the current minTemp is less than the previously stored minTemp then
													// run the following code
							minTempFinal = String.valueOf(minTemp);// minTempFinal is assigned the current maxTemp
							minYear = places.get(i).getYear();// minYear is assigned the year the current minTemp was recorded
																// in
							minMonth = places.get(i).getMonth();// minMonth is assigned the month the current minTemp was
																// recorded in
							minMonth = PlaceClass.getMonth(minMonth);
							lastMinTemp = minTemp;// assigns the current minTemp to the lastMinTemp to continue the iteration
							lowestTempLocation = locations.get(j).getName();// stores the location name where the minTemp was
																			// recorded
						}
					}
				}
				
				i++;// increments i by 1 to move to next object
			} else {
				currentIndex++;// increments currentIndex by 1 to allow the program to check its in the correct
								// location
				j++;// increments j by 1 to move to next location
			}
		}
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("Maximum Temperature Recorded: " + maxTempFinal + " Degrees on " + maxMonth + " " + maxYear
				+ " in " + highestTempLocation);
		System.out.println("Minimum Temperature Recorded: " + minTempFinal + " Degrees on " + minMonth + " " + minYear
				+ " in " + lowestTempLocation);
		System.out.println("---------------------------------------------------------------------------");
	}

	private static void displayMinMaxHoursOfSunDataS() { // WIP
		int currentIndex = 1;
		int j = 0;
		int i = 0;
		int currentYear = Year.now().getValue();
		float lastMaxHoursOfSun = 0;
		float lastMinHoursOfSun = 755;
		String maxHoursOfSunFinal = "";
		String minHoursOfSunFinal = "";
		float maxHoursOfSun = 0;
		float minHoursOfSun = 755;
		String maxYear = "";
		String minYear = "";
		String year;
		String yearChoice = "";
		String maxMonth = "";
		String minMonth = "";
		String monthChoice = "";
		String month;
		String lowestHoursOfSunLocation = "";
		String highestHoursOfSunLocation = "";

		System.out.println("Please enter a year preceding: " + currentYear + " (Format: YYYY)");
		yearChoice = input.next();
		System.out.println("Please enter a month. (Format: 1-12)");
		monthChoice = input.next();

		while (i < places.size()) {
			if (places.get(i).getIndex() == currentIndex) {
				if(places.get(i).getYear().equals(yearChoice)) {
					
					if(places.get(i).getMonth().equals(monthChoice)) {
						try {// Attempt the following code
							maxHoursOfSun = Float.parseFloat(places.get(i).getHours());
						} catch (Exception e) {
							maxHoursOfSun = 0;
						}

						try {// Attempt the following code
							minHoursOfSun = Float.parseFloat(places.get(i).getHours());
						} catch (Exception e) {
							minHoursOfSun = 755;
						}

						if (maxHoursOfSun > lastMaxHoursOfSun) {
							maxHoursOfSunFinal = String.valueOf(maxHoursOfSun);
							maxYear = places.get(i).getYear();
							maxMonth = places.get(i).getMonth();
							maxMonth = PlaceClass.getMonth(maxMonth);
							lastMaxHoursOfSun = maxHoursOfSun;
							highestHoursOfSunLocation = locations.get(j).getName();
						}

						if (minHoursOfSun < lastMinHoursOfSun) {
							minHoursOfSunFinal = String.valueOf(minHoursOfSun);
							minYear = places.get(i).getYear();
							minMonth = places.get(i).getMonth();
							minMonth = PlaceClass.getMonth(minMonth);
							lastMinHoursOfSun = minHoursOfSun;
							lowestHoursOfSunLocation = locations.get(j).getName();
						}
					}
				}
				
				i++;// increments i by 1 to move to next object
			} else {
				currentIndex++;// increments currentIndex by 1 to allow the program to check its in the correct
								// location
				j++;// increments j by 1 to move to next location
			}
		}
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("Maximum Hours Of Sun Recorded: " + maxHoursOfSunFinal + " Hours on " + maxMonth + " "
				+ maxYear + " in " + highestHoursOfSunLocation);
		System.out.println("Minimum Hours Of Sun Recorded: " + minHoursOfSunFinal + " Hours on " + minMonth + " "
				+ minYear + " in " + lowestHoursOfSunLocation);
		System.out.println("---------------------------------------------------------------------------");
	}

	private static void displayMinMaxRainfallS() { // WIP
		int currentIndex = 1;
		int j = 0;
		int i = 0;
		int currentYear = Year.now().getValue();
		float lastMaxRain = 0;
		float lastMinRain = 1500;
		String minRainFinal = "";
		String maxRainFinal = "";
		float maxRain = 0;
		float minRain = 1500;
		String maxYear = "";
		String maxMonth = "";
		String highestRainLocation = "";
		String minYear = "";
		String yearChoice = "";
		String monthChoice = "";
		String month = "";
		String year = "";
		String minMonth = "";
		String lowestRainLocation = "";

		System.out.println("Please enter a year preceding: " + currentYear + " (Format: YYYY)");
		yearChoice = input.next();
		System.out.println("Please enter a month (Format: 1-12)");
		monthChoice = input.next();

		while (i < places.size()) {
			if (places.get(i).getIndex() == currentIndex) {
				if(places.get(i).getYear().equals(yearChoice)) {
					
					if(places.get(i).getMonth().equals(monthChoice)) {
						
						try {
							maxRain = Float.parseFloat(places.get(i).getRainQuantity());
						} catch (Exception e) {
							maxRain = 0;
						}

						try {
							minRain = Float.parseFloat(places.get(i).getRainQuantity());
						} catch (Exception e) {
							minRain = 1000;
						}
						//System.out.println("Current: "+maxRain);
						if (maxRain > lastMaxRain) {
							maxRainFinal = String.valueOf(maxRain);
							maxYear = places.get(i).getYear();
							maxMonth = places.get(i).getMonth();
							maxMonth = PlaceClass.getMonth(maxMonth);
							lastMaxRain = maxRain;
							//System.out.println("Max: "+maxRainFinal);
							highestRainLocation = locations.get(j).getName();
						}

						if (minRain < lastMinRain) {
							minRainFinal = String.valueOf(minRain);
							minYear = places.get(i).getYear();
							minMonth = places.get(i).getMonth();
							minMonth = PlaceClass.getMonth(minMonth);
							lastMinRain = minRain;
							//System.out.println(minRainFinal);
							lowestRainLocation = locations.get(j).getName();
						}
						
					}
				}
				
				i++;
			} else {
				currentIndex++;
				j++;
			}
		}
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("Maximum Rainfall Recorded: " + maxRainFinal + " mm on " + maxMonth + " " + maxYear + " in "
				+ highestRainLocation);
		System.out.println("Minimum Rainfall Recorded: " + minRainFinal + " mm on " + minMonth + " " + minYear + " in "
				+ lowestRainLocation);
		System.out.println("---------------------------------------------------------------------------");
	}

	private static void minMaxMenu() {
		String choice2 = "";// Initialises the choice2 variable
		System.out.println("\n-- MIN/MAX LOCATION MENU --");
		System.out.println("1) - Rainfall");
		System.out.println("2) - Hours of Sun");
		System.out.println("3) - Temperature");
		System.out.print("Choose : ");
		choice2 = input.next();// takes the users input
		switch (choice2) {
		case "1": {
			displayMinMaxRainfall();

			break;// exits the switch as the condition is met
		}
		case "2": {
			displayMinMaxHoursOfSunData();
			break;// exits the switch as the condition is met
		}
		case "3": {
			displayMinMaxTempData();
			break;// exits the switch as the condition is met
		}
		}
	}

	private static void locationFilters() {
		String sortingChoice = "";// user input will be stored in this variable
		System.out.println("---------------------");
		System.out.println("--- Location List ---");
		System.out.println("Location sort Type:");
		System.out.println("1) - Alphabetical");
		System.out.println("2) - Chronological");
		System.out.println("---------------------");
		sortingChoice = input.next();
		switch (sortingChoice) {// decides which path to take dependent on the user input
		case "1": {
			displayLocations();// call the display locations function
			break;// exits the switch as the condition is met
		}
		case "2": {
			sortByLocationDate();// calls the function that sorts the locations chronologically
			break;
		} // exits the switch as the condition is met
		}
	}

	private static void mainMenuReturn() {
		String menuChoice = "";
		System.out.println("");
		System.out.println("");
		System.out.println("--- Return to Main Menu? ---");
		System.out.println("1) - Yes");
		System.out.println("2) - Quit");
		menuChoice = input.next();
		switch (menuChoice) {// decides which path to take dependent on the user input
		case "1": {
			// Does nothing, just generates a pause between the function ending and the menu
			// appearing
			break;// exits the switch as the condition is met
		}
		case "2": {
			System.exit(0);// ends the program.
			System.out.println("-- GOODBYE --");
			break;
		} // exits the switch as the condition is met
		}
	}

	private static void readIn() {// function that reads in the text files and saves the data in objects to an
									// arraylist
		String[] lNames = { // list of all the file names
				"aberporthdata.txt", "armaghdata.txt", "ballypatrickdata.txt", "bradforddata.txt", "braemardata.txt",
				"cambornedata.txt", "cambridgedata.txt", "cardiffdata.txt", "chivenordata.txt", "cwmystwythdata.txt",
				"dunstaffnagedata.txt", "durhamdata.txt", "eastbournedata.txt", "eskdalemuirdata.txt",
				"heathrowdata.txt", "hurndata.txt", "lerwickdata.txt", "leucharsdata.txt", "lowestoftdata.txt",
				"manstondata.txt", "nairndata.txt", "newtonriggdata.txt", "oxforddata.txt", "paisleydata.txt",
				"ringwaydata.txt", "rossonwyedata.txt", "shawburydata.txt", "sheffielddata.txt", "southamptondata.txt",
				"stornowaydata.txt", "suttonboningtondata.txt", "tireedata.txt", "valleydata.txt", "waddingtondata.txt",
				"whitbydata.txt", "wickairportdata.txt", "yeoviltondata.txt" };
		int i = 0;// sets the counting variable to 0

		while (i != lNames.length) {// repeats the following code for as many text files there are in the array
			String filename = lNames[i];// Assigns the variable filename to the current text file name using the
										// variable i as the index
			i++;// increments i by 1
			Scanner file = null;// Initialises the file variable
			try {// attempt the following code
				file = new Scanner(new FileReader(filename));// assigns the scanner to the current filename
			} catch (FileNotFoundException e) {// if there is no file run the following code
				e.printStackTrace();// output and error message to the console
			}

			String pattern = "(?:[A-Z][a-z]+\\s?)+";// Creates a string containing a regex pattern to be used with the
													// findInLine function
			String locationName = file.findInLine(pattern);// assigns the next token the variable locationName --
															// Matches strings found to match the pattern specified in
															// pattern
			locations.add(new LocationName(locationName));// adds the location found in the file to the location object

			boolean x = true;// Assigns x to true
			String year = null;// Initialises the year variable
			String sunHours = null;// Initialises the sunHours variable
			while (x == true) {// runs the following code while x is true
				String currentLine = file.next();// assigns the next token to the variable currentLine
				// System.out.println(currentLine);//testing
				if ("hours".equals(currentLine)) {// checks of the token equals hours
					x = false;// assigns x to false when the token equals hours
				} // The code above is used to skip the opening information of each text file
			}

			while (file.hasNextLine()) {// runs the following code while the file has another line
				Scanner line = new Scanner(file.nextLine());// reads in the next whole line of the file

				if (line.hasNext()) {// runs the following if the file has another token to read in
					String temp = line.next();// The variable tempt is assigned the next token of the file

					if ("Site".equals(temp)) {// if the file has the text Site Closed at the end then it skips over it
						break;// exists the while loop to stop reading in the file
					} else {
						year = temp;// the first token of the line is assigned to the variable year
					}

					String month = line.next();// the second token of the line is assigned to the variable month
					String maxTemp = line.next();// the third token of the line is assigned to the variable maxTemp
					String minTemp = line.next();// the fourth token of the line is assigned to the variable minTemp
					String afDays = line.next();// the fifth token of the line is assigned to the variable afDays
					String rainQuantity = line.next();// the sixth token of the line is assigned to the variable
														// rainQuantity
					try {// attempts the following code
						Float removeNonFloat = Float.valueOf(line.next().replaceAll("[^\\d.]", ""));
						sunHours = String.valueOf(removeNonFloat);
						// the seventh token of the line is assigned to the variable sunHours
					} catch (Exception e) {// if the seventh token is blank then assign the sunHours variable to ---
						sunHours = "---";// the string --- is assigned to the variable
					} // if (line.hasNext()) // provision
					places.add(new PlaceClass(i, year, month, maxTemp, minTemp, afDays, rainQuantity, sunHours));// adds
																													// all
																													// the
																													// variable
																													// into
																													// an
																													// object
																													// then
																													// saves
																													// them
																													// to
																													// the
																													// arraylist
				}
			}
		}
	}

	private static void displayData() {// function to display the data for every location
		System.out.println("\nyyyy   mm  tmax  tmin   af   rain   sun");// output the headers
		System.out.println("           degC  degC  days   mm   hours");// output the headers
		for (int i = 0; i < places.size(); i++) {// repeat the following code for every object in the places array
			System.out.println(places.get(i));// outputs the the object for the current index i
		}
	}

	private static void displayMinMaxTempData() {// function to display the locations with the highest and lowest
													// temperature
		int currentIndex = 1;// sets the currentIndex variable to 1
		int j = 0; // sets the counting variable j to 0 which counts the location index
		int i = 0; // sets the counting variable i to 0 which counts the object index
		float lastMaxTemp = 0;// Initialises the lastMaxTemp variable - holds the previously stored max
								// temperature
		float lastMinTemp = 100000;// Initialises the lastMinTemp variable - holds the previously stored min
								// temperature
		String maxTempFinal = "";// Initialises the maxTempFinal variable - holds the final max temperature
		String minTempFinal = "";// Initialises the minTempFinal variable - holds the final min temperature
		float maxTemp = 0;// Initialises the maxTemp variable - holds the current max temperature
		float minTemp = 0;// Initialises the minTemp variable - holds the current min temperature
		String maxYear = "";// Initialises the maxYear variable - holds the year of the final max
							// temperature
		String minYear = "";// Initialises the minYear variable - holds the year of the final min
							// temperature
		String maxMonth = "";// Initialises the maxMonth variable - holds the month of the final max
								// temperature
		String minMonth = "";// Initialises the minMonth variable - holds the month of the final min
								// temperature
		String lowestTempLocation = "";// Initialises the lowestTempLocation variable - holds the location of the final
										// min temperature
		String highestTempLocation = "";// Initialises the highestTempLocation variable - holds the location of the
										// final max temperature

		while (i < places.size()) {// runs the following code for every object in the arraylist
			if (places.get(i).getIndex() == currentIndex) {// if the index of the current object equals the value of the
															// currentIndex variable run the following code
				try {// Attempt the following code
					maxTemp = Float.parseFloat(places.get(i).getMaxTemp());// store the maxTemp of the current object at
																			// index i to the maxTemp variable as a
																			// float
				} catch (Exception e) {// if the maxTemp has a null value (etc "---") then run the following code
					maxTemp = 0;// assigns maxTemp the value of 0 to allow the program to skip the null value
				}

				try {// Attempt the following code
					minTemp = Float.parseFloat(places.get(i).getMinTemp());// store the minTemp of the current object at
																			// index i to the minTemp variable as a
																			// float
				} catch (Exception e) {
					minTemp = 0;// assigns maxTemp the value of 0 to allow the program to skip the null value
				}

				if (maxTemp > lastMaxTemp) {// if the current maxTemp is greater than the previously stored maxTemp then
											// run the following code
					maxTempFinal = String.valueOf(maxTemp);// maxTempFinal is assigned the current maxTemp
					maxYear = places.get(i).getYear();// maxYear is assigned the year the current MaxTemp was recorded
														// in
					maxMonth = places.get(i).getMonth();// maxMonth is assigned the year the current MaxTemp was
														// recorded in
					// System.out.println(maxTempFinal + locations.get(j).getName());
					maxMonth = PlaceClass.getMonth(maxMonth);
					lastMaxTemp = maxTemp;// assigns the current maxTemp to the lastMaxTemp to continue the iteration
					highestTempLocation = locations.get(j).getName();// stores the location name where the maxTemp was
																		// recorded
					// System.out.println(maxTempFinal+" "+maxYear);//testing
				}

				if (minTemp < lastMinTemp) {// if the current minTemp is less than the previously stored minTemp then
											// run the following code
					minTempFinal = String.valueOf(minTemp);// minTempFinal is assigned the current maxTemp
					minYear = places.get(i).getYear();// minYear is assigned the year the current minTemp was recorded
														// in
					minMonth = places.get(i).getMonth();// minMonth is assigned the month the current minTemp was
														// recorded in
					minMonth = PlaceClass.getMonth(minMonth);
					lastMinTemp = minTemp;// assigns the current minTemp to the lastMinTemp to continue the iteration
					lowestTempLocation = locations.get(j).getName();// stores the location name where the minTemp was
																	// recorded
					// System.out.println(minTempFinal+" "+minYear);//testing
				}

				i++;// increments i by 1 to move to next object
			} else {
				currentIndex++;// increments currentIndex by 1 to allow the program to check its in the correct
								// location
				j++;// increments j by 1 to move to next location
			}
		}
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("Maximum Temperature Recorded: " + maxTempFinal + " Degrees on " + maxMonth + " " + maxYear
				+ " in " + highestTempLocation);
		System.out.println("Minimum Temperature Recorded: " + minTempFinal + " Degrees on " + minMonth + " " + minYear
				+ " in " + lowestTempLocation);
		System.out.println("---------------------------------------------------------------------------");
	}

	private static void specificDateData() {
		int currentYear = Year.now().getValue();// makes an int called currentyear and assigns it the value of the year
												// that it is currently
		System.out.println("Please enter a year preceding: " + currentYear + " (Format: YYYY)");
		String yearChoice = "";
		yearChoice = input.next();
		int yearChoiceInt = Integer.parseInt(yearChoice);
		System.out.println("Please enter a month (Format: 1-12)");
		String monthChoice = "1";
		monthChoice = input.next();
		int monthChoiceInt = Integer.parseInt(monthChoice);
		if (monthChoiceInt >= 1 && yearChoiceInt >= 1) {// replace 1 with smallest year
			int i = 0;
			int j = 1;
			while (j < places.size()) {
				if (places.get(j).getYear().equals(yearChoice) && places.get(j).getMonth().equals(monthChoice)) {
					System.out.println("------------- " + locations.get(i).getName() + " Data ------------");
					System.out.println("yyyy   mm  tmax  tmin   af   rain   sun");
					System.out.println("           degC  degC  days   mm   hours");
					System.out.println(places.get(j) + "\n");
					j++;
					i++;
				} else {
					j++;
				}
			}
			System.out.print("---------------");
			for (int k = 0; k < locations.get(i).getName().length(); k++) {
				System.out.print("-");
			}
			System.out.print("---------------");
		} else {
			System.out.println("There is no data for the selected time!");
			specificDateData();
		}
	}

	private static void displaySpecificDataNameInput() {// Repeats the naming process after a user has input an
														// incorrect name
		displayLocations();// calls the display locations function to allow the user to see what locations
							// are available to chose from
		System.out.println("Enter your chosen location from the list above");
		displaySpecificDataChoice = input.next();// Store the user data in the choice variable
		displaySpecificDataChoice = displaySpecificDataChoice.substring(0, 1).toUpperCase()
				+ displaySpecificDataChoice.substring(1).toLowerCase();
		displaySpecificData();
	}

	private static void displaySpecificDataNameInputRetry() {// Repeats the naming process after a user has input an
																// incorrect name
		System.out.println("Please enter a valid location name:");
		displaySpecificDataChoice = input.next();// Store the user data in the choice variable
		displaySpecificDataChoice = displaySpecificDataChoice.substring(0, 1).toUpperCase()
				+ displaySpecificDataChoice.substring(1).toLowerCase();
		displaySpecificDataRebound();

	}

	private static void displaySpecificData() {
		int loopCount = 0;// Initiates the loopCount variable
		int j = 0;
		int chosenIndex = 1;
		while (j < locations.size()) {
			if (locations.get(j).getName().equals(displaySpecificDataChoice)) {
				chosenIndex = j + 1;
				break;
			} else {
				j++;
			}
		}
		if (j == locations.size()) {
			System.out.println("Location Not Found! Please Try Again");
			try {
				displaySpecificDataNameInputRetry();
				TimeUnit.SECONDS.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.print("---------- " + locations.get(j).getName() + " Data ---------");
			System.out.println("\nyyyy   mm  tmax  tmin   af   rain   sun");
			System.out.println("           degC  degC  days   mm   hours");
		}
		int i = 0;
		while (i < places.size()) {
			if (places.get(i).getIndex() == chosenIndex) {
				System.out.println(places.get(i));
				loopCount++;
				i++;
			} else if (places.get(i).getIndex() == chosenIndex + 1) {
				break;
			} else {
				loopCount++;
				i++;
			}
		}
		System.out.println("Loop Count: " + loopCount);// to see how many loops where made
	}

	private static void displaySpecificDataRebound() {// function to display data for a specific location
		int loopCount = 0;// Initiates the loopCount variable
		int j = 0;
		int chosenIndex = 1;
		while (j < locations.size()) {
			if (locations.get(j).getName().equals(displaySpecificDataChoice)) {
				chosenIndex = j + 1;
				break;
			} else {
				j++;
			}
		}

		if (j == locations.size()) {
			System.out.println("Location Not Found! Please Try Again");
			try {
				TimeUnit.SECONDS.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			displaySpecificDataNameInputRetry();
		} else {
			System.out.print("---------- " + locations.get(j).getName() + " Data ---------");
			System.out.println("\nyyyy   mm  tmax  tmin   af   rain   sun");
			System.out.println("           degC  degC  days   mm   hours");
		}
		int i = 0;
		while (i < places.size()) {
			if (places.get(i).getIndex() == chosenIndex) {
				System.out.println(places.get(i));
				loopCount++;
				i++;
			} else if (places.get(i).getIndex() == chosenIndex + 1) {
				break;
			} else {
				loopCount++;
				i++;
			}
		}
		System.out.println("Loop Count: " + loopCount);// to see how many loops where made
	}

	private static void displayMinMaxDataNameInput() {
		displayLocations();
		System.out.println("Enter your chosen location from the list above");
		displayMinMaxDataNameInputRetry();
	}

	private static void displayMinMaxDataNameInputRetry() {
		displayMinMaxDataChoice = "";
		displayMinMaxDataChoice = input.next();
		displayMinMaxDataChoice = displayMinMaxDataChoice.substring(0, 1).toUpperCase()
				+ displayMinMaxDataChoice.substring(1).toLowerCase();
		// System.out.println(locations.size());
		displayMinMaxData();
	}

	private static void displayMinMaxData() {
		int j = 0;
		int chosenIndex = 0;
		while (j < locations.size()) {
			if (locations.get(j).getName().equals(displayMinMaxDataChoice)) {
				chosenIndex = j + 1;
				break;
			} else {
				j++;
			}
		}

		if (j == locations.size()) {
			System.out.println("Location Not Found! Please Try Again");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			displayMinMaxDataNameInputRetry();
		} else {
			int loopCount = 0;
			int i = 0;
			float lastMaxTemp = 0;
			float lastMinTemp = 0;
			String maxTempFinal = "";
			String minTempFinal = "";
			float maxTemp = 0;
			float minTemp = 0;
			String maxYear = "";
			String minYear = "";
			String maxMonth = "";
			String minMonth = "";
			while (i < places.size()) {
				if (places.get(i).getIndex() == chosenIndex) {
					try {
						maxTemp = Float.parseFloat(places.get(i).getMaxTemp());
					} catch (Exception e) {
						maxTemp = 0;
					}
					try {
						minTemp = Float.parseFloat(places.get(i).getMinTemp());
					} catch (Exception e) {
						minTemp = 0;
					}

					if (maxTemp > lastMaxTemp) {
						maxTempFinal = String.valueOf(maxTemp);
						maxYear = places.get(i).getYear();
						maxMonth = places.get(i).getMonth();
						// System.out.println(maxTempFinal+" "+maxMonth+" "+maxYear);
						maxMonth = PlaceClass.getMonth(maxMonth);
						lastMaxTemp = maxTemp;
						// System.out.println(maxTempFinal+" "+maxYear);
					}

					if (minTemp < lastMinTemp) {
						minTempFinal = String.valueOf(minTemp);
						minYear = places.get(i).getYear();
						minMonth = places.get(i).getMonth();
						minMonth = PlaceClass.getMonth(minMonth);
						lastMinTemp = minTemp;
						// System.out.println(minTempFinal+" "+minYear);
					}
					loopCount++;
					i++;
				} else if (places.get(i).getIndex() == chosenIndex + 1) {
					break;
				} else {
					loopCount++;
					i++;
				}
			}
			System.out.println("Loop Count: " + loopCount);// to see how many loops where made
			String locName = locations.get(j).getName();
			System.out.println("--------------------------- " + locName + " ---------------------------");
			System.out.println(
					"Maximum Temperature Recorded: " + maxTempFinal + " Degrees on " + maxMonth + " " + maxYear);
			System.out.println(
					"Minimum Temperature Recorded: " + minTempFinal + " Degrees on " + minMonth + " " + minYear);
			System.out.print("--------------------------------------------------------");
			for (int k = 0; k < locName.length(); k++) {
				System.out.print("-");
			}
		}
	}

	private static void displayLocations() {
		System.out.println("");
		for (int i = 0; i < locations.size(); i++) {
			System.out.println(locations.get(i));
		}
	}

	private static void displayMinMaxRainfall() {
		int currentIndex = 1;
		int j = 0;
		int i = 0;
		float lastMaxRain = 0;
		float lastMinRain = 1500;
		String minRainFinal = "";
		String maxRainFinal = "";
		float maxRain = 0;
		float minRain = 1500;
		String maxYear = "";
		String maxMonth = "";
		String highestRainLocation = "";
		String minYear = "";
		String minMonth = "";
		String lowestRainLocation = "";

		while (i < places.size()) {
			if (places.get(i).getIndex() == currentIndex) {
				try {
					maxRain = Float.parseFloat(places.get(i).getRainQuantity());
				} catch (Exception e) {
					maxRain = 0;
				}

				try {
					minRain = Float.parseFloat(places.get(i).getRainQuantity());
				} catch (Exception e) {
					minRain = 1000;
				}

				if (maxRain > lastMaxRain) {
					maxRainFinal = String.valueOf(maxRain);
					maxYear = places.get(i).getYear();
					maxMonth = places.get(i).getMonth();
					// System.out.println(maxTempFinal + locations.get(j).getName());
					maxMonth = PlaceClass.getMonth(maxMonth);
					lastMaxRain = maxRain;
					highestRainLocation = locations.get(j).getName();
					// System.out.println(maxTempFinal+" "+maxYear);
				}

				if (minRain < lastMinRain) {
					minRainFinal = String.valueOf(minRain);
					minYear = places.get(i).getYear();
					minMonth = places.get(i).getMonth();
					minMonth = PlaceClass.getMonth(minMonth);
					lastMinRain = minRain;
					lowestRainLocation = locations.get(j).getName();
					// System.out.println(maxTempFinal+" "+maxYear);
				}
				i++;
			} else {
				currentIndex++;
				j++;
			}
		}
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("Maximum Rainfall Recorded: " + maxRainFinal + " mm on " + maxMonth + " " + maxYear + " in "
				+ highestRainLocation);
		System.out.println("Minimum Rainfall Recorded: " + minRainFinal + " mm on " + minMonth + " " + minYear + " in "
				+ lowestRainLocation);
		System.out.println("---------------------------------------------------------------------------");
	}

	private static void displayMinMaxHoursOfSunData() {
		int currentIndex = 1;
		int j = 0;
		int i = 0;
		float lastMaxHoursOfSun = 0;
		float lastMinHoursOfSun = 755;
		String maxHoursOfSunFinal = "";
		String minHoursOfSunFinal = "";
		float maxHoursOfSun = 0;
		float minHoursOfSun = 755;
		String maxYear = "";
		String minYear = "";
		String maxMonth = "";
		String minMonth = "";
		String lowestHoursOfSunLocation = "";
		String highestHoursOfSunLocation = "";

		while (i < places.size()) {// runs the following code for every object in the arraylist
			if (places.get(i).getIndex() == currentIndex) {// if the index of the current object equals the value of the
															// currentIndex variable run the following code
				try {// Attempt the following code
					maxHoursOfSun = Float.parseFloat(places.get(i).getHours());
				} catch (Exception e) {
					maxHoursOfSun = 0;
				}

				try {// Attempt the following code
					minHoursOfSun = Float.parseFloat(places.get(i).getHours());
				} catch (Exception e) {
					minHoursOfSun = 755;
				}

				if (maxHoursOfSun > lastMaxHoursOfSun) {
					maxHoursOfSunFinal = String.valueOf(maxHoursOfSun);
					maxYear = places.get(i).getYear();
					maxMonth = places.get(i).getMonth();
					// System.out.println(maxTempFinal + locations.get(j).getName());
					maxMonth = PlaceClass.getMonth(maxMonth);
					lastMaxHoursOfSun = maxHoursOfSun;
					highestHoursOfSunLocation = locations.get(j).getName();
					// System.out.println(maxTempFinal+" "+maxYear);//testing
				}

				if (minHoursOfSun < lastMinHoursOfSun) {
					minHoursOfSunFinal = String.valueOf(minHoursOfSun);
					minYear = places.get(i).getYear();
					minMonth = places.get(i).getMonth();
					minMonth = PlaceClass.getMonth(minMonth);
					lastMinHoursOfSun = minHoursOfSun;
					lowestHoursOfSunLocation = locations.get(j).getName();
				}
				i++;// increments i by 1 to move to next object
			} else {
				currentIndex++;// increments currentIndex by 1 to allow the program to check its in the correct
								// location
				j++;// increments j by 1 to move to next location
			}
		}
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("Maximum Hours Of Sun Recorded: " + maxHoursOfSunFinal + " Hours on " + maxMonth + " "
				+ maxYear + " in " + highestHoursOfSunLocation);
		System.out.println("Minimum Hours Of Sun Recorded: " + minHoursOfSunFinal + " Hours on " + minMonth + " "
				+ minYear + " in " + lowestHoursOfSunLocation);
		System.out.println("---------------------------------------------------------------------------");
	}

	private static void compareDataLocationsInput() {
		System.out.println("Please enter a location:");
		compareLocationsChoice1 = input.next();
		System.out.println("Please enter a second location to comapare:");
		compareLocationsChoice2 = input.next();
	}
	
	private static void compareDataLocationsInputRetry() {
		System.out.println("Please enter a location:");
		compareLocationsChoice1 = input.next();
		System.out.println("Please enter a second location to comapare:");
		compareLocationsChoice2 = input.next();
	}

	private static String specificStatInput() {
		String specificStat = "";
		String choice = "";
		System.out.println("Please enter a stat to compare");
		System.out.println("1) - Rain Fall");
		System.out.println("2) - Sun Hours");
		System.out.println("3) - Frosty Days");
		System.out.println("4) - Max Temperature");
		System.out.println("5) - Min Temperature");
		System.out.println("Pick: ");
		choice = input.next();

		switch (choice) {
		case "1": {
			specificStat = "Rainfall";
			break;
		}
		case "2": {
			specificStat = "Sun-Hours";
			break;
		}
		case "3": {
			specificStat = "Frosty-Days";
			break;
		}
		case "4": {
			specificStat = "Max-Temperature";
			break;
		}
		case "5": {
			specificStat = "Min-Temperature";
			break;
		}

		}
		return specificStat;
	}

	private static void compareLocations() {
		displayLocations(); // calls the display locations function to allow the user to see what locations
							// are available to chose from
		compareDataLocationsInput();

		
		int j = 0;
		int m = 0;

		while (j < locations.size()) {
			if (locations.get(j).getName().equalsIgnoreCase(compareLocationsChoice1)) {
				locationIndex1 = j+1;
				break;
			} else {
				j++;
			}
		}

		while (m < locations.size()) {
			if (locations.get(m).getName().equalsIgnoreCase(compareLocationsChoice2)) {
				locationIndex2 = m+1;
				break;
			} else {
				m++;
			}
		}

		if (j == locations.size() || m == locations.size()) {
			System.out.println("Location Not Found! Please Try Again");
			try {
				compareLocations();
				TimeUnit.SECONDS.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		specificStat = specificStatInput();// The stat you want to compare the two against
		compareHighestLocations();
		
		
		String avgStat1 = "";// will hold the average stat of the first location
		String avgStat2 = "";// will hold the average stat of the second location

		String locationName1 = "";// will hold the name of the first location
		String locationName2 = "";// will hold the name stat of the first location
		String suffix = "";// will hold the suffix of the chosen stat

		int chosenIndex = 0;

		for (int k = 0; k < 2; k++) {// will run the following code twice to get the for the two different locations

			if (k == 0) {// the first run will assign the first locations index and the second run will
							// assign the second locations index
				chosenIndex = locationIndex1;
			} else {
				chosenIndex = locationIndex2;
			}
			int notRecordedCount = 0;
			float avgStatCount = 0;
			int i = 0;
			float currentStatCount = 0;
			int dataCount = 0;
			String locationName = "";
			int count = 0;

			while (i < places.size()) {// loops thorugh every data row
				if (places.get(i).getIndex() == chosenIndex) {// runs the following code if the chosen index is found

					switch (specificStat) {// assigns the values based of what stat the user decided
					case "Rainfall": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getRainQuantity());
							dataCount++;
							suffix = " mm ";
						} catch (Exception e) {
							currentStatCount = 0;
							notRecordedCount++;
						}
						break;
					}
					case "Sun-Hours": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getHours());
							dataCount++;
							suffix = " Hours ";
						} catch (Exception e) {
							currentStatCount = 0;
							notRecordedCount++;
						}
						break;
					}
					case "Frosty-Days": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getFrosty());
							dataCount++;
							suffix = " Days ";
						} catch (Exception e) {
							currentStatCount = 0;
							notRecordedCount++;
						}
						break;
					}
					case "Max-Temperature": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getMaxTemp());
							dataCount++;
							suffix = " Degrees ";
						} catch (Exception e) {
							currentStatCount = 0;
							notRecordedCount++;
						}
						break;
					}
					case "Min-Temperature": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getMinTemp());
							dataCount++;
							suffix = " Degrees ";
						} catch (Exception e) {
							currentStatCount = 0;
							notRecordedCount++;
						}
						break;
					}

					}

					avgStatCount = avgStatCount + currentStatCount;// Increments the total amount of the average stat

					locationName = locations.get(chosenIndex-1).getName();// holds the name of this location
					count++;
					i++;// moves to the next data row
				} else if (places.get(i).getIndex() == (chosenIndex + 1)) {
					break;
				} else {
					i++;
					count++;

				}
			}
			// System.out.println("Loop Count: "+count);
			if (k == 0) {// does the following on the first run
				//System.out.println("Total: "+avgStatCount+" Stored-Rows: "+dataCount+" Skipped-Rows: "+notRecordedCount+" Total Rows "+(dataCount+notRecordedCount));
				avgStat1 = String.valueOf(Math.round(avgStatCount / dataCount * 100.0) / 100.0);
				locationName1 = locationName;
			} else {// does the following on the second run
				//System.out.println("Total: "+avgStatCount+" Stored-Rows: "+dataCount+" Skipped-Rows: "+notRecordedCount+" Total Rows "+(dataCount+notRecordedCount));
				avgStat2 = String.valueOf(Math.round(avgStatCount / dataCount * 100.0) / 100.0);
				locationName2 = locationName;
			}

		}

		int currentDashCount = 19 + specificStat.length() + locationName1.length() + locationName2.length()+ (suffix.length() * 2) + avgStat1.length() + avgStat2.length();
		
		
		if(currentDashCount > dashCount) {
			dashCount = currentDashCount;
		}

		for (int l = 0; l < dashCount; l++) {// outputs all the dashes required

			System.out.print("-");
		}

		System.out.println("\nAverage " + specificStat + " Recorded:" + locationName1 + " " + avgStat1 + suffix
				+ locationName2 + " " + avgStat2 + suffix);// oututs the data
//		for (int l = 0; l < dashCount; l++) {
//
//			System.out.print("-");// outputs all the dashes required
//			
//		}
		compareLowestLocations();
	}
	private static void compareLowestLocations() {

		// The stat you want to compare the two against

		String lowStat1 = "";// will hold the average stat of the first location
		String lowStat2 = "";// will hold the average stat of the second location

		String locationName1 = "";// will hold the name of the first location
		String locationName2 = "";// will hold the name stat of the first location
		String suffix = "";// will hold the suffix of the chosen stat

		int chosenIndex = 0;

		for (int k = 0; k < 2; k++) {// will run the following code twice to get the for the two different locations

			if (k == 0) {// the first run will assign the first locations index and the second run will
				// assign the second locations index
				chosenIndex = locationIndex1;
			} else {
				chosenIndex = locationIndex2;
			}
			int notRecordedCount = 0;
			float lowStatCount = 9999;
			int i = 0;
			float currentStatCount = 0;
			int dataCount = 0;
			String locationName = "";
			int count = 0;
			float lowStatFinal = 0;

			while (i < places.size()) {
				if (places.get(i).getIndex() == chosenIndex) {

					switch (specificStat) {
					case "Rainfall": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getRainQuantity());
							dataCount++;
							suffix = " mm ";
						} catch (Exception e) {
							currentStatCount = 100000;
						}
						break;
					}
					case "Sun-Hours": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getHours());
							dataCount++;
							suffix = " Hours ";
						} catch (Exception e) {
							currentStatCount = 755;
						}
						break;
					}
					case "Frosty-Days": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getFrosty());
							//System.out.println(currentStatCount);
							dataCount++;
							suffix = " Days ";
						} catch (Exception e) {
							currentStatCount = 100000;
						}
						break;
					}
					case "Max-Temperature": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getMaxTemp());
							dataCount++;
							suffix = " Degrees ";
						} catch (Exception e) {
							currentStatCount = 100000;
						}
						break;
					}

					case "Min-Temperature": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getMinTemp());
							dataCount++;
							suffix = " Degrees ";
						} catch (Exception e) {
							currentStatCount = 100000;
						}
						break;
					}

					}
					//System.out.println("Current: "+currentStatCount);
					if (currentStatCount < lowStatCount) {
						lowStatCount = currentStatCount;
						lowStatFinal = currentStatCount;
						//System.out.println("Lowest: "+lowStatFinal);
						locationName = locations.get(chosenIndex-1).getName();

					}

					count++;
					i++;
				} else if (places.get(i).getIndex() == (chosenIndex + 1)) {
					break;
				} else {
					i++;
					count++;
				}
			}

			if (k == 0) {
				lowStat1 = String.valueOf(lowStatFinal);
				locationName1 = locationName;
			} else {
				lowStat2 = String.valueOf(lowStatFinal);
				locationName2 = locationName;
			}

		}

		int currentDashCount = 19 + specificStat.length() + locationName1.length() + locationName2.length()+ (suffix.length() * 2) + lowStat1.length() + lowStat2.length();
		
		
		if(currentDashCount > dashCount) {
			dashCount = currentDashCount;
		}

		for (int l = 0; l < dashCount; l++) {

			System.out.print("-");
		}

		System.out.println("\nLowest " + specificStat + " Recorded:" + locationName1 + " " + lowStat1 + suffix
				+ locationName2 + " " + lowStat2 + suffix);
		for (int l = 0; l < dashCount; l++) {

			System.out.print("-");
		}
	}

	
	private static void compareHighestLocations() {

		// The stat you want to compare the two against

		String highStat1 = "";// will hold the average stat of the first location
		String highStat2 = "";// will hold the average stat of the second location

		String locationName1 = "";// will hold the name of the first location
		String locationName2 = "";// will hold the name stat of the first location
		String suffix = "";// will hold the suffix of the chosen stat

		int chosenIndex = 0;

		for (int k = 0; k < 2; k++) {// will run the following code twice to get the for the two different locations

			if (k == 0) {// the first run will assign the first locations index and the second run will
				// assign the second locations index
				chosenIndex = locationIndex1;
			} else {
				chosenIndex = locationIndex2;
			}
			int notRecordedCount = 0;
			float highStatCount = -100000;
			int i = 0;
			float currentStatCount = 0;
			int dataCount = 0;
			String locationName = "";
			int count = 0;
			float highStatFinal = 0;

			while (i < places.size()) {
				if (places.get(i).getIndex() == chosenIndex) {

					switch (specificStat) {
					case "Rainfall": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getRainQuantity());
							dataCount++;
							suffix = " mm ";
						} catch (Exception e) {
							currentStatCount = -100000;
						}
						break;
					}
					case "Sun-Hours": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getHours());
							dataCount++;
							suffix = " Hours ";
						} catch (Exception e) {
							currentStatCount = -755;
						}
						break;
					}
					case "Frosty-Days": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getFrosty());
							//System.out.println(currentStatCount);
							dataCount++;
							suffix = " Days ";
						} catch (Exception e) {
							currentStatCount = -100000;
						}
						break;
					}
					case "Max-Temperature": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getMaxTemp());
							dataCount++;
							suffix = " Degrees ";
						} catch (Exception e) {
							currentStatCount = -100000;
						}
						break;
					}

					case "Min-Temperature": {
						try {
							currentStatCount = Float.parseFloat(places.get(i).getMinTemp());
							dataCount++;
							suffix = " Degrees ";
						} catch (Exception e) {
							currentStatCount = -100000;
						}
						break;
					}

					}
					//System.out.println("Current: "+currentStatCount);
					if (currentStatCount > highStatCount) {
						highStatCount = currentStatCount;
						highStatFinal = currentStatCount;
						//System.out.print("Higest: "+highStatFinal);
						locationName = locations.get(chosenIndex-1).getName();
						//System.out.println(" "+locationName);
					}

					count++;
					i++;
				} else if (places.get(i).getIndex() == (chosenIndex + 1)) {
					break;
				} else {
					i++;
					count++;
				}
			}

			if (k == 0) {
				highStat1 = String.valueOf(highStatFinal);
				locationName1 = locationName;

			} else {
				highStat2 = String.valueOf(highStatFinal);
				locationName2 = locationName;
				
			}

		}

		dashCount = 19 + specificStat.length() + locationName1.length() + locationName2.length()
				+ (suffix.length() * 2) + highStat1.length() + highStat2.length();


		for (int l = 0; l < dashCount; l++) {

			System.out.print("-");
		}

		System.out.println("\nHighest " + specificStat + " Recorded:" + locationName1 + " " + highStat1 + suffix
				+ locationName2 + " " + highStat2 + suffix);
//		for (int l = 0; l < dashCount; l++) {
//
//			System.out.print("-");
//		}
	}
	
	private static void sortByLocationDate() {
		int i = 0;
		int j = 0;
		int currentIndex = 1;
		int minYear = 0;
		int minYearLast = 9999;
		int minYearFinal = 0;
		String yearLocation = "";

		while (i < places.size()) {// runs the following code for every object in the arraylist
			if (places.get(i).getIndex() == currentIndex) {// if the index of the current object equals the value of the
															// currentIndex variable run the following code
				try { // Attempts the following code
					minYear = (Integer.parseInt(places.get(i).getYear()));
				} catch (Exception e) {
					minYear = 9999;
				}

				if (minYear < minYearLast) { // If the current year is less than the previous run the following code
					minYearFinal = minYear;
					minYearLast = minYear;
					yearLocation = locations.get(j).getName();
					firstYear.add(new EarliestYear(yearLocation, minYearFinal)); // Adds the location name and earliest
																					// year to a new arraylist
				}
				i++;
			} else {
				minYearLast = 9999; // resets the variable
				currentIndex++; // Changes to next set of location data
				j++; // Changes to next location
			}
		}

		for (int u = 1; u < firstYear.size(); u++) { // Sort the ArrayList in descending order of year
			for (int t = u; t > 0; t--) {
				EarliestYear lower = firstYear.get(t - 1);
				EarliestYear higher = firstYear.get(t);
				if ((higher.getYear()) < (lower.getYear())) {
					firstYear.set(t, lower);
					firstYear.set(t - 1, higher);
				} else
					break;
			}
		}
		System.out.println("Location Name------Earliest Year\n");
		for (int k = 0; k < firstYear.size(); k++) {
			System.out.println(String.format("%12s %10s", firstYear.get(k).getName(), firstYear.get(k).getYear()));
		}
	}
}
