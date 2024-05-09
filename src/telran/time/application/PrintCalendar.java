package telran.time.application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Locale;

record MonthYear(int month, int year, DayOfWeek weekDayStart) {
	
}
public class PrintCalendar {

	private static final int TITLE_OFFSET = 5;
	private static final int COLUMN_WIDTH = 4;
	private static DayOfWeek[] weekDays = DayOfWeek.values();
	public static void main(String[] args)  {
		try {
			DayOfWeek weekStartDay = args.length < 3 ? 
		DayOfWeek.MONDAY : DayOfWeek.valueOf(args[2].toUpperCase());
			MonthYear monthYear = getMonthYear(args, weekStartDay);
			printCalendar(monthYear);
		} catch (NumberFormatException e) {
			System.out.println("Ошибка: введенное значение должно быть числом.");
		} catch (IllegalArgumentException e) {
			System.out.println("Ошибка: неверное значение дня недели. Должно быть одно из следующих: " + Arrays.toString(DayOfWeek.values()));
		} catch (Exception e) {
			System.out.println("Произошла неожиданная ошибка: " + e.getMessage());
		}
	}

	private static  MonthYear getMonthYear(String[] args, DayOfWeek weekStartDay) throws Exception{
		int monthNumber = getMonth(args);
		int year = getYear(args);
		return new MonthYear(monthNumber, year, weekStartDay);
	}

	private static int getYear(String[] args) throws Exception {
		int year = args.length < 2 ? getCurrentYear() : getYear(args[1]);
		return year;
	}

	private static int getYear(String yearStr) throws Exception {
		try {
			int res = Integer.parseInt(yearStr);
			return res;
		} catch (NumberFormatException e) {
			throw new Exception("year must be an integer number");
		}
		
	}

	private static int getCurrentYear() {
		
		return LocalDate.now().getYear();
	}

	private static int getMonth(String[] args) throws Exception{
		int month = args.length == 0 ? getCurrentMonth() : getMonthNumber(args[0]);
		return month;
	}

	private static int getMonthNumber(String monthStr)throws Exception {
		try {
			int result = Integer.parseInt(monthStr);
			if (result < 1) {
				throw new Exception("Month cannot be less than 1");
			}
			if(result > 12) {
				throw new Exception("Month cannot be greater than 12");
			}
			return result;
		} catch (NumberFormatException e) {
			throw new Exception("Month must be a number");
		}
	}

	private static int getCurrentMonth() {
		
		return LocalDate.now().get(ChronoField.MONTH_OF_YEAR);
	}

	private static void printCalendar(MonthYear monthYear) {
		DayOfWeek[]weekDays = getWeekDayStartingFrom(monthYear.weekDayStart());
		printTitle(monthYear);
		printWeekDays(weekDays);
		printDays(monthYear,weekDays);		
	}

	private static DayOfWeek[] getWeekDayStartingFrom(DayOfWeek weekDayStart) {
      DayOfWeek[] weekDays = DayOfWeek.values();
      DayOfWeek[] reorderedWeekDays = new DayOfWeek[7];
      int startDayIndex = weekDayStart.getValue() -1;
      for(int i = 0; i < 7; i++) {
    	  reorderedWeekDays[i] = weekDays[(startDayIndex + i) % 7];
      }
     return reorderedWeekDays;
	}

	private static void printDays(MonthYear monthYear,DayOfWeek[] weekDays) {
		int nDays = getDaysInMonth(monthYear);
		int currentWeekDay = getFirstDayOfMonth(monthYear);
		int firstOffset = getFirstOffset(currentWeekDay);
		System.out.printf("%s", " ".repeat(firstOffset));
		for(int day = 1; day <= nDays; day++) {
			System.out.printf("%" + COLUMN_WIDTH +"d", day);
			if(currentWeekDay == weekDays.length) {
				currentWeekDay = 0;
				System.out.println();
			}
			currentWeekDay++;
		}
		
	}

	private static int getFirstOffset(int currentWeekDay) {
		
		return COLUMN_WIDTH * (currentWeekDay - 1);
	}

	private static int getFirstDayOfMonth(MonthYear monthYear) {
		LocalDate ld = LocalDate.of(monthYear.year(), monthYear.month(),
				1);
		return ld.get(ChronoField.DAY_OF_WEEK);
	}

	private static int getDaysInMonth(MonthYear monthYear) {
		YearMonth ym = YearMonth.of(monthYear.year(), monthYear.month());
		return ym.lengthOfMonth();
	}

	private static void printWeekDays(DayOfWeek[] weekDays) {
		System.out.printf("%s", " ".repeat(1));
		for(DayOfWeek weekday: weekDays) {
			System.out.printf("%" + COLUMN_WIDTH +"s",weekday.getDisplayName(TextStyle.SHORT,
					Locale.forLanguageTag("en")));
			
		}
		System.out.println();
		
	}

	private static void printTitle(MonthYear monthYear) {
		String monthName = Month.of(monthYear.month())
				.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("en"));
		System.out.printf("%s%s %d\n"," ".repeat(TITLE_OFFSET), monthName, monthYear.year());
		
		
	}

}