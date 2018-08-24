/**
 * Program that models a simplified airport environment. 
 * The airport has only takeoff runways, but no landing runways. 
 * When the user runs this program, the program should ask the user to enter 
 * information about the initial runways. Other runways may be opened or closed later. 
 * The airport system is initialized using this information. 
 * After entering this information, the user should be able to use the system 
 * through an interactive menu that allows planes to enter the system, 
 * planes to take off if they get the clearance or wait to be allowed to re-enter a runway, 
 * for runways to open and close and information about planes to be displayed.
 * 
 * @author Merry DeGaga
 * @author Brian Mendoza
 */

import java.io.*;

public class Airport {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static int inFlight = 0;
	private static int current = 0;

	/**
	 * Will start up the program and initialize number of runways by given input
	 * Names will then be given to each runway by given input
	 */
	public static void main(String args[]) throws IOException {
		ListArrayBased<Runway> runways = new ListArrayBased<>();
		boolean running = true;
		int input = 0;

		int numRunway = 0;
		String runwayInput = "";
		System.out.println("Welcome to the Airport program!\n" + "Enter number of runways: ");
		numRunway = Integer.parseInt(reader.readLine());
		System.out.println(numRunway);
		int i = 0;
		while (i < numRunway) {
			System.out.println("Enter the name of runway number " + (i + 1) + ": ");
			runwayInput = reader.readLine();
			System.out.println(runwayInput);
			runways.add(i, new Runway(runwayInput));
			i++;
		}

		while (running) {
			options();

			input = Integer.parseInt(reader.readLine());
			System.out.println(input);

			menu(input, runways);

			if (input == 9) {
				running = false; // will stop the program
			}

		}
	}

	/**
	 * Options provided to the user
	 */
	private static void options() {
		System.out.println("Select from the following menu:");
		System.out.println("1. Plane enters the system.");
		System.out.println("2. Plane takes off.");
		System.out.println("3. Plane is allowed to re-enter a runway.");
		System.out.println("4. Runway opens.");
		System.out.println("5. Runway closes.");
		System.out.println("6. Display info about planes waiting to take off.");
		System.out.println("7. Display info about planes waiting to be allowed to re-enter the runway.");
		System.out.println("8. Display number of planes who have taken off.");
		System.out.println("9. End the program.");
		System.out.println("Make your selection now: ");
	}

	/**
	 * Runs specified option given by user
	 * 
	 * @param input
	 *            Option input
	 * @param runways
	 *            Runway list
	 * @throws IOException
	 */
	public static void menu(int input, ListArrayBased runways) throws IOException {
		Runway r = (Runway) runways.get(current);
		int size = runways.size();
		String value = "";

		switch (input) {

		case 1:
			/*
			 * Plane entered into runway specified by user Checks for runway validity before
			 * a plane is inserted into runway When inserted in to runway, plane is entered
			 * into a waiting list before take off
			 */
			String flightNum = "";
			String destination = "";
			String runwayName = "";
			boolean valid = false;

			System.out.println("Enter flight number: ");
			flightNum = reader.readLine();
			System.out.println(flightNum);
			System.out.println("Enter destination: ");
			destination = reader.readLine();
			System.out.println(destination);
			while (!valid) { // Will loop until until a valid runway is selected
				System.out.println("Enter runway: ");
				runwayName = reader.readLine();
				System.out.println(runwayName);
				int check = validRunway(runwayName, runways);
				if (check == -1) {
					System.out.println("No such runway!");
				} else {
					r = (Runway) runways.get(check);
					Plane plane = new Plane(flightNum, destination);
					r.addPlane(plane);
					System.out.println(
							"Flight " + flightNum + " is now waiting for takeoff on runway " + runwayName + ".");
					valid = true;

				}

			}
			break;

		case 2:
			/**
			 * Current runway will ask if planes that have clearance are ready for take off
			 * If current runways do not have any planes, it will search next available
			 * runway
			 */
			int count = 0;
			boolean running = true;
			while (running) {
				if (count > runways.size()) {
					System.out.println("No plane on any runway!");
					running = false;
				}

				if (!r.isEmpty()) {
					System.out.println("Is flight " + r.getPlane().getFlightNumber() + " cleared for takeoff(Y/N): ");
					value = reader.readLine();
					System.out.println(value);
					if (value.equalsIgnoreCase("Y")) {
						r.takeoff();
						inFlight++;
					} else {
						r.addToReentry();
					}
					running = false;
				} else {
					current = (current + 1) % size;
					count++;
				}

				r = (Runway) runways.get(current);
			}
			current = (current + 1) % size;

			break;

		case 3:
			/*
			 * Will check runway waiting list of planes If specified plane is in runway, it
			 * will then move the plane into list ready for take off
			 */
			boolean empty = true;

			running = true;

			for (int temp = 0; running && temp < size; temp++) {
				r = (Runway) runways.get(temp);
				boolean searching = true;
				while (searching) {
					if (!r.reentryIsEmpty()) {
						System.out.println("Enter in flight number:");
						value = reader.readLine();
						System.out.println(value);

						if (r.removeFromReentry(value)) {
							empty = false;
							running = false;
							searching = false;
							System.out.println(
									"Flight " + value + " is now waiting for takeoff on runway " + r.getRunwayName());
						} else {
							System.out.println("Flight " + value + " is not waiting for clearance.");
							temp = 0;
							searching = false;
						}
					} else {
						searching = false;
					}
				}
			}
			if (empty && running) {
				System.out.println("There are no planes waiting for clearance!");
			}

			break;

		case 4:
			openRunway(runways);
			break;
		case 5:
			closeRunway(runways);
			break;

		case 6:
			for (int i = 0; i < size; i++) {
				Runway curr = (Runway) runways.get(i);

				curr.displayTakeOff();
			}
			break;

		case 7:
			boolean isEmpty = true;

			for (int i = 0; i < size; i++) {
				Runway curr = (Runway) runways.get(i);
				if (!curr.reentryIsEmpty()) {
					curr.displayReentry();
					isEmpty = false;
				}
			}

			if (isEmpty) {
				System.out.println("No planes are waiting to be cleared to re-enter a runway!");
			}
			break;

		case 8:
			System.out.println(inFlight + " planes have taken off from the airport.");
			break;

		case 9:
			System.out.println("The Airport is closing :Bye Bye....");
			System.exit(0);

			break;
		}

	}

