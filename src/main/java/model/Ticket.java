package model;

import parkingspot.ParkingSpot;

import java.sql.Timestamp;
import java.util.UUID;

public class Ticket {
    private String id;
    private ParkingSpot parkingSpot;
    private long issueTime;

    public Ticket(ParkingSpot parkingSpot) {
        this.id = UUID.randomUUID().toString();
        this.parkingSpot = parkingSpot;
        this.issueTime = System.currentTimeMillis();
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
}
