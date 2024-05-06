package telran.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;


public class NextFriday13 implements TemporalAdjuster{

	@Override
	public Temporal adjustInto(Temporal temporal) {
		Temporal temp = temporal;
		 do {
		        temp = temp.plus(1,ChronoUnit.DAYS);
		    } while (!(temp.get(ChronoField.DAY_OF_MONTH) == 13 && temp.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.FRIDAY.getValue()));
		    return temp;
		}
}