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
import org.got5.tapestry5.jquery.jqplot.util.StringUtil;


/**
 * This graph plots X (Date) and Y with zooming feature explained on this example page http://www.jqplot.com/tests/zooming.php
 */
@Import( library={ "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.cursor.min.js", 
				   "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.logAxisRenderer.min.js",
				   "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.logAxisRenderer.min.js",
				   "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.canvasTextRenderer.min.js",
				   "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.canvasAxisLabelRenderer.min.js",
				   "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.canvasAxisTickRenderer.min.js",
		           "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.dateAxisRenderer.min.js"})
public class JqPlotDateZooming extends JqPlot {	
	
	@Parameter(name = "xAxisMinDate", required=false)
	private Date xAxisMinDate;
	
	@Parameter(name = "xAxisMaxDate", required=false)
	private Date xAxisMaxDate;
	
	@Parameter(name = "xAxisTickerInterval", required=false)
	private String xAxisTickerInterval;
	
	@Parameter(name = "xAxisLabel", required=false)
	private String xAxisLabel;
	
	@Parameter(name = "yAxisLabel", required=false)
	private String yAxisLabel;
	
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
			options.put("legend", new JSONObject("{ show:true, rendererOptions: { numberRows: 1 }, placement: 'outsideGrid', location: 's' }"));
		}
    	
    	JSONObject axes = new JSONObject();
    	options.put("axes", axes);
    	
    	JSONObject xaxis = new JSONObject();
    	axes.put("xaxis", xaxis);
    	
    	if(StringUtil.isNonEmptyString(xAxisLabel)) {
    		xaxis.put("label", new JSONLiteral("'"+xAxisLabel.trim()+"'"));
    	}
    	
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
    	
    	JSONObject xTickOptions = new JSONObject();
    	xaxis.put("labelRenderer", new JSONLiteral("jQuery.jqplot.CanvasAxisLabelRenderer"));
    	xTickOptions.put("formatString", new JSONLiteral("'%H:%M:%S'"));
    	xTickOptions.put("labelPosition", new JSONLiteral("'middle'"));
    	xTickOptions.put("angle", new JSONLiteral("-90"));
    	xaxis.put("tickOptions", xTickOptions);
    	xaxis.put("tickRenderer", new JSONLiteral("jQuery.jqplot.CanvasAxisTickRenderer"));
    	
    	JSONObject yaxis = new JSONObject();
    	axes.put("yaxis", yaxis);
    	
    	if(StringUtil.isNonEmptyString(yAxisLabel)) {
    		yaxis.put("label", new JSONLiteral("'"+yAxisLabel.trim()+"'"));
    	}
    	
    	JSONObject yTickOptions = new JSONObject();
    	yaxis.put("labelRenderer", new JSONLiteral("jQuery.jqplot.CanvasAxisLabelRenderer"));
    	yTickOptions.put("formatString", new JSONLiteral("'%.2f'"));
    	yTickOptions.put("labelPosition", new JSONLiteral("'middle'"));
    	yaxis.put("tickOptions", yTickOptions);
    	yaxis.put("tickRenderer", new JSONLiteral("jQuery.jqplot.CanvasAxisTickRenderer"));
    	
    	
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
