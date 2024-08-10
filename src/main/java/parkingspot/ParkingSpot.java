package parkingspot;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class ParkingSpot {

    private String id;
    private Double size;
    private Boolean reserveSpot;
    private List<Double> distFromTerminals;

    public ParkingSpot(List<Double> distFromTerminals) {
        this.id = UUID.randomUUID().toString();
        this.size = 100.0;
        this.reserveSpot = false;
        this.distFromTerminals = distFromTerminals;
    }

    public String getId() {
        return id;
    }

    public Double getSize() {
        return size;
    }

    public Boolean getReserveSpot() {
        return reserveSpot;
    }

    public List<Double> getDistFromTerminals() {
        return distFromTerminals;
    }
}
