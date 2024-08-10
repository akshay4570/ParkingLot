package terminal;

import model.Ticket;
import parkingspot.ParkingSpot;
import strategy.ParkingAssignmentStrategy;
import system.ParkingLotSystem;
import system.ParkingLotSystemFactory;
import terminal.Terminal;

public class EntryTerminal extends Terminal {

    public Ticket getTicket(){
        return generateTicket(assignParkingSpot());
    }
    private ParkingSpot assignParkingSpot(){
        ParkingLotSystem parkingLotSystem = getParkingLotSystem();
        return parkingLotSystem.getParkingSpot(this);
    }

    private Ticket generateTicket(ParkingSpot parkingSpot){
        return new Ticket(parkingSpot);
    }
}
