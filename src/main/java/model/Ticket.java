package model;

import parkingspot.ParkingSpot;
import terminal.Terminal;

import java.sql.Timestamp;
import java.util.UUID;

public class Ticket {
    private String id;
    private ParkingSpot parkingSpot;
    private long issueTime;
    private Terminal terminal;

    public Ticket(ParkingSpot parkingSpot, Terminal terminal) {
        this.id = UUID.randomUUID().toString();
        this.parkingSpot = parkingSpot;
        this.issueTime = System.currentTimeMillis();
        this.terminal = terminal;
    }

    public String getId() {
        return id;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public long getIssueTime() {
        return issueTime;
    }

    public Terminal getTerminal() {
        return terminal;
    }
}
