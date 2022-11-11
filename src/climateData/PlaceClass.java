package climateData;

public class PlaceClass {
	
	private int index;
	private String year;
	private String month;
	private String maxTemp;
	private String minTemp;
	private String afDays;
	private String rainQuantity;
	private String sunHours;
	
	
	public PlaceClass(int index,String year,String month,String maxTemp,String minTemp,String afDays,String rainQuantity,String sunHours) {
		
		this.index = index;
		this.year = year;
		this.month = month;
		this.maxTemp = maxTemp;
		this.minTemp = minTemp;
		this.afDays = afDays;
		this.rainQuantity = rainQuantity;
		this.sunHours = sunHours;
		
	}
	
	public static String getMonth(String index) {

		String month = "";

		switch (index) {// takes in the int value of a month and outputs the worded equivalent etc 1 to January

		case "1": {
			month = "January";
			break;// exits the switch statement
		}
		case "2": {
			month = "February";
			break;// exits the switch statement
		}
		case "3": {
			month = "March";
			break;// exits the switch statement
		}
		case "4": {
			month = "April";
			break;// exits the switch statement
		}
		case "5": {
			month = "May";
			break;// exits the switch statement
		}
		case "6": {
			month = "June";
			break;// exits the switch statement
		}
		case "7": {
			month = "July";
			break;// exits the switch statement
		}
		case "8": {
			month = "August";
			break;// exits the switch statement
		}
		case "9": {
			month = "September";
			break;// exits the switch statement
		}
		case "10": {
			month = "October";
			break;// exits the switch statement
		}
		case "11": {
			month = "November";
			break;// exits the switch statement
		}
		case "12": {
			month = "December";
			break;// exits the switch statement
		}
		}
		return month;

	}

	//index+" "+ for testing
	public String toString() {
		String result = year+"   "+month+"   "+maxTemp+"   "+minTemp+"   "+afDays+"   "+rainQuantity+"   "+sunHours;
		return result;
	}
	
	public int getIndex(){
		int result = index;
		return result;
	}
	public String getRainQuantity(){
		String result = rainQuantity;
		return result;
	}
	
	public String getYear(){
		String result = year;
		return result;
	}
	
	public String getMinTemp(){
		String result = minTemp;
		return result;
	}
	
	public String getMaxTemp(){
		String result = maxTemp;
		return result;
	}
	
	public String getMonth(){
		String result = month;
		return result;
	}
	public String getHours(){
		String result = sunHours;
		return result;
	}

	public String getFrosty() {
		// TODO Auto-generated method stub
		return null;
	}
}


