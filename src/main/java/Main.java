import model.Ticket;
import parkingspot.ParkingSpot;
import payment.Payment;
import payment.UPIPayment;
import strategy.ParkingSpotNearEntranceStrategy;
import system.ParkingLotSystem;
import system.ParkingLotSystemFactory;
import terminal.EntryTerminal;
import terminal.Terminal;

public class Main {
    public static void main(String[] args) {

        ParkingLotSystem parkingLotSystem = ParkingLotSystemFactory.createParkingLotSystem(
                new UPIPayment(),
                new ParkingSpotNearEntranceStrategy(),
                4,
                10000
        );
        EntryTerminal entryTerminal = new EntryTerminal();
        ParkingSpot parkingSpot = parkingLotSystem.getParkingSpot(entryTerminal);
        Ticket ticket = entryTerminal.getTicket();
        Payment payment = parkingLotSystem.getPaymentForParking(ticket);
    }
}