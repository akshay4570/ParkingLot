package amount;

import java.util.Calendar;

public class AmountCalculatorFactory {

    public static AmountCalculator getAmountCalculator(){
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek > 1 && dayOfWeek < 7){
            return new WeekDayAmountCalculator();
        }else {
            return new WeekendAmountCalculator();
        }
    }
}
