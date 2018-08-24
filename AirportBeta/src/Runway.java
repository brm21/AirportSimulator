/**
 * Runway class that holds a list of planes specified to specific runway. User
 * will enter new and existing to planes to list in the runway class
 * 
 * @author Brian Mendoza
 * @author Merry DeGaga
 *
 */

public class Runway {
	private String runway;
	private Queue<Plane> waiting;
	private ListArrayBasedPlus<Plane> reentry;

	/**
	 * Initializes a new runway to be generated in the list of runways Creates a
	 * queue for planes waiting to enter runway Creates a list for planes ready for
	 * take off
	 * 
	 * @param runwayName
	 *            New Runway
	 */
	public Runway(String runwayName) {
		runway = runwayName;
		waiting = new Queue<>();
		reentry = new ListArrayBasedPlus<>();
	}

	/**
	 * Adds new planes with name and destination and entered in queue of planes
	 * waiting to enter the runway
	 * 
	 * @param newPlane
	 *            New Plane
	 */
	public void addPlane(Plane newPlane) {
		waiting.enqueue(newPlane);

	}

	/**
	 * Returns the name of specified runway index
	 * 
	 * @return Runway Name
	 */
	public String getRunwayName() {
		return runway;
	}

	/**
	 * Displays information of a flight that has taken off
	 */
	public void takeoff() {

		if (!isEmpty()) {
			System.out.println(
					"Flight " + waiting.dequeue().getFlightNumber() + " has now taken off from runway " + runway);

		}

	}

	public void addToReentry() {
		Plane p = waiting.dequeue();

		System.out.println("Flight " + p.getFlightNumber() + " is now waiting to be allowed to re-enter a runway.");
		reentry.add(reentry.size(), p);

	}

	/**
	 * Moves planes to the re entry list of planes ready to take off
	 * 
	 * @param Exisiting
	 *            Plane
	 */
	public void addToReentry(Plane p) {
		System.out.println("Flight " + p.getFlightNumber() + " is now waiting to be allowed to re-enter a runway.");
		reentry.add(reentry.size(), p);

	}

	/**
	 * Searches re entry list of planes to remove plane and add to waitlist because
	 * plane is not ready for take off
	 * 
	 * @param Plane
	 *            flightNumber
	 * @return True if plane is found in re entry list
	 */
	public boolean removeFromReentry(String flightNumber) {
		boolean searching = true;
		boolean removed = false;
		int size = reentry.size();
		int i = 0;
		if (!(reentry.isEmpty())) {
			while (searching && i < size) {
				Plane p = (Plane) reentry.get(i);
				if (p.getFlightNumber().equals(flightNumber)) {
					searching = false;
					reentry.remove(i);
					addPlane(p);
					removed = true;
				} else {
					i++;
				}
			}
		}

		return removed;
	}

	/**
	 * Displays list of planes waiting for take off
	 */
	public void displayTakeOff() {
		if (!isEmpty()) {
			System.out.println("These planes are waiting for takeoff on runway " + runway + ": ");
			waiting.toString();
		} else {
			System.out.println("No planes are waiting for takeoff on runway " + runway + "!");
		}
	}

	/**
	 * Displays list planes waiting to be cleared to re enter runway
	 */
	public void displayReentry() {
		if (!reentry.isEmpty()) {
			System.out.println("These planes are waiting to be cleared to re-enter a runway: ");

			System.out.println(reentry.toString());
		} else {
			System.out.println("No planes are waiting to be cleared to re-enter a runway!");
		}

	}

	/**
	 * 
	 * @return Plane that was removed from wait list
	 */
	public Plane removePlane() {
		return waiting.dequeue();
	}

	/**
	 * 
	 * @return Plane Object
	 */
	public Plane getPlane() {
		return this.waiting.peek();
	}

	/**
	 * 
	 * @return True if plane queue is empty
	 */
	public boolean isEmpty() {
		return waiting.isEmpty();
	}

	/**
	 * 
	 * @return True if re entry list of planes is empty
	 */
	public boolean reentryIsEmpty() {
		return reentry.isEmpty();
	}

	/**
	 * 
	 * @param index
	 *            in re entry list
	 * @return plane in that index
	 */
	public Plane getReentryPlane(int index) {
		Plane p = null;
		int size = reentry.size();

		if (index < size) {
			p = reentry.get(index);
		}

		return p;
	}

	/**
	 * Removes plane is re entry list specified by index
	 * 
	 * @param index
	 * of plane in re entry list
	 */
	public void removePlaneReentry(int index) {

		reentry.remove(index);

	}
}