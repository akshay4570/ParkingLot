package strategy;

import parkingspot.ParkingSpot;
import terminal.Terminal;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ParkingSpotNearEntranceStrategy implements ParkingAssignmentStrategy{

    private Map<String, PriorityQueue<ParkingSpot> > mapTerminalParkingSpot;
    private Set<String> setAvailableSpots;

    @Override
    public ParkingSpot getParkingSpot(Terminal terminal) {
        return null;
    }

    @Override
    public void releaseParkingSpot(ParkingSpot parkingSpot) {

    }
}
