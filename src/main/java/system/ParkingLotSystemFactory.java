package system;

import parkingspot.*;
import payment.Payment;
import strategy.ParkingAssignmentStrategy;
import terminal.EntryTerminal;
import terminal.ExitTerminal;
import terminal.Terminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotSystemFactory {

    public static ParkingLotSystem createParkingLotSystem(Payment payment, ParkingAssignmentStrategy parkingAssignmentStrategy,
                                                          int numOfTerminals, int totalParkingSpots){
        List<Terminal> listTerminal = new ArrayList<>(2*numOfTerminals);
        //Alternatively Creating Entry and Exit Terminals
        for(int i=0;i<numOfTerminals;i++){
            listTerminal.add(new EntryTerminal());
            listTerminal.add(new ExitTerminal());
        }
        System.out.println(listTerminal.size());
        List<ParkingSpot> listParkingSpot = new ArrayList<>(totalParkingSpots);
        //Creating all the Parking Types in a Round Robin Manner
        Double dist = 1.0;
        for(int i=0;i<totalParkingSpots/4;i++){
            listParkingSpot.add(new TwoWheelerParking(calculateDistanceFromTerminals(listTerminal,dist)));
            listParkingSpot.add(new FourWheelerParking(calculateDistanceFromTerminals(listTerminal,dist+1)));
            listParkingSpot.add(new HandicappedParking(calculateDistanceFromTerminals(listTerminal,dist+2)));
            listParkingSpot.add(new MiscellaneousParking(calculateDistanceFromTerminals(listTerminal,dist+3)));
            dist += 4;
        }

        ParkingLotSystem parkingLotSystem = ParkingLotSystem.getInstance();
        parkingLotSystem.init(listTerminal, listParkingSpot, payment, parkingAssignmentStrategy);
        return parkingLotSystem;
    }

    private static Map<String, Double> calculateDistanceFromTerminals(List<Terminal> listTerminal, Double dist) {
        Map<String, Double> mapDist = new HashMap<>();
        Double distTemp = dist;
        for(Terminal objTerminal : listTerminal){
            //Use some metrics to calculate Distance just populated Dummy values;
            mapDist.put(objTerminal.getId(), distTemp);
            distTemp++;
        }
        return mapDist;
    }
}
