package org.got5.tapestry5.jquery.jqplot.data;

import java.io.Serializable;
import java.util.Date;

import org.apache.tapestry5.json.JSONArray;
import org.got5.tapestry5.jquery.jqplot.util.DateUtil;

/**
 * inspired from chenillekit project author mlusetti 
 */
public class DateValueDataItem implements Serializable, DataJqPlotSerializer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3112059885235833332L;

	/**
     * The date
     */
    private Date _date;

    /**
     * The value.
     */
    private Number _value;

    /**
     * Constructs a new data item.
     *
     * @param date the label.
     * @param value the value.
     */
    public DateValueDataItem(Date date, Number value)
    {
        assert date != null;
        assert value != null;

        _date = date;
        _value = value;
    }

    /**
     * Returns the date.
     *
     * @return The date.
     */
    public Date getDate()
    {
        return _date;
    }

    /**
     * Returns the value.
     *
     * @return The value.
     */
    public Number getValue()
    {
        return _value;
    }

    /**
     * Sets the value for this data item.
     *
     * @param Value the new value.
     */
    public void setValue(Number Value)
    {
        assert Value != null;
        _value = Value;
    }

    /**
     * Sets the date for this data item.
     *
     * @param date the new date.
     */
    public void setXValue(Date date)
    {
        assert date != null;
        _date = date;
    }

   

    /**
     * Returns a hash code.
     *
     * @return A hash code.
     */
    public int hashCode()
    {
        int result;
        result = (_date != null ? DateUtil.getTimestamp(_date, DateUtil.TIMESTAMP_PATTERN_FOR_DATE_GRAPHS).hashCode() : 0);
        result = 31 * result + (_value != null ? _value.hashCode() : 0);
        return result;
    }

    public String toString()
    {
        return String.format("[%s,%s]", DateUtil.getTimestamp(_date, DateUtil.TIMESTAMP_PATTERN_FOR_DATE_GRAPHS), _value.toString());
    }

	public JSONArray toJSONArray() {
		return new JSONArray(DateUtil.getTimestamp(_date, DateUtil.TIMESTAMP_PATTERN_FOR_DATE_GRAPHS), _value);
	}

}
