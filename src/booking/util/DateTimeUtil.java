package booking.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	public static String FormatWithTime(Timestamp timestamp) {
		if (timestamp == null)
			return "";
		return Format(timestamp, "yyyy-MM-dd HH:mm:ss");
	}

	public static String FormatNoTime(Timestamp timestamp) {
		if (timestamp == null)
			return "";
		return Format(timestamp, "yyyy-MM-dd");
	}

	public static String Format(Timestamp timestamp, String formatString) {
		if (timestamp == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		return formatter.format(timestamp);
	}

	public static String FormatCurrentDateTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return Format(timestamp, "yyyy-MM-dd HH:mm:ss");
	}

	public static String FormatCurrentDate() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return Format(timestamp, "yyyy-MM-dd");
	}

	public static Timestamp CurrentDateTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Date toDate(String strValue) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(strValue);
		return date;
	}

	public static Date toDateTime(String strValue) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = formatter.parse(strValue);
		return date;
	}

	// get beginning of month
	public static String getMonthStart(String dateString, String year, String month) {
		String[] dateStr = dateString.split("-");
		month = month != null && !"".equals(month.trim()) ? month : dateStr[1];
		year = year != null && !"".equals(year.trim()) ? year : dateStr[0];
		return year + "-" + month + "-01";
	}

	// get end of month
	public static String getMonthEnd(String dateString, String year, String month) {
		String[] dateStr = dateString.split("-");
		month = month != null && !"".equals(month.trim()) ? month : dateStr[1];
		year = year != null && !"".equals(year.trim()) ? year : dateStr[0];
		if ("1".equals(month) || "3".equals(month) || "5".equals(month) || "7".equals(month) || "8".equals(month)
				|| "10".equals(month) || "12".equals(month)) {
			return dateStr[0] + "-" + month + "-31";
		} else {
			if ("2".equals(month)) {
				int year1 = Integer.parseInt(year);
				if (year1 % 4 == 0 && year1 % 100 != 0 || year1 % 400 == 0) {
					return dateStr[0] + "-" + month + "-29";
				} else {
					return dateStr[0] + "-" + month + "-28";
				}
			} else {
				return dateStr[0] + "-" + month + "-30";
			}
		}
	}
}