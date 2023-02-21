package telran.time.application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

public class PrintCalendar {

	private static final String LANGUAGE_TAG = "en";
	private static final int YEAR_OFFSET = 10;
	private static final int WIDTH_FIELD = 4;
	private static Locale locale = Locale.forLanguageTag(LANGUAGE_TAG);

	public static void main(String[] args) {
		try {
			int monthYear[] = getMonthYear(args);
			printCalendar(monthYear[0], monthYear[1], monthYear[2]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void printCalendar(int month, int year, int firstDayOfWeek) {
		printTitle(month, year);
		printWeekDays(firstDayOfWeek);
		printDates(month, year, firstDayOfWeek);
	}


	private static void printDates(int month, int year, int firstDayOfWeek) {
		int weekDayNumber = getFirstDay(month, year, firstDayOfWeek);
		int offset = getOffset(weekDayNumber);
		int nDays = YearMonth.of(year, month).lengthOfMonth();
		System.out.printf("%s", " ".repeat(offset));
		for (int date = 1; date <= nDays; date++) {
			System.out.printf("%4d", date);
			if (++weekDayNumber > 7) {
				System.out.println();
				weekDayNumber = 1;
			}
		}
	}

	private static int getFirstDay(int month, int year, int firstDayOfWeek) {
		return LocalDate.of(year, month, 1).getDayOfWeek().minus(firstDayOfWeek).getValue();
	}

	private static int getOffset(int weekDayNumber) {
		return (weekDayNumber - 1) * WIDTH_FIELD;
	}

	private static void printWeekDays(int firstDayOfWeek) {
		System.out.print("  ");
		Arrays.stream(DayOfWeek.values()).map(d -> d.plus(firstDayOfWeek))
				.forEach(dw -> System.out.printf("%s ", dw.getDisplayName(TextStyle.SHORT, locale)));
		System.out.println();
	}

	private static void printTitle(int month, int year) {
		System.out.printf("%s%d, %s\n", " ".repeat(YEAR_OFFSET), year,
				Month.of(month).getDisplayName(TextStyle.FULL, locale));
	}

	private static int[] getMonthYear(String[] args) throws Exception {
		return args.length == 0 ? getCurrentMonthYear() : getMonthYearArgs(args);
	}

	private static int[] getCurrentMonthYear() {
		LocalDate current = LocalDate.now();
		return new int[] { current.getMonth().getValue(), current.getYear(), DayOfWeek.of(1).getValue() };
	}
	
	private static int[] getMonthYearArgs(String[] args) throws Exception {
		return new int[] { getMonthArgs(args), getYearArgs(args), getDayArgs(args) };
	}

	private static int getDayArgs(String[] args) throws Exception {
		int res = DayOfWeek.valueOf("MONDAY").getValue();
		if (args.length > 2) {
			try {
				res = DayOfWeek.valueOf(args[2].toUpperCase()).getValue()-1;
			} catch (IllegalArgumentException e) {
				throw new Exception(
						"There is no such day. Try from [Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday].");
			}
		}
		return res;
	}

	private static int getYearArgs(String[] args) throws Exception {
		int res = LocalDate.now().getYear();
		if (args.length > 1) {
			try {
				res = Integer.parseInt(args[1]);
				if (res < 0) {
					throw new Exception("year must be a positive number");
				}
			} catch (NumberFormatException e) {
				throw new Exception("year must be a number");
			}
		}
		return res;
	}

	private static int getMonthArgs(String[] args) throws Exception {
		try {
			int res = Integer.parseInt(args[0]);
			if (res < 1 || res > 12) {
				throw new Exception("Month should be a number in the range [1-12]");
			}
			return res;
		} catch (NumberFormatException e) {
			throw new Exception("Month should be a number");
		}
	}

}