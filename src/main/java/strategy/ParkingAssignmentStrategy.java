package strategy;

import parkingspot.ParkingSportWithDistance;
import parkingspot.ParkingSpot;
import terminal.Terminal;

import java.util.List;

public interface ParkingAssignmentStrategy {

    public ParkingSpot getParkingSpot(Terminal terminal);

    public void releaseParkingSpot(ParkingSportWithDistance parkingSpot);

    void setup(List<Terminal> listTerminal, List<ParkingSpot> listParkingSpot);
}
