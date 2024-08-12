import model.Ticket;
import parkingspot.ParkingSpot;
import payment.Payment;
import payment.UPIPayment;
import strategy.ParkingSpotNearEntranceStrategy;
import system.ParkingLotSystem;
import system.ParkingLotSystemFactory;
import terminal.EntryTerminal;
import terminal.Terminal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        ParkingLotSystem parkingLotSystem = ParkingLotSystemFactory.createParkingLotSystem(
                new UPIPayment(),
                new ParkingSpotNearEntranceStrategy(),
                4,
                10000
        );
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        EntryTerminal entryTerminal = new EntryTerminal();
        executorService.submit(() -> {
            ParkingSpot parkingSpot = parkingLotSystem.getParkingSpot(entryTerminal);
            Ticket ticket = entryTerminal.getTicket();
            System.out.println("Get the Ticket and Park the Vehicle");
            Payment payment = parkingLotSystem.getPaymentForParking(ticket);
            System.out.println("Release the spot and receive the amount");
        });
    }
}