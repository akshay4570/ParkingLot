import parkingspot.*;
import payment.Payment;
import payment.UPIPayment;
import strategy.ParkingAssignmentStrategy;
import strategy.ParkingSpotNearEntranceStrategy;
import terminal.EntryTerminal;
import terminal.ExitTerminal;
import terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystemFactory {

    private static final Integer NUM_OF_TERMINALS = 4;
    private static final Integer NUM_OF_PARKING_SPOTS = 10000;
    public ParkingLotSystem createParkingLotSystem(){
        Payment payment = new UPIPayment();
        List<Terminal> listTerminal = new ArrayList<>(2*NUM_OF_TERMINALS);
        //Alternatively Creating Entry and Exit Terminals
        for(int i=0;i<NUM_OF_TERMINALS;i++){
            listTerminal.set(i, new EntryTerminal());
            listTerminal.set(i+1, new ExitTerminal(payment));
        }
        ParkingAssignmentStrategy parkingAssignmentStrategy = new ParkingSpotNearEntranceStrategy();
        List<ParkingSpot> listParkingSpot = new ArrayList<>(NUM_OF_PARKING_SPOTS);
        //Creating all the Parking Types in a Round Robin Manner
        for(int i=0;i<NUM_OF_PARKING_SPOTS/4;i++){
            listParkingSpot.set(i, new TwoWheelerParking(calculateDistanceFromTerminals(listTerminal)));
            listParkingSpot.set(i+1, new FourWheelerParking(calculateDistanceFromTerminals(listTerminal)));
            listParkingSpot.set(i+2, new HandicappedParking(calculateDistanceFromTerminals(listTerminal)));
            listParkingSpot.set(i+3, new MiscellaneousParking(calculateDistanceFromTerminals(listTerminal)));
        }

        ParkingLotSystem parkingLotSystem = ParkingLotSystem.getInstance();
        parkingLotSystem.init(listTerminal, listParkingSpot, payment, parkingAssignmentStrategy);
        return parkingLotSystem;
    }

    private List<Double> calculateDistanceFromTerminals(List<Terminal> listTerminal) {
        List<Double> listDist = new ArrayList<>(listTerminal.size());
        for(Terminal objTerminal : listTerminal){
            //Use some metrics to calculate Distance just populated Dummy values;
            Double dist = 1.0;
            listDist.add(dist);
        }
        return listDist;
    }
}
