package terminal;

import model.Ticket;
import parkingspot.ParkingSpot;
import system.ParkingLotSystem;

import java.util.UUID;

public class EntryTerminal extends Terminal {

    public EntryTerminal() {
        super(UUID.randomUUID().toString());
    }

    public Ticket getTicket(){
        return generateTicket(assignParkingSpot());
    }
    private ParkingSpot assignParkingSpot(){
        ParkingLotSystem parkingLotSystem = getParkingLotSystem();
        return parkingLotSystem.getParkingSpot(this);
    }

    private Ticket generateTicket(ParkingSpot parkingSpot){
        return new Ticket(parkingSpot, this);
    }
}
