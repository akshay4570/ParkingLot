package system;

import amount.AmountCalculator;
import amount.AmountCalculatorFactory;
import model.Ticket;
import parkingspot.ParkingSpot;
import payment.Payment;
import strategy.ParkingAssignmentStrategy;
import terminal.EntryTerminal;
import terminal.Terminal;
import utils.Utils;

import java.util.List;

public class ParkingLotSystem {
    private List<Terminal> listTerminal;
    private List<ParkingSpot> listParkingSpot;
    private Payment payment;
    private ParkingAssignmentStrategy parkingAssignmentStrategy;

    private static ParkingLotSystem parkingLotSystem = null;
    private ParkingLotSystem(){
    }

    public static ParkingLotSystem getInstance() {
        if(parkingLotSystem == null) {
            synchronized (ParkingLotSystem.class){
                if(parkingLotSystem == null){
                    parkingLotSystem = new ParkingLotSystem();
                }
            }
        }
        return parkingLotSystem;
    }

    public void init(List<Terminal> listTerminal, List<ParkingSpot> listParkingSpot, Payment payment, ParkingAssignmentStrategy parkingAssignmentStrategy){
        this.listTerminal = listTerminal;
        this.listParkingSpot = listParkingSpot;
        this.payment = payment;
        this.parkingAssignmentStrategy = parkingAssignmentStrategy;
        this.parkingAssignmentStrategy.setup(listTerminal, listParkingSpot);
    }

    public ParkingSpot getParkingSpot(EntryTerminal entryTerminal) {
        return parkingAssignmentStrategy.getParkingSpot(entryTerminal);
    }

    public Payment getPaymentForParking(Ticket ticket) {
        AmountCalculator amountCalculator = AmountCalculatorFactory.getAmountCalculator();
        Double amountToBePaid = amountCalculator.calculateTotalAmount(Utils.getTotalTimeInHours(ticket.getIssueTime()), ticket.getParkingSpot());
        payment.process(amountToBePaid);
        parkingAssignmentStrategy.releaseParkingSpot(ticket.getParkingSpot());
        return payment;
    }
}

