package strategy;

import parkingspot.ParkingSportWithDistance;
import parkingspot.ParkingSpot;
import terminal.Terminal;

import java.util.List;

public class ParkingSpotNearElevatorStrategy implements ParkingAssignmentStrategy{

    @Override
    public ParkingSpot getParkingSpot(Terminal terminal) {
        return null;
    }

    @Override
    public void releaseParkingSpot(ParkingSportWithDistance parkingSpot) {

    }

    @Override
    public void setup(List<Terminal> listTerminal, List<ParkingSpot> listParkingSpot) {

    }
}
