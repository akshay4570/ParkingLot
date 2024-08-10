package amount;

import parkingspot.ParkingSpot;

public interface AmountCalculator {

    public Double calculateTotalAmount(Double totalTimeInHours, ParkingSpot parkingSpot);

}
