package booking.util;

public abstract class CommUtils {
	public static boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
}