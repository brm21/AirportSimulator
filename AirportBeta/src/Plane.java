/*
 * Purpose: Data Structure and Algorithms Airport Project
 * Status: Complete and thoroughly tested
 * Last update: 12/04/17
 * Submitted:  12/04/17
 * Comment: test suite and sample run attached
 * @author: Brian Mendoza and Merry Degaga
 * @version: 2017.09.01
 */

public class Plane {
    private String flightNumber;
    private String destination;
    /**
     * Constructor
     * @param flight Is the flight number of a plane
     * @param location Is the destination of a plane
     */
    public Plane(String flight,String location) {
        flightNumber = flight;
        destination = location;
    }
    
    /**
     * @return The flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @return The destination 
     */
    public String getDestination() {
        return destination;
    }
    
    /**
     * @param A statement of the flight number and destination
     */
    public String toString() {
        return "Flight " +flightNumber + " to " + destination + ".";
    }
}
