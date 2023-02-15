package telran.time;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class WorkingDays implements TemporalAdjuster {
	private int workingDays;
	private DayOfWeek[] dayOffs;
	
	public WorkingDays(int workingDays, DayOfWeek[] dayOffs) {
		this.workingDays = workingDays;
		this.dayOffs = dayOffs;
	}
	@Override
	public Temporal adjustInto(Temporal temporal) {
		// TODO Auto-generated method stub
		return null;
	}
	public WorkingDays(DayOfWeek[] dayOffs) {
		//TODO
	}
	
}