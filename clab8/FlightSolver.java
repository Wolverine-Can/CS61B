import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    PriorityQueue<Flight> startTimes;
    PriorityQueue<Flight> endTimes;
    int mostPassenger = 0;

    public FlightSolver(ArrayList<Flight> flights) {
        Comparator<Flight> startTimeComparator = (i, j) -> i.startTime() - j.startTime();
        Comparator<Flight> endTimeComparator = (i, j) -> i.endTime() - j.endTime();
        startTimes = new PriorityQueue<Flight>(startTimeComparator);
        endTimes = new PriorityQueue<Flight>(endTimeComparator);
        for (Flight flight : flights) {
            startTimes.add(flight);
            endTimes.add(flight);
        }
    }

    public int solve() {
        int nofPassengers = 0;
        while (!startTimes.isEmpty()) {
            if (startTimes.peek().startTime() <= endTimes.peek().endTime()) {
                nofPassengers += startTimes.poll().passengers();
                if (nofPassengers > mostPassenger) {
                    mostPassenger = nofPassengers;
                }
            } else {
                nofPassengers -= endTimes.poll().passengers();
            }
        }
        return mostPassenger;
    }

}
