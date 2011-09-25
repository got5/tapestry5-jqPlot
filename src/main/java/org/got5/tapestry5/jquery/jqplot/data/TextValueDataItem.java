/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/
 *
 * Copyright 2008 by chenillekit.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.got5.tapestry5.jquery.jqplot.data;

import java.io.Serializable;

import org.apache.tapestry5.json.JSONArray;

/**
 * inspired from chenillekit project author mlusetti 
 */
public class TextValueDataItem implements Serializable, DataJqPlotSerializer
{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2264289557350915525L;

	/**
     * The text
     */
    private String _text;

    /**
     * The value.
     */
    private Number _value;

    /**
     * Constructs a new data item.
     *
     * @param text the label.
     * @param value the value.
     */
    public TextValueDataItem(String text, Number value)
    {
        assert text != null;
        assert value != null;

        _text = text;
        _value = value;
    }

    /**
     * Returns the text.
     *
     * @return The texr.
     */
    public String getText()
    {
        return _text;
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
     * Sets the text for this data item.
     *
     * @param text the new text.
     */
    public void setXValue(String text)
    {
        assert text != null;
        _text = text;
    }

   

    /**
     * Returns a hash code.
     *
     * @return A hash code.
     */
    public int hashCode()
    {
        int result;
        result = (_text != null ? _text.hashCode() : 0);
        result = 31 * result + (_value != null ? _value.hashCode() : 0);
        return result;
    }

    


    public String toString()
    {
        return String.format("[%s,%s]", _text.toString(), _value.toString());
    }

	public JSONArray toJSONArray() {
		return new JSONArray(_text, _value);
	}
}
