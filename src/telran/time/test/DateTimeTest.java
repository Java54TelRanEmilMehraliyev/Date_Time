package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.time.chrono.ChronoPeriod;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Iterator;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import telran.time.BarMitzvaAdjuster;
import telran.time.Friday13Range;
import telran.time.NextFriday13;
import telran.time.TimeDateZoneDisplay;
class DateTimeTest {

	@Test
	void barMizvaAdjusterTest() {
		LocalDate birthDate = LocalDate.parse("1799-06-06");
		LocalDate expected = LocalDate.of(1812, 6, 6);
		assertEquals(expected, birthDate.with(new BarMitzvaAdjuster()));
	}
	@Test
	void nextFriday13Test() {
		NextFriday13 nextFriday13 = new NextFriday13();
		Temporal date = LocalDate.of(2024, 5, 8);
		Temporal result = nextFriday13.adjustInto(date);
	    assertEquals(LocalDate.of(2024, 9, 13),result);
	}
	@Test
	void friday13RangeTest() {
		Temporal from = LocalDate.of(2024, 05, 04);
		Temporal to = LocalDate.of(2024, 12, 31);
		Friday13Range range = new Friday13Range(from,to);
		Iterator<Temporal> iterator = range.iterator();
		assertTrue(iterator.hasNext());
	}
	@Test
	void displayCurrentDateTimeTest() {
		
		TimeDateZoneDisplay.displayCurrentDateTime("Canada");
	}
}
