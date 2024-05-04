package telran.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;


public class NextFriday13 implements TemporalAdjuster{

	@Override
	public Temporal adjustInto(Temporal temporal) {
		LocalDate todayDate =  LocalDate.from(temporal);
		 do {
		        todayDate = todayDate.plusDays(1);
		    } while (!(todayDate.getDayOfMonth() == 13 && todayDate.getDayOfWeek() == DayOfWeek.FRIDAY));
		    return todayDate;
		}
}