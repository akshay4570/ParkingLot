package terminal;

import model.Ticket;
import parkingspot.ParkingSpot;
import strategy.ParkingAssignmentStrategy;
import terminal.Terminal;

public class EntryTerminal extends Terminal {

    public Ticket getTicket(ParkingSpot parkingSpot){
        return new Ticket(parkingSpot);
    }
}
