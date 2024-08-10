package strategy;

import parkingspot.ParkingSpot;
import terminal.Terminal;

public interface ParkingAssignmentStrategy {

    public ParkingSpot getParkingSpot(Terminal terminal);

    public void releaseParkingSpot(ParkingSpot parkingSpot);
}
