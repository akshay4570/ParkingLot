package parkingspot;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class ParkingSpot {

    private String id;
    private Double size;
    private Boolean reserveSpot;
    private Map<String, Double> distFromTerminals;

    public ParkingSpot(Map<String, Double> distFromTerminals) {
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

    public Map<String, Double> getDistFromTerminals() {
        return distFromTerminals;
    }

}
