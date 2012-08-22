package org.got5.tapestry5.jquery.jqplot.components;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.json.JSONLiteral;
import org.apache.tapestry5.json.JSONObject;

/**
 * This graph plots X and Y with zooming feature explained on this example page http://www.jqplot.com/tests/zooming.php
 */
@Import( library={ "${jquery.jqplot.core.path}/jquery.jqplot.${jquery.jqplot.version}/plugins/jqplot.cursor.min.js" })
public class JqPlotZooming extends JqPlot {
		
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
    	
    	JSONObject cursor = new JSONObject();
    	cursor.put("show", new JSONLiteral("true")); 
    	cursor.put("zoom", new JSONLiteral("true")); 
    	cursor.put("showTooltip", new JSONLiteral("true"));   
    	
    	options.put("cursor", cursor);    	
    	
    }
	
}
