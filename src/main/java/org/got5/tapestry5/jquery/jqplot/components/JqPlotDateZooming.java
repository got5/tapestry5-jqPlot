package org.got5.tapestry5.jquery.jqplot.components;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONLiteral;
import org.apache.tapestry5.json.JSONObject;
import org.got5.tapestry5.jquery.jqplot.util.DateUtil;

/**
 * This graph plots X (Date) and Y with zooming feature explained on this example page http://www.jqplot.com/tests/zooming.php
 */
@Import( library={ "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.cursor.min.js", 
		           "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.dateAxisRenderer.min.js" })
public class JqPlotDateZooming extends JqPlot {	
	
	@Parameter(name = "xAxisMinDate", required=false)
	private Date xAxisMinDate;
	
	@Parameter(name = "xAxisMaxDate", required=false)
	private Date xAxisMaxDate;
	
	@Parameter(name = "xAxisTickerInterval", required=false)
	private String xAxisTickerInterval;
	
	@Parameter(name = "seriesLabels", required = false, defaultPrefix = BindingConstants.PROP)
	private List<String> seriesLabels;
	
	/**
     * Invoked to allow subclasses to further configure the parameters passed to this component's javascript
     * options. Subclasses may override this method to configure additional features of the jqPlot library.
     *
     * @param config parameters object
     */
    protected void configure(JSONObject config)
    {
    	JSONObject options = new JSONObject();    
    	config.put("options", options);
    	
    	// data items might have series labels 
		// user is responsible for setting label for each series. Do not make mistake. 
    	// Size of dataSeries and Size of series lable has to be equal.
		if(seriesLabels != null && seriesLabels.size()>0) {
			JSONArray series = new JSONArray();
			for(String currentLabel: seriesLabels) {
				JSONObject labelEntry = new JSONObject();
				labelEntry.put("label", new JSONLiteral("'" + currentLabel + "'"));
				series.put(labelEntry);
			}
			options.put("series", series);
			options.put("legend", new JSONObject("{ show:true, location: 'nw' }"));
		}
    	
    	JSONObject axes = new JSONObject();
    	options.put("axes", axes);
    	
    	JSONObject xaxis = new JSONObject();
    	axes.put("xaxis", xaxis);
    	
    	xaxis.put("renderer", new JSONLiteral("jQuery.jqplot.DateAxisRenderer"));
    	
    	if(xAxisMinDate != null) {
    		xaxis.put("min", new JSONLiteral("'" + DateUtil.getTimestamp(xAxisMinDate, DateUtil.TIMESTAMP_PATTERN_FOR_JSON_MIN_OR_MAX_DATE) + "'" ));
    	}
    	if(xAxisMaxDate != null) {
    		xaxis.put("max", new JSONLiteral("'" + DateUtil.getTimestamp(xAxisMaxDate, DateUtil.TIMESTAMP_PATTERN_FOR_JSON_MIN_OR_MAX_DATE) + "'"));
    	}
    	if(xAxisTickerInterval != null && isXAxisTickerIntervalValid(xAxisTickerInterval) ) {
    		xaxis.put("tickInterval", new JSONLiteral("'" + xAxisTickerInterval + "'"));
    	}
    	
    	JSONObject tickOptions = new JSONObject();
    	tickOptions.put("formatString", new JSONLiteral("'%H:%M:%S'"));
    	xaxis.put("tickOptions", tickOptions);
    	
    	JSONObject cursor = new JSONObject();
    	cursor.put("show", new JSONLiteral("true")); 
    	cursor.put("zoom", new JSONLiteral("true")); 
    	cursor.put("showTooltip", new JSONLiteral("true")); 
    	cursor.put("dblClickReset", new JSONLiteral("true"));
    	options.put("cursor", cursor);    	
    	
    }
    
    private boolean isXAxisTickerIntervalValid(String xAxisTickerInterval) {
    	// TODO: implement validation logic
    	return true;
    }

}
