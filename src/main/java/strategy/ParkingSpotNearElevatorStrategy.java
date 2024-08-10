package strategy;

import parkingspot.ParkingSpot;
import terminal.Terminal;

public class ParkingSpotNearElevatorStrategy implements ParkingAssignmentStrategy{

    @Override
    public ParkingSpot getParkingSpot(Terminal terminal) {
        return null;
    }

    @Override
    public void releaseParkingSpot(ParkingSpot parkingSpot) {

    }
}
