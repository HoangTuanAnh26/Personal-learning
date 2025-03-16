import java.time.Year;
import java.util.Scanner;

public class PersonInfo {
	public static boolean askYesNo(Scanner scanner, String prompt) {
		while (true) {
			System.out.print(prompt);
			String answer = scanner.nextLine().trim().toLowerCase();
			if (answer.equals("yes")) {
				return true;
			} else if (answer.equals("no")) {
				return false;
			}
		}
	}

	public static int calculateAge(int yearBorn) {
		int currentYear = Year.now().getValue();
		return currentYear - yearBorn;
	}

	public static double convertMeterToFeet(double meter) {
		final double METER_TO_FEET = 3.281;
		return Math.round(meter * METER_TO_FEET * 10.0) / 10.0;
	}

	public static void printHeightInfo(double heightFeet, boolean isMale) {
		if (isMale) {
			if (heightFeet > 6.5) {
				System.out.print("You are ");
				for (int i = 0; i < 10; i++) {
					System.out.print("very ");
				}
				System.out.println("tall as a man");
			} else if (heightFeet > 6.0) {
				System.out.println("You are tall as a man");
			} else {
				System.out.println("You are short as a man");
			}
		} else {
			if (heightFeet > 5.7) {
				System.out.println("You are tall as a girl");
			} else if (heightFeet < 5.0) {
				System.out.print("You are ");
				for (int i = 0; i < 10; i++) {
					System.out.print("very ");
				}
				System.out.println("short as a girl");
			} else {
				System.out.println("You are short as a girl");
			}
		}
	}

	public static void printPersonInfo(String firstName, String lastName, int age, double heightFeet,
			boolean isVietnamese, boolean isMale) {
		int currentYear = Year.now().getValue();
		System.out.println("\n---");
		System.out.println("Your name is " + firstName + " " + lastName);
		System.out.printf("%s is %d years old in %d\n", firstName, age, currentYear);
		System.out.println("You are " + heightFeet + " feet tall");
		System.out.println(isVietnamese ? "You are from Vietnam" : "You are not from Vietnam");
		printHeightInfo(heightFeet, isMale);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Your first name: ");
		String firstName = scanner.nextLine();
		System.out.print("Your last name: ");
		String lastName = scanner.nextLine();
		System.out.print("When were you born: ");
		int yearBorn = Integer.parseInt(scanner.nextLine());
		System.out.print("Your height (meter): ");
		double heightMeter = Double.parseDouble(scanner.nextLine());
		boolean isMale = askYesNo(scanner, "Are you male (yes/no): ");
		boolean isVietnamese = askYesNo(scanner, "Are you Vietnamese (yes/no): ");
		scanner.close();

		int age = calculateAge(yearBorn);
		double heightFeet = convertMeterToFeet(heightMeter);
		printPersonInfo(firstName, lastName, age, heightFeet, isVietnamese, isMale);
	}
}
