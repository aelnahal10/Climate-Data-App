package climateData;

public class EarliestYear {

	private String name;
	private int year;

	public String getName() {
		String result = name;
		return result;
	}

	public int getYear() {
		int result = year;
		return result;
	}

	public EarliestYear(String name, int year) {
		this.name = name;
		this.year = year;
	}

	public String toString() {
		String result = name + "  " + year;
		return result;
	}

}
