package amount;

import parkingspot.FourWheelerParking;
import parkingspot.HandicappedParking;
import parkingspot.ParkingSpot;
import parkingspot.TwoWheelerParking;
import utils.Utils;

public class WeekendAmountCalculator implements AmountCalculator{

    private static final Double weekendSurcharge = 1.5;
    @Override
    public Double calculateTotalAmount(Double totalTimeInHours, ParkingSpot parkingSpot) {
        return weekendSurcharge * totalTimeInHours * Utils.calculateChargeBasedOnParkingType(parkingSpot);
    }


}
