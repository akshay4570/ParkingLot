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

public class TestFunctionality {

    private Payment payment;
    private ParkingAssignmentStrategy parkingAssignmentStrategy;
    private int numOfTerminals;
    private int totalParkingSpots;
    @Before
    public void dataSetup(){
        payment = new UPIPayment();
        parkingAssignmentStrategy = new ParkingSpotNearEntranceStrategy();
        numOfTerminals = 4;
        totalParkingSpots = 1000;
    }

    @Test
    public void testDefaultBehaviour(){
        ParkingLotSystem parkingLotSystem = ParkingLotSystemFactory.createParkingLotSystem(payment, parkingAssignmentStrategy, numOfTerminals, totalParkingSpots);
        Assert.assertNotNull(parkingLotSystem);
    }

    @Test
    public void testEntryConditionWithSingleThread(){
        ParkingLotSystem parkingLotSystem = ParkingLotSystemFactory.createParkingLotSystem(payment, parkingAssignmentStrategy, numOfTerminals, totalParkingSpots);
        EntryTerminal entryTerminal = (EntryTerminal) parkingLotSystem.getListTerminal().get(0);
        Ticket ticket = entryTerminal.getTicket();
        Assert.assertNotNull(ticket);
        ParkingSpot parkingSpotActual = ticket.getParkingSpot();
        Payment payment = parkingLotSystem.getPaymentForParking(ticket);
        ParkingSpot parkingSpotExpected = parkingAssignmentStrategy.getParkingSpot(entryTerminal);
        Assert.assertEquals(parkingSpotExpected, parkingSpotActual);
    }
}