	/**
	 * Creates a new runway as long as runway input does not already exist
	 * 
	 * @param runways
	 * @throws IOException
	 */
	public static void openRunway(ListArrayBased<Runway> runways) throws IOException {
		String newRun = "";

		boolean running = true;
		while (running) {
			System.out.println("Enter the name of the new runway : ");
			newRun = reader.readLine();
			System.out.println(newRun);
			int runwayIndex = validRunway(newRun, runways);
			if (runwayIndex == -1) {
				runways.add(runways.size(), new Runway(newRun));
				System.out.println("Runway " + newRun + " has opened.");
				running = false;
			} else {
				System.out.println("Runway " + newRun + " already exist please choose another name.");

			}
		}
	}

	/**
	 * Choose which runway to close as long as it is valid When runway chosen, move
	 * planes in runway to another existing runway until runway is empty When runway
	 * is empty, runway is then deleted
	 * 
	 * @param runways
	 * @throws IOException
	 */
	public static void closeRunway(ListArrayBased<Runway> runways) throws IOException {
		String closeRunway = "";
		String switchRunway;
		int closeRunwayIndex = 0;
		int newRunwayIndex = 0;
		boolean running = true;
		while (running) {
			System.out.println("Enter runway : ");
			closeRunway = reader.readLine();
			System.out.println(closeRunway);
			closeRunwayIndex = validRunway(closeRunway, runways);
			if (closeRunwayIndex == -1) {
				System.out.println("No such runway!");
				// closeRunway(runways);
			} else if (runways.get(closeRunwayIndex).isEmpty()) {
				System.out.println(
						"Runway " + runways.get(closeRunwayIndex).getRunwayName().toString() + " has been closed.");
				runways.remove(closeRunwayIndex);
			} else {
				while (!(runways.get(closeRunwayIndex).isEmpty())) { // checks for planes waiting list
					Plane p = runways.get(closeRunwayIndex).getPlane();

					System.out.println("Enter new runway for plane " + p.getFlightNumber().toString() + ":");
					switchRunway = reader.readLine();
					System.out.println(switchRunway);
					newRunwayIndex = validRunway(switchRunway, runways);
					if (newRunwayIndex == -1) {
						System.out.println("No such runway!");

					} else if (switchRunway.equals(closeRunway)) {
						System.out.println("This is the runway that is closing!");
					} else {
						runways.get(newRunwayIndex).addPlane(p);
						System.out.println(
								"Flight " + p.getFlightNumber().toString() + " is now waiting for takeoff on runway "
										+ runways.get(newRunwayIndex).getRunwayName().toString());
						runways.get(closeRunwayIndex).removePlane();

					}

				}

				while (!(runways.get(closeRunwayIndex).reentryIsEmpty())) { // checks for reentry plane list
					Plane p = runways.get(closeRunwayIndex).getReentryPlane(0);
					System.out.println("Enter new runway for plane " + p.getFlightNumber().toString() + ":");
					switchRunway = reader.readLine();
					System.out.println(switchRunway);
					newRunwayIndex = validRunway(switchRunway, runways);

					if (newRunwayIndex == -1) {
						System.out.println("No such runway!");

					} else if (switchRunway.equals(closeRunway)) {
						System.out.println("This is the runway that is closing!");
					} else {
						runways.get(newRunwayIndex).addToReentry(p);
						System.out.println(
								"Flight " + p.getFlightNumber().toString() + " is now waiting for takeoff on runway "
										+ runways.get(newRunwayIndex).getRunwayName().toString());

						runways.get(closeRunwayIndex).removePlaneReentry(0);

					}
				}
				System.out.println(
						"Runway " + runways.get(closeRunwayIndex).getRunwayName().toString() + " has been closed.");
				runways.remove(closeRunwayIndex);
				running = false;
			}

			current = (current - 1) % runways.size();

		}

	}

	/**
	 * Checks validity of the name of runway and returns index
	 * 
	 * @param runwayName
	 *            Name of runway input by user
	 * @param runways
	 *            List of runways
	 * @return specified index of runway name given
	 */
	public static int validRunway(String runwayName, ListArrayBased<Runway> runways) {
		for (int i = 0; i < runways.size(); i++) {
			if (runwayName.equals(runways.get(i).getRunwayName())) {
				return i;
			}
		}
		return -1;
	}

}