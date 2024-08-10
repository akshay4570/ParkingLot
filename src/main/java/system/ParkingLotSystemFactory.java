package system;

import parkingspot.*;
import payment.Payment;
import strategy.ParkingAssignmentStrategy;
import terminal.EntryTerminal;
import terminal.ExitTerminal;
import terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystemFactory {

    public static ParkingLotSystem createParkingLotSystem(Payment payment, ParkingAssignmentStrategy parkingAssignmentStrategy,
                                                          int numOfTerminals, int totalParkingSpots){
        List<Terminal> listTerminal = new ArrayList<>(2*numOfTerminals);
        //Alternatively Creating Entry and Exit Terminals
        for(int i=0;i<numOfTerminals;i++){
            listTerminal.set(i, new EntryTerminal());
            listTerminal.set(i+1, new ExitTerminal());
        }
        List<ParkingSpot> listParkingSpot = new ArrayList<>(totalParkingSpots);
        //Creating all the Parking Types in a Round Robin Manner
        for(int i=0;i<totalParkingSpots/4;i++){
            listParkingSpot.set(i, new TwoWheelerParking(calculateDistanceFromTerminals(listTerminal)));
            listParkingSpot.set(i+1, new FourWheelerParking(calculateDistanceFromTerminals(listTerminal)));
            listParkingSpot.set(i+2, new HandicappedParking(calculateDistanceFromTerminals(listTerminal)));
            listParkingSpot.set(i+3, new MiscellaneousParking(calculateDistanceFromTerminals(listTerminal)));
        }

        ParkingLotSystem parkingLotSystem = ParkingLotSystem.getInstance();
        parkingLotSystem.init(listTerminal, listParkingSpot, payment, parkingAssignmentStrategy);
        return parkingLotSystem;
    }

    private static List<Double> calculateDistanceFromTerminals(List<Terminal> listTerminal) {
        List<Double> listDist = new ArrayList<>(listTerminal.size());
        for(Terminal objTerminal : listTerminal){
            //Use some metrics to calculate Distance just populated Dummy values;
            Double dist = 1.0;
            listDist.add(dist);
        }
        return listDist;
    }
}
