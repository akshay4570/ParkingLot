import parkingspot.ParkingSpot;
import payment.Payment;
import strategy.ParkingAssignmentStrategy;
import terminal.Terminal;

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
    }


}

