package fi.pickup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import fi.pickup.domain.*;

public class CsvReader {
	
	//The CsvReader 'main' method
	//returns trues if the program was successfully terminated
	public static boolean processInput(UserInput userInput) throws ParseException, IOException {
		List<String> locationIDs = getLocationIDs();
		List<PickupTime> pickupTimes = getPickupTimes();
		List<Result> results = getResults(locationIDs, pickupTimes, userInput);
		writeToCSV(results);
		return true;
	}
	
	//Goes through the locations.csv file, gets the IDs and saves them to a List
	private static List<String> getLocationIDs() throws FileNotFoundException {		
		Scanner fileReader = new Scanner(new File("src/locations.csv"));
		fileReader.useDelimiter("\\,");
		List<String> locationIDs = new ArrayList<>();
		fileReader.nextLine();

		while (fileReader.hasNextLine()) {
			String[] strArray = fileReader.nextLine().split("\\,");
			locationIDs.add(strArray[0]);
		}
		fileReader.close();
		return locationIDs;
	}
	
	//Goes through the pickup_times.csv file, gets the data and saves them to a List using the pickupTime class
	private static List<PickupTime> getPickupTimes() throws FileNotFoundException, ParseException {
		Scanner fileReader = new Scanner(new File("src/pickup_times.csv"));
		fileReader.useDelimiter("\\,");
		List<PickupTime> pickupTimes = new ArrayList<>();
		fileReader.nextLine();

		while (fileReader.hasNextLine()) {
			String[] strArray = fileReader.nextLine().split("\\,");
			PickupTime pickupTime = new PickupTime(strArray[0], strArray[1], strArray[2]);
			pickupTimes.add(pickupTime);
		}
		fileReader.close();
		return pickupTimes;
	}

	//Cross-references the two ArrayLists and sends a list of data that apply to the users given rules to the calculateMedian method
	//returns the results
	private static List<Result> getResults(List<String> locationIDs, List<PickupTime> pickupTimes, UserInput userInput) {
		List<Result> results = new ArrayList<>();
		for (String id : locationIDs) {
			List<Integer> timesInTimeFrame = new ArrayList<>();
			for (PickupTime pickupTime : pickupTimes) {
				if (id.equals(pickupTime.getId())
						&& userInput.getDate().getDayOfWeek() == pickupTime.getTimestamp().getDayOfWeek()
						&& userInput.getStartTime() <= pickupTime.getTimestamp().getHourOfDay()
						&& pickupTime.getTimestamp().getHourOfDay() < userInput.getEndTime()) {

					timesInTimeFrame.add(pickupTime.getPickupTime());
				}
			}
			Result result = new Result(id, calculateMedian(timesInTimeFrame));
			results.add(result);
		}
		return results;
	}
	//This method calculates the median for the input list
	//if the list size is an even number it calculates the average for the to middle integers
	private static int calculateMedian(List<Integer> timesInTimeFrame) {
		Collections.sort(timesInTimeFrame);
		if (timesInTimeFrame.size() % 2 != 0) {
			return timesInTimeFrame.get(timesInTimeFrame.size() / 2);
		} else {
			return timesInTimeFrame.get(timesInTimeFrame.size() / 2)
					+ timesInTimeFrame.get((timesInTimeFrame.size() / 2) - 1) / 2;
		}
	}
	//This method writes the result into the results.csv file
	private static void writeToCSV(List<Result> results) throws IOException {
		FileWriter fw = new FileWriter(new File("src/results.csv"), false);
		StringBuilder sb = new StringBuilder();
		fw.write("location_id,median_pickup_time\n");
		
		for(Result result : results) {
			sb.append(result.getId() + "," + result.getMedian() + "\n");
		}
		fw.write(sb.toString());
		fw.flush();
		fw.close();
	}
}
