package telran.time;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class TimeDateZoneDisplay {
	public static void displayCurrentDateTime(String zonePart) {
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
		for (String zoneId : allZoneIds) {
			if (zoneId.contains(zonePart)) {
				ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(zoneId));
				String formattedDateTime = zdt.format(formatter);
				System.out.println(formattedDateTime + "" + zoneId);
			}
		}
	}

}
