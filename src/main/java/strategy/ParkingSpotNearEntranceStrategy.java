package strategy;

import parkingspot.ParkingSpot;
import parkingspot.ParkingSportWithDistance;
import terminal.EntryTerminal;
import terminal.Terminal;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingSpotNearEntranceStrategy implements ParkingAssignmentStrategy{

    private Map<String, PriorityQueue<ParkingSportWithDistance> > mapTerminalParkingSpot;
    private Map<String, Set<String>> mapTerminalCopy;
    private Set<String> setAvailableSpots;
    private Set<String> setReservedSpots;

    public ParkingSpotNearEntranceStrategy() {
        this.mapTerminalParkingSpot = new ConcurrentHashMap<>();
        this.mapTerminalCopy = new ConcurrentHashMap<>();
        this.setAvailableSpots = ConcurrentHashMap.newKeySet();
        this.setReservedSpots = ConcurrentHashMap.newKeySet();
    }

    @Override
    public ParkingSpot getParkingSpot(Terminal terminal) {
        String key = terminal.getId();
        ParkingSpot parkingSpot = null;
        try {
            if(mapTerminalParkingSpot.containsKey(key)) {
                for (ParkingSportWithDistance objPar : mapTerminalParkingSpot.get(key)) {
                    String parkingSpotId = objPar.getParkingSpot().getId();
                    synchronized (this){
                        if (setReservedSpots.contains(parkingSpotId)){
                            mapTerminalParkingSpot.get(key).poll();
                            mapTerminalCopy.get(key).remove(parkingSpotId);
                            setAvailableSpots.remove(parkingSpotId);
                        }else {
                            parkingSpot = mapTerminalParkingSpot.get(key).poll().getParkingSpot();
                            mapTerminalCopy.get(key).remove(parkingSpotId);
                            if (parkingSpot != null) {
                                setReservedSpots.add(parkingSpot.getId());
                            }
                            break;
                        }
                    }
                }
            }
            if(parkingSpot == null){
                throw new IndexOutOfBoundsException();
            }
        }catch (Exception e){
            System.out.println("Exception " +e.getMessage());
            throw new IllegalStateException();
        }
        return parkingSpot;
    }

    @Override
    public void releaseParkingSpot(ParkingSportWithDistance parkingSpot) {
        setAvailableSpots.add(parkingSpot.getParkingSpot().getId());
        setReservedSpots.remove(parkingSpot.getParkingSpot().getId());
        for(String key : mapTerminalCopy.keySet()){
            if(mapTerminalCopy.containsKey(key) && !mapTerminalCopy.get(key).contains(parkingSpot.getParkingSpot().getId())){
                if(mapTerminalParkingSpot.containsKey(key) && mapTerminalParkingSpot.get(key) != null){
                    System.out.println(mapTerminalParkingSpot.get(key).size());
                    mapTerminalParkingSpot.get(key).add(parkingSpot);
                }else{
                    PriorityQueue<ParkingSportWithDistance> priorityQueue = new PriorityQueue<>(Comparator.comparing(ParkingSportWithDistance::getDist));
                    mapTerminalParkingSpot.put(key, priorityQueue);
                }
                mapTerminalCopy.get(key).add(parkingSpot.getParkingSpot().getId());
            }
        }
    }

    @Override
    public void setup(List<Terminal> listTerminal, List<ParkingSpot> listParkingSpot) {
        //Set the Available Spots and PriorityQueue;
        for(ParkingSpot parkingSpot : listParkingSpot){
            setAvailableSpots.add(parkingSpot.getId());
            Map<String, Double> mapDistFromTerminals = parkingSpot.getDistFromTerminals();
            for(String key : mapDistFromTerminals.keySet()){
                Double dist = mapDistFromTerminals.get(key);
                if(mapTerminalParkingSpot.containsKey(key)){
                    mapTerminalParkingSpot.get(key).offer(new ParkingSportWithDistance(dist, parkingSpot));
                    mapTerminalCopy.get(key).add(parkingSpot.getId());
                }else{
                    PriorityQueue<ParkingSportWithDistance> priorityQueue = new PriorityQueue<>(Comparator.comparing(ParkingSportWithDistance::getDist));
                    priorityQueue.offer(new ParkingSportWithDistance(dist, parkingSpot));
                    mapTerminalParkingSpot.put(key, priorityQueue);
                    Set<String> setParkingSpot = ConcurrentHashMap.newKeySet();
                    setParkingSpot.add(parkingSpot.getId());
                    mapTerminalCopy.put(key, setParkingSpot);
                }
            }
        }
    }
}
