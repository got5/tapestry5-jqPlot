/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/
 *
 * Copyright 2008-2010 by chenillekit.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */


package org.got5.tapestry5.jquery.jqplot.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.jqplot.data.DataJqPlotSerializer;
import org.got5.tapestry5.jquery.jqplot.services.javascript.JqPlotJavaScriptStack;
import org.got5.tapestry5.jquery.jqplot.util.StringUtil;


import java.util.List;

/**
 * chart component based on <a href="http://www.jqplot.com/index.php"> javascript library</a>.
 * Inspired form tapestry implementation that came from Chenillekit project kudos to homburg
 * 
 */
@SupportsInformalParameters
@Import(stack = JqPlotJavaScriptStack.STACK_ID, library={ "T5jqPlot.js" })
public class JqPlot implements ClientElement
{
	/**
	 * The id used to generate a page-unique client-side identifier for the component. If a component renders multiple
	 * times, a suffix will be appended to the to id to ensure uniqueness.
	 */
	@Parameter(value = "prop:componentResources.id", defaultPrefix = BindingConstants.LITERAL)
	private String clientId;

	/**
	 * the list of data item arrays.
	 */
	@Parameter(name = "dataItems", required = false, defaultPrefix = BindingConstants.PROP)
	private List<List<DataJqPlotSerializer>> dataItemsList;
	
	@Parameter(name = "graphTitle", required = false, defaultPrefix = BindingConstants.LITERAL)
	private String graphTitle;

	/**
	 * PageRenderSupport to get unique client side id.
	 */
	@Environmental
	private JavaScriptSupport javascriptSupport;

	/**
	 * For blocks, messages, create actionlink, trigger event.
	 */
	@Inject
	private ComponentResources resources;

	private String assignedClientId;

	/**
	 * Tapestry render phase method.
	 * Initialize temporary instance variables here.
	 */
	@SetupRender
	void setupRender()
	{
		assignedClientId = javascriptSupport.allocateClientId(clientId);
	}

	/**
	 * Tapestry render phase method.
	 * Start a tag here, end it in afterRender
	 */
	@BeginRender
	void beginRender(MarkupWriter writer)
	{
		writer.element("div", "id", getClientId());
		resources.renderInformalParameters(writer);
		writer.end();
	}

	/**
	 * Tapestry render phase method. End a tag here.
	 */
	@AfterRender
	void afterRender()
	{
		JSONObject spec = new JSONObject();
		JSONObject config = new JSONObject();
		JSONArray dataArray = null;
	
		//
		// Let subclasses do more.
		//
		configure(config);
		
		// Set Graph Title if it is provided
		if(StringUtil.isNonEmptyString(graphTitle)) {
			JSONObject optionObjRef = null;
			try {
				Object optionObj = config.get("options");
				optionObjRef = (JSONObject)optionObj;			
			} catch ( RuntimeException re ) {			
				// Some graphs might not have option object at all. We need to set option object and title both in that case. 
				if(re.getMessage().endsWith("not found.")) {
					optionObjRef = new JSONObject();
				}			
			}
			if(optionObjRef != null) {
				optionObjRef.put("title", graphTitle.trim());
			}
		}

		//
		// do it only if user give us some values
		//
		if (dataItemsList != null && dataItemsList.size() > 0)
		{
			dataArray = new JSONArray();

			for (List<DataJqPlotSerializer> dataItems : dataItemsList)
			{
				JSONArray data = buildDataValues(dataItems);
				dataArray.put(data);
			}
		}


		//
		// if the user doesn't give us some chart values we add an empty value array.
		//
		if (dataArray != null)
		    spec.put("data", dataArray);
		else if (config.has("data"))
		    spec.put("data", config.get("data"));
		else
		    spec.put("data", new JSONArray(new JSONArray()));

		if (config.has("options"))
		    spec.put("options", config.get("options"));

		spec.put("id", getClientId());

		javascriptSupport.addInitializerCall("jqPlotChart",spec);
	}

	/**
	 * let us build the data value string for Flotr.
	 *
	 * @param dataItems a list of data items
	 *
	 * @return a JSON array containing the data items
	 */
	private static JSONArray buildDataValues(List<DataJqPlotSerializer> dataItems)
	{
		JSONArray data = new JSONArray();

		for (DataJqPlotSerializer dataItem : dataItems)
			data.put(dataItem.toJSONArray());

		return data;
	}

	/**
	 * Invoked to allow subclasses to further configure the parameters passed to this component's javascript
	 * options. Subclasses may override this method to configure additional features of the Flotr.
	 * <p/>
	 * This implementation does nothing.
	 *
	 * @param config parameters object
	 */
	protected void configure(JSONObject config)
	{
	}

	/**
	 * Returns a unique id for the element. This value will be unique for any given rendering of a
	 * page. This value is intended for use as the id attribute of the client-side element, and will
	 * be used with any DHTML/Ajax related JavaScript.
	 */
	public String getClientId()
	{
		return assignedClientId;
	}
}
