package org.got5.tapestry5.jquery.jqplot.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONLiteral;
import org.apache.tapestry5.json.JSONObject;
import org.got5.tapestry5.jquery.utils.JQueryUtils;

@Import( library={ "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.barRenderer.min.js" })
public class JqPlotBar extends JqPlot {
	
	
	/**
	 *  JSON parameters you want to override.
	 */
	@Parameter
    private JSONObject params;
	
	@Inject
	private ComponentResources resources;

	/**
     * Invoked to allow subclasses to further configure the parameters passed to this component's javascript
     * options. Subclasses may override this method to configure additional features of the jqPlot library.
     *
     * @param config parameters object
     */
    protected void configure(JSONObject config)
    {    	
    	//let's do default configuration
    	JSONObject options = new JSONObject();    
    	config.put("options", options); 
	      
    	JSONObject renderer= new JSONObject();
    	renderer.put("renderer", new JSONLiteral("jQuery.jqplot.BarRenderer"));  
    	renderer.put("rendererOptions",new JSONLiteral("{fillToZero: true}"));	  
    	// The "seriesDefaults" option is an options object that will
        // be applied to all series in the chart.
    	options.put("seriesDefaults", renderer);   
    	// Show the legend and put it outside the grid, but inside the
        // plot container, shrinking the grid to accomodate the legend.
        // A value of "outside" would not shrink the grid and allow
        // the legend to overflow the container.
    	options.put("legend", new JSONObject("{ show:true,  placement: 'outsideGrid', location: 's' }"));	  
    	
    	//do we have to merge default params with some other component   
    	if (resources.isBound("params") && resources.getBoundType("params").equals(JSONObject.class))
    	{
    		 JQueryUtils.merge(config, params);
    	}
    }

}
