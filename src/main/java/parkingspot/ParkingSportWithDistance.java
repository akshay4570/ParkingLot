package parkingspot;

public class ParkingSportWithDistance {

    private final Double dist;
    private final ParkingSpot parkingSpot;

    public ParkingSportWithDistance(Double dist, ParkingSpot parkingSpot) {
        this.dist = dist;
        this.parkingSpot = parkingSpot;
    }

    public Double getDist() {
        return dist;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
