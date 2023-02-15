package telran.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		Temporal res = temporal;
		while(!DayOfWeek.from(res).equals(DayOfWeek.FRIDAY)) {
			res = res.plus(1, ChronoUnit.DAYS);
		}
		while (LocalDate.from(res).getDayOfMonth() != 13) {
			res = res.plus(1, ChronoUnit.WEEKS);
		}
		return res;
	}
}