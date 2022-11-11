package climateData;

public class Menus {

	public void mainMenu() {

		System.out.println("\n-- MAIN MENU --");
		System.out.println("1 - View Locations");
		System.out.println("2 - Show The Location With The Min & Max Recorded(Rainfall/Hours of Sun/Temperature)");
		System.out.println("3 - Show The Min & Max Recorded Temperatures Data For A Specific Location");
		System.out.println("4 - Show All Data For Every Location");
		System.out.println("5 - Show All Data For A Specific Location");
		System.out.println("6 - List weather observations for a specified date");
		System.out.println("Q - Quit");
		System.out.print("Pick : ");
	}

	public void minMaxMenu() {
		System.out.println("\n-- MIN/MAX LOCATION MENU --");
		System.out.println("1 - Rainfall");
		System.out.println("2 - Hours of Sun");
		System.out.println("3 - Temperature");
		System.out.print("Pick : ");
	}

	public void locationFilterMenu() {
		System.out.println("---------------------");
		System.out.println("--- Location List ---");
		System.out.println("Location sort Type:");
		System.out.println("1) - Alphabetical");
		System.out.println("2) - Chronological");
		System.out.println("---------------------");
	}

	public void returnMenu() {
		System.out.println("");
		System.out.println("");
		System.out.println("--- Return to Main Menu? ---");
		System.out.println("1) - Yes");
		System.out.println("2) - Quit");
	}
}
