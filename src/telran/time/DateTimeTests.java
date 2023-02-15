
package telran.time;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateTimeTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void localDateTest() {
		LocalDate birthDateAS = LocalDate.parse("1799-06-06");
		LocalDate barMizvaAS = birthDateAS.plusYears(13);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM,YYYY,d");
		System.out.println(barMizvaAS.format(dtf));
		ChronoUnit unit = ChronoUnit.MONTHS;
		System.out.printf("Number of %s between %s and %s is %d ", unit,
				birthDateAS, barMizvaAS, unit.between(birthDateAS, barMizvaAS));
		System.out.println("#################################################");
		
	}
	@Test
	void barMizvaTest() {
		LocalDate current = LocalDate.now();
		assertEquals(current.plusYears(13), current.with(new BarMizvaAdjuster()));
	}
	@Test
	void displayCurrentDateTimeCanadaTimeZones () {
		//displaying current local date and time for all Canada time zones
		//displaying should contains time zone name
//		LocalDateTime current = LocalDateTime.now();
		ZoneId.getAvailableZoneIds().stream()
		.filter(n -> n.contains("Canada"))
		.forEach(n -> System.out.println(ZonedDateTime.now(ZoneId.of(n))));
	}
	
	@Test
	void nextFriday13Test() {
		TemporalAdjuster nextFri13 = new NextFriday13();
		LocalDate next13 = LocalDate.parse("2023-10-13");
		LocalDate date = LocalDate.parse("2023-03-03");
		assertEquals(next13, date.with(nextFri13));
	}

}