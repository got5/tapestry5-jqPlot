package org.got5.tapestry5.jquery.jqplot.util;

public abstract class StringUtil {
	
	public static boolean isNonEmptyString(String value) {
		return value != null && !"".equals(value);
	}

}
