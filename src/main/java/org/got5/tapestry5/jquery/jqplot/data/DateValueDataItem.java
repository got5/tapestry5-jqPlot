package org.got5.tapestry5.jquery.jqplot.data;

import java.io.Serializable;
import java.util.Date;

import org.apache.tapestry5.json.JSONArray;
import org.got5.tapestry5.jquery.jqplot.util.DateUtil;

/**
 * inspired from chenillekit project author mlusetti 
 */
public class DateValueDataItem implements Serializable, Comparable, DataJqPlotSerializer {

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
     * Tests if this object is equal to another.
     *
     * @param obj the object to test against for equality (<code>null</code>
     *            permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof DateValueDataItem)) return false;

        DateValueDataItem that = (DateValueDataItem) obj;

        if (_date != null ? !_date.equals(that._date) : that._date != null) return false;
        if (_value != null ? !_value.equals(that._value) : that._value != null) return false;

        return true;
    }
    
    /**
     * Returns an integer indicating the order of this object relative to
     * another object.
     * <p/>
     * For the order we consider only the x-value:
     * negative == "less-than", zero == "equal", positive == "greater-than".
     *
     * @param o1 the object being compared to.
     *
     * @return An integer indicating the order of this data pair object
     *         relative to another object.
     */
    public int compareTo(Object o1)
    {

        int result;

        // CASE 1 : Comparing to another TimeSeriesDataPair object
        // -------------------------------------------------------
        if (o1 instanceof DateValueDataItem)
        {
        	DateValueDataItem dataItem = (DateValueDataItem) o1;
            long compare = _date.getTime() - dataItem.getDate().getTime();
            if (compare > 0)
                result = 1;
            else
            {
                if (compare < 0)
                    result = -1;
                else
                    result = 0;
            }
        }
        // CASE 2 : Comparing to a general object
        // ---------------------------------------------
        // consider time periods to be ordered after general objects
        else
            result = 1;

        return result;
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
