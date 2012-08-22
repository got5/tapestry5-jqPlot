package org.got5.tapestry5.jquery.jqplot.components;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.json.JSONLiteral;
import org.apache.tapestry5.json.JSONObject;

@Import( library={ "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.pieRenderer.js" })
public class JqPlotPie extends JqPlot {
	
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
	      
    	JSONObject renderer= new JSONObject();
    	renderer.put("renderer", new JSONLiteral("jQuery.jqplot.PieRenderer"));  
    	renderer.put("rendererOptions",new JSONLiteral("{showDataLabels: true}"));	  
	  
    	options.put("seriesDefaults", renderer);   
    	options.put("legend", new JSONObject("{ show:true, location: 'e' }"));	  
    }

}
