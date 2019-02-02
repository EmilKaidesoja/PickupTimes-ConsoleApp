package fi.pickup;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import fi.pickup.domain.UserInput;

public class PickupMain {

	public static void main(String[] args) {
		boolean success = false;
		do {
			try {
				UserInput userInput = getUserInput();
				success = CsvReader.processInput(userInput);

				if (success) {
					System.out.println("Success! Check the results.csv file.");
				} else {
					System.out.println("Something went wrong. Try again!");
				}
				
			} catch (ParseException e) {
				System.out.println("The input was invalid. Try again!\n");
			} catch (InputMismatchException e) {
				System.out.println("The input was invalid. Try again!\n");
			} catch (FileNotFoundException e) {
				System.out.println("The file was not found\n");
			} catch (IOException e) {
				System.out.println("An IO exception occurred. Check the source code\n");
			} catch (StringIndexOutOfBoundsException e) {
				System.out.println("The input was invalid. Try again!\n");
				
			}
		} while (success == false);
	}

	// Get information from the user
	private static UserInput getUserInput() throws ParseException, InputMismatchException {
		DateTimeFormatter fmt = ISODateTimeFormat.date();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Input a date and time frame(yyyy-MM-dd HH-HH): ");
		String str = scanner.nextLine();
		int startTime = Integer.parseInt(str.substring(11, 13));
		int endTime = Integer.parseInt(str.substring(str.length() - 2, str.length()));
		DateTime date = fmt.parseDateTime(str.substring(0, 10));

		UserInput userInput = new UserInput(date, startTime, endTime);
		scanner.close();
		return userInput;
	}

}
