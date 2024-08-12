package terminal;

import amount.AmountCalculator;
import amount.AmountCalculatorFactory;
import model.Ticket;
import payment.Payment;
import payment.UPIPayment;
import system.ParkingLotSystem;
import terminal.Terminal;

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
