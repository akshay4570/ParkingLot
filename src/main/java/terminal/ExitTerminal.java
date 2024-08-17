package terminal;

import model.Ticket;
import payment.Payment;
import system.ParkingLotSystem;

import java.util.UUID;

public class ExitTerminal extends Terminal {

    public ExitTerminal() {
        super(UUID.randomUUID().toString());
    }

    public Payment showTicket(Ticket ticket){
        ParkingLotSystem parkingLotSystem = getParkingLotSystem();
        return parkingLotSystem.getPaymentForParking(ticket);
    }
}
