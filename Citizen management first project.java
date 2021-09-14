package project_11;

import java.util.*;

import javax.swing.JOptionPane;

public class FinalProject_11 {
	static Scanner sc = new Scanner(System.in);
	static final int LENGTH = 100;
	static int count = 0;

	public static void main(String[] args) {
		String[] citizenName = new String[LENGTH];
		String[] citizenId = new String[LENGTH];
		String[] city = new String[LENGTH];
		String[] salary = new String[LENGTH];

		boolean chosenNum = true;
		System.out.println("Welcome to Citizens Manager Project");

		do {

			do {
				int taskNumber = showMenu();
				switch (taskNumber) {
				// task 1
				case 1:
					System.out.println("Enter the following info :");
					newCitizen(citizenName, citizenId, city, salary);
					chosenNum = true;
					break;

				// task2
				case 2:
					modifyCitizen(citizenName, citizenId, city, salary);
					chosenNum = true;
					break;
				// task3
				case 3:
					searchForCitizen(citizenName, citizenId, city, salary);
					chosenNum = true;
					break;
				// task4
				case 4:
					salaryWithTax(citizenName, citizenId, city, salary);
					chosenNum = true;
					break;

				// task5
				case 5:
					report(citizenName, citizenId, city, salary);
					chosenNum = true;
					break;
				case 6:
					System.out.println("******    *   *     *****     ******    *   *     *****\n"
							+ "*     *   *   *     *         *     *   *   *     *    \n"
							+ "*      *  *   *     *         *      *  *   *     *    \n"
							+ "*     *   *   *     *         *     *   *   *     *    \n"
							+ "******    *****     *****     ******    *****     *****\n"
							+ "*     *       *     *         *     *       *     *    \n"
							+ "*      *  *   *     *         *      *  *   *     *    \n"
							+ "*     *   *   *     *         *     *   *   *     *    \n"
							+ "******    *****     *****     ******    *****     *****\n");
					System.exit(0);
					break;
				default:
					JOptionPane.showMessageDialog(null, "Wrong choice!", "Error", JOptionPane.ERROR_MESSAGE);
					chosenNum = false;
				}
			} while (!chosenNum);
		} while (chosenNum);
	}

	public static int showMenu() {
		System.out.println("Please choose one of the following tasks: \n" + "1. Add a new citizen\n"
				+ "2. Modify existing citizen\n" + "3. Search for a citizen\n" + "4. Compute income Tax\n"
				+ "5. Display reports\n" + "6. Exit the program\n" + "");
		int usersChoice = sc.nextInt();
		return usersChoice;
	}

