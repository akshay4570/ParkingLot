package amount;

import parkingspot.ParkingSpot;
import utils.Utils;

public class WeekDayAmountCalculator implements AmountCalculator{
    @Override
    public Double calculateTotalAmount(Double totalTimeInHours, ParkingSpot parkingSpot) {
        return totalTimeInHours * Utils.calculateChargeBasedOnParkingType(parkingSpot);
    }
}
