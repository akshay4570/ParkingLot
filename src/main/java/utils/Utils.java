package utils;

import parkingspot.FourWheelerParking;
import parkingspot.HandicappedParking;
import parkingspot.ParkingSpot;
import parkingspot.TwoWheelerParking;

public class Utils {
    public static Double calculateChargeBasedOnParkingType(ParkingSpot parkingSpot) {
        if(parkingSpot instanceof HandicappedParking){
            return 10.0;
        }else if(parkingSpot instanceof TwoWheelerParking){
            return 20.0;
        }else if(parkingSpot instanceof FourWheelerParking){
            return 40.0;
        }else{
            return 50.0;
        }
    }

    public static Double getTotalTimeInHours(long issueTime) {
        return (System.currentTimeMillis() - issueTime)/3600000.0;
    }
}
