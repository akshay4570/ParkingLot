import model.Ticket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkingspot.ParkingSportWithDistance;
import parkingspot.ParkingSpot;
import payment.Payment;
import payment.UPIPayment;
import strategy.ParkingAssignmentStrategy;
import strategy.ParkingSpotNearEntranceStrategy;
import system.ParkingLotSystem;
import system.ParkingLotSystemFactory;
import terminal.EntryTerminal;
import terminal.ExitTerminal;
import terminal.Terminal;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestFunctionality {

    private Payment payment;
    private ParkingAssignmentStrategy parkingAssignmentStrategy;
    private int numOfTerminals;
    private int totalParkingSpots;
//    private ParkingLotSystem parkingLotSystem;
    @Before
    public void dataSetup(){
        payment = new UPIPayment();
        parkingAssignmentStrategy = new ParkingSpotNearEntranceStrategy();
        numOfTerminals = 4;
        totalParkingSpots = 1000;
//        parkingLotSystem = ParkingLotSystemFactory.createParkingLotSystem(payment, parkingAssignmentStrategy, numOfTerminals, totalParkingSpots);
    }

//    @Test
//    public void testDefaultBehaviour(){
//        ParkingLotSystem parkingLotSystem = ParkingLotSystemFactory.createParkingLotSystem(payment, parkingAssignmentStrategy, numOfTerminals, totalParkingSpots);
//        Assert.assertNotNull(parkingLotSystem);
//    }
//
//    @Test
//    public void testEntryConditionWithSingleThread(){
//        ParkingLotSystem parkingLotSystem = ParkingLotSystemFactory.createParkingLotSystem(payment, parkingAssignmentStrategy, numOfTerminals, totalParkingSpots);
//        EntryTerminal entryTerminal = (EntryTerminal) parkingLotSystem.getListTerminal().get(0);
//        ExitTerminal exitTerminal = (ExitTerminal) parkingLotSystem.getListTerminal().get(1);
//        Ticket ticket = entryTerminal.getTicket();
//        Assert.assertNotNull(ticket);
//        ParkingSpot parkingSpotActual = ticket.getParkingSpot();
//        Payment payment = parkingLotSystem.getPaymentForParking(ticket);
//        Assert.assertEquals(payment.getClass(), UPIPayment.class);
//        ParkingSpot parkingSpotExpected = parkingAssignmentStrategy.getParkingSpot(entryTerminal);
//        Assert.assertEquals(parkingSpotExpected, parkingSpotActual);
//    }

    @Test
    public void testEntryConditionWithMultipleThread(){
        ParkingLotSystem parkingLotSystem = ParkingLotSystemFactory.createParkingLotSystem(payment, parkingAssignmentStrategy, numOfTerminals, totalParkingSpots);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        EntryTerminal entryTerminal = (EntryTerminal) parkingLotSystem.getListTerminal().get(0);
        ExitTerminal exitTerminal = (ExitTerminal) parkingLotSystem.getListTerminal().get(1);
        CompletableFuture.runAsync(() -> {
            Ticket ticket = entryTerminal.getTicket();
            Assert.assertNotNull(ticket);
            ParkingSpot parkingSpotActual = ticket.getParkingSpot();
            Payment payment = parkingLotSystem.getPaymentForParking(ticket);
            Assert.assertEquals(payment.getClass(), UPIPayment.class);
            ParkingSpot parkingSpotExpected = parkingAssignmentStrategy.getParkingSpot(entryTerminal);
            Assert.assertEquals(parkingSpotExpected, parkingSpotActual);
        }, executorService);

        CompletableFuture.runAsync(() -> {
            Ticket ticket = entryTerminal.getTicket();
            Assert.assertNotNull(ticket);
            ParkingSpot parkingSpotActual = ticket.getParkingSpot();
            Payment payment = parkingLotSystem.getPaymentForParking(ticket);
            Assert.assertEquals(payment.getClass(), UPIPayment.class);
            ParkingSpot parkingSpotExpected = parkingAssignmentStrategy.getParkingSpot(entryTerminal);
            Assert.assertEquals(parkingSpotExpected, parkingSpotActual);
        }, executorService);
    }
}
