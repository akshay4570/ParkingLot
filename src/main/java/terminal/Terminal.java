package terminal;

import system.ParkingLotSystem;
import system.ParkingLotSystemFactory;

public abstract class Terminal {
    private final String id;
    private final ParkingLotSystem parkingLotSystem;

    public Terminal(String id) {
        this.id = id;
        parkingLotSystem = ParkingLotSystem.getInstance();
    }

    public String getId() {
        return id;
    }

    public ParkingLotSystem getParkingLotSystem() {
        return parkingLotSystem;
    }
}
