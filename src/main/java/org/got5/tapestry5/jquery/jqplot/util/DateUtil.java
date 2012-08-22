package org.got5.tapestry5.jquery.jqplot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtil {
	
	public static final String TIMESTAMP_PATTERN_FOR_DATE_GRAPHS = "MM/dd/yyyy HH:mm:ss";
	
	public static final String TIMESTAMP_PATTERN_FOR_JSON_MIN_OR_MAX_DATE = "MMMMM dd, yyyy HH:mm:ss";
	
	public static String getTimestamp(Date date, String timestampPattern) {
		if(date != null && timestampPattern != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(timestampPattern);
			return formatter.format(date);
		} 
		return date != null ? date.toString() : null;
	}
		
}