	public static void newCitizen(String[] arr1, String[] arr2, String[] arr3, String[] arr4) {
		// checking if the name is valid
		boolean checkName = true;
		String cName = "";
		sc.nextLine();
		do {
			System.out.println("Enter citizen's name:");
			cName = sc.nextLine();
			for (int i = 0; i < cName.length(); i++) {
				if (Character.isLetter(cName.charAt(i)) || cName.charAt(i) == (char) 32) {
					checkName = true;
				} else {
					checkName = false;
					break;
				}
			}
			// storing the valid name
			if (!checkName) {
				JOptionPane.showMessageDialog(null, "Names must contain letters only!", "Input not accepted.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				arr1[count] = cName.toLowerCase();
				JOptionPane.showMessageDialog(null, "The input has been stored!", "Input accepted.",
						JOptionPane.INFORMATION_MESSAGE);

			}
		} while (!checkName);
		// checking if the id is valid
		boolean checkId = true;
		String cId = "";
		do {
			System.out.println("Enter citizen's ID:");
			cId = sc.nextLine();
			if (cId.length() == 10) {
				for (int i = 0; i < cId.length(); i++) {
					if (!Character.isDigit(cId.charAt(i))) {
						checkId = false;
					} else {
						checkId = true;
					}
				}
			}

			else
				checkId = false;

			// storing the valid ID
			if (!checkId) {
				JOptionPane.showMessageDialog(null, "An ID cannot be nothing but 10-digits!", "Input not accepted.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				arr2[count] = cId;
				JOptionPane.showMessageDialog(null, "The input has been stored!", "Input accepted.",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} while (!checkId);
		// checking if the city is valid
		boolean checkCity = true;
		String cCity = "";
		do {
			System.out.println("Enter where the citizen lives:");
			cCity = sc.nextLine();
			for (int i = 0; i < cCity.length(); i++) {
				if (Character.isLetter(cCity.charAt(i)) || cCity.charAt(i) == (char) 32) {
					checkCity = true;
				} else {
					checkCity = false;
					break;
				}
			}
			// storing the valid city
			if (!checkCity) {
				JOptionPane.showMessageDialog(null, "Cities names must contain letters only!", "Input not accepted.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				arr3[count] = cCity.toLowerCase();
				JOptionPane.showMessageDialog(null, "The input has been stored!", "Input accepted.",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} while (!checkCity);
		// checking if the salary is valid
		boolean checkSalary = true;
		String cSalary = "";
		do {
			System.out.println("Enter citizen's salary amount in Dollars:");
			cSalary = sc.nextLine();
			for (int i = 0; i < cSalary.length(); i++) {
				if (Character.isDigit(cSalary.charAt(i))) {
					checkSalary = true;
				} else {
					checkSalary = false;
					break;
				}
			}
			// storing the valid ID
			if (!checkSalary) {
				JOptionPane.showMessageDialog(null, "The salary amount should consist of digits only!",
						"Input not accepted.", JOptionPane.ERROR_MESSAGE);
			} else {
				arr4[count] = cSalary;
				JOptionPane.showMessageDialog(null, "The input has been stored!", "Input accepted.",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} while (!checkSalary);
		count++;
	}

	public static void modifyCitizen(String[] arr1, String[] arr2, String[] arr3, String[] arr4) {

		System.out.println("Please enter the citizen's ID:");
		sc.nextLine();
		String id = sc.nextLine();
		for (int i = 0; i < count; i++) {
			if (arr2[i].compareTo(id) == 0) {
				// checking if the new city is valid
				boolean checkCity = true;
				do {
					System.out.println("Enter the new city where " + arr1[i] + " lives: ");
					String newCity = sc.nextLine();
					newCity = newCity.toLowerCase();
					for (int c = 0; c < newCity.length(); c++) {
						if (Character.isLetter(newCity.charAt(c)) || newCity.charAt(c) == (char) 32) {
							checkCity = true;
						} else {
							checkCity = false;
							break;
						}
					}
					// storing the valid city
					if (!checkCity) {
						JOptionPane.showMessageDialog(null, "City names must contain letters only!",
								"Input not accepted.", JOptionPane.ERROR_MESSAGE);

					} else {
						arr3[i] = newCity;
						JOptionPane.showMessageDialog(null, "The city name has been updated successfully!",
								"Input accepted.", JOptionPane.INFORMATION_MESSAGE);
					}
				} while (!checkCity);
				// checking if the new salary is valid
				boolean checkSalary = true;
				do {
					System.out.println("Enter the new salary for citizen " + arr1[i] + " :");
					String newSalary = sc.nextLine();
					for (int j = 0; j < newSalary.length(); j++) {
						if (Character.isDigit(newSalary.charAt(j))) {
							checkSalary = true;
						} else {
							checkSalary = false;
							break;
						}
					}
					// storing the valid ID
					if (!checkSalary) {
						JOptionPane.showMessageDialog(null, "The salary amount should consist of digits only!",
								"Input not accepted.", JOptionPane.ERROR_MESSAGE);
					} else {
						arr4[i] = newSalary;
						JOptionPane.showMessageDialog(null, "The salary amount has been updated successfully!",
								"Input accepted.", JOptionPane.INFORMATION_MESSAGE);
					}
				} while (!checkSalary);
			} else {
				JOptionPane.showMessageDialog(null, "Citizen is not found!", "Input not accepted.",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public static void searchForCitizen(String[] arr1, String[] arr2, String[] arr3, String[] arr4) {

		// checking if the name is valid
		boolean checkName = true;
		String enteredName = "";
		sc.nextLine();
		do {
			System.out.println("Enter citizen's name:");
			enteredName = sc.nextLine();
			enteredName = enteredName.toLowerCase();
			for (int i = 0; i < enteredName.length(); i++) {
				if (Character.isLetter(enteredName.charAt(i)) || enteredName.charAt(i) == (char) 32) {
					checkName = true;
				} else {
					checkName = false;
					break;
				}
			}
			// storing the valid name
			if (!checkName) {
				System.out.println("Names must contain letters only");
			} else {
				for (int k = 0; k < count; k++) {
					if (enteredName.compareTo(arr1[k]) == 0) {
						System.out.println("The citizen " + arr1[k] + " has the ID number: " + arr2[k] + "\nlives in "
								+ arr3[k] + " and has the salary of" + arr4[k]);
						System.out.println();
					} else
						JOptionPane.showMessageDialog(null, "Citizen is not found!", "Input not accepted.",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		} while (!checkName);
	}
	public static void salaryWithTax(String[] arr1, String[] arr2, String[] arr3, String[] arr4) {
		System.out.println("Enter tax amount percantage:");
		double taxAmount = sc.nextDouble();
		double newSalary = 0;
		System.out.printf("%-20s %-20s %-20s %-20s %-20s \n",
						  "Citziten's Name", "Citizen's ID", "City", "Salary","Salary with tax");
		System.out.println();
		for (int i = 0; i < count; i++) {
			for (int c = 0; c < 100; c++) {
				System.out.print("*");
			}
			System.out.println();
			double originalSalary = Double.parseDouble(arr4[i]);
			double tax = originalSalary * (taxAmount / 100);
			newSalary = originalSalary + tax;
			System.out.printf("%-20s %-20s %-20s %-20s %-20.2f \n", arr1[i], arr2[i], arr3[i], arr4[i], newSalary);
			System.out.println();
		}
	}
	public static void report(String[] arr1, String[] arr2, String[] arr3, String arr4[]) {
		System.out.println("Choose which report you want to display:" 
						 + "\nA. All citizens who live at the same city."
						 + "\nB. All citizens sorted according to their salary descending."
						 + "\nC. Total number of citizens and their average salary.");
		String option = sc.next();
		option = option.toLowerCase();
		char reportOption = option.charAt(0);
		switch (reportOption) {
		case 'a':
			sc.nextLine();
			boolean checkCity = false;
			System.out.println("Enter a city:");
			String enteredCity = sc.nextLine();
			enteredCity = enteredCity.toLowerCase();
			for (int i = 0; i < count; i++) {
				if (arr3[i].compareTo(enteredCity) == 0) {
					checkCity = true;
					System.out.println("Citizen " + arr1[i] + " lives in " + enteredCity);
				}
			}
			if (!checkCity) {
				JOptionPane.showMessageDialog(null, "No citizens live in " + enteredCity, "Result Not Found",
						JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		case 'b':
			sortdecent(arr1, arr2, arr3, arr4);
			break;
		case 'c':
			double sum = 0;
			int[] salaryInt = new int[count];
			for (int c = 0; c < count; c++) {
				salaryInt[c] = Integer.parseInt(arr4[c]);
				sum += salaryInt[c];
			}
			double avg = sum / count;
			System.out.println("There are " + count + " citizens stored in the program.");
			System.out.printf("The average of the stored salaries is: %.2f\n ", avg);
		}

	}

	public static void swap(String[] arr, int a, int b) {
		String tempMax = arr[a];
		arr[a] = arr[b];
		arr[b] = tempMax;
	}
	public static void swap(int[] arr, int a, int b) {
		int tempMax = arr[a];
		arr[a] = arr[b];
		arr[b] = tempMax;
	}

	public static void print(String[] citizenName, String[] citizenId, String[] city, String[] salary) {
		System.out.println("This table shows all the citizens arranged according to their salaries descending\n");
		System.out.printf("%-20s %-20s %-20s %-20s\n", "Citizen Name", "Citizen ID", "City", "Salary");
		for (int i = 0; i < count; i++) {
			System.out.printf("%-20s %-20s %-20s %-20s\n", citizenName[i], citizenId[i], city[i], salary[i]);
		}
		System.out.println();
	}

	private static void sortdecent(String[] arr1, String[] arr2, String[] arr3, String[] arr4) {
		int[] salaryAmount = new int[count];
		for (int j = 0; j < count; j++) {
			salaryAmount[j] = Integer.parseInt(arr4[j]);
		}
		int tempMax;
		for (int i = 0; i < salaryAmount.length - 1; i++) {
			tempMax = salaryAmount[i];
			for (int j = i + 1; j < salaryAmount.length; j++) {
				if (tempMax < salaryAmount[j]) {
					swap(salaryAmount, j, i);
					swap(arr4, j, i);
					swap(arr3, j, i);
					swap(arr2, j, i);
					swap(arr1, j, i);
				}
			}
		}
		print(arr1, arr2, arr3, arr4);
	}
}