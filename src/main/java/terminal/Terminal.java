package terminal;

import system.ParkingLotSystem;
import system.ParkingLotSystemFactory;

public abstract class Terminal {
    private String id;
    private ParkingLotSystem parkingLotSystem;
    public String getId() {
        return id;
    }

    public ParkingLotSystem getParkingLotSystem() {
        return parkingLotSystem;
    }
}
