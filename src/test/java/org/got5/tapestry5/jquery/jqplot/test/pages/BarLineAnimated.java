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

package org.got5.tapestry5.jquery.jqplot.test.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONLiteral;
import org.apache.tapestry5.json.JSONObject;

import org.got5.tapestry5.jquery.jqplot.components.*;
import org.got5.tapestry5.jquery.jqplot.data.DateValueDataItem;
import org.got5.tapestry5.jquery.jqplot.data.TextValueDataItem;
import org.got5.tapestry5.jquery.jqplot.data.XYDataItem;

/*
 * see http://www.jqplot.com/deploy/dist/examples/barLineAnimated.html for more details
 */

@Import( library={ "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.highlighter.min.js",
		           "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.cursor.min.js",
		           "${jquery.jqplot.core.path}/jquery/jqplot/${jquery.jqplot.version}/plugins/jqplot.pointLabels.min.js"})

public class BarLineAnimated
{
	
    private List<List<XYDataItem>> testData;

    @Component(parameters = {"dataItems=testData", "graphTitle=literal:JqPlotBar" , "params=OverridedParams"})
    private JqPlotBar chart1;

       
      
    public JSONObject getOverridedParams() {
    	JSONObject ret =  new JSONObject(); 
    	
    	JSONObject options = new JSONObject();
    	/*
    		        // Turns on animatino for all series in this plot.
    		        animate: true,
    		        // Will animate plot on calls to plot1.replot({resetAxes:true})
    		        animateReplot: true,
    		        cursor: {
    		            show: true,
    		            zoom: true,
    		            looseZoom: true,
    		            showTooltip: false
    		        },
    		*/
    	options.put("animate", new JSONLiteral("true"));
    	// Will animate plot on calls to plot1.replot({resetAxes:true})
    	options.put("animateReplot", new JSONLiteral("true"));
    	options.put("cursor",new JSONLiteral("{show: true,zoom: true,looseZoom: true,showTooltip: false}")); 
    	
    	/*
    	   series:[
    		            {
    		                pointLabels: {
    		                    show: true
    		                },
    		                renderer: $.jqplot.BarRenderer,
    		                showHighlight: false,
    		                yaxis: 'y2axis',
    		                rendererOptions: {
    		                    // Speed up the animation a little bit.
    		                    // This is a number of milliseconds.  
    		                    // Default for bar series is 3000.  
    		                    animation: {
    		                        speed: 2500
    		                    },
    		                    barWidth: 15,
    		                    barPadding: -15,
    		                    barMargin: 0,
    		                    highlightMouseOver: false
    		                }
    		            }, 
    		            {
    		                rendererOptions: {
    		                    // speed up the animation a little bit.
    		                    // This is a number of milliseconds.
    		                    // Default for a line series is 2500.
    		                    animation: {
    		                        speed: 2000
    		                    }
    		                }
    		            }
    		        ]
    	 */
    	JSONArray series = new JSONArray();
    	//serie 1 :bar
    	series.put(new JSONLiteral("{pointLabels: {show: true}, renderer: jQuery.jqplot.BarRenderer, showHighlight: false, yaxis: 'y2axis'," +
    							   " rendererOptions: {animation: {speed: 2500},barWidth: 15,  barPadding: -15, barMargin: 0,  highlightMouseOver: false}}"));
    	//serie 2 :line
    	series.put(new JSONLiteral("{rendererOptions: {animation: { speed: 2000}}}"));
    	options.put("series", series);
    	/*
    	axesDefaults: {
    		            pad: 0
    		        },
    	*/	
    	options.put("axesDefaults",new JSONLiteral("{pad: 0}")); 
    	/*	
    	        axes: {
    		            // These options will set up the x axis like a category axis.
    		            xaxis: {tickInterval: 1,drawMajorGridlines: false, drawMinorGridlines: true,drawMajorTickMarks: false,
    		                rendererOptions: {
    		                tickInset: 0.5,
    		                minorTicks: 1
    		            }
    		            },
    		
    		            yaxis: { tickOptions: {formatString: "$%'d"},rendererOptions: {forceTickAt0: true}},
    		            y2axis: {tickOptions: {formatString: "$%'d"},rendererOptions: {alignTicks: true,forceTickAt0: true}}
    		        },
    	*/
    	JSONObject axes = new JSONObject();
    	axes.put("xaxis",new JSONLiteral("{tickInterval: 1,drawMajorGridlines: false,drawMinorGridlines: true, drawMajorTickMarks: false, rendererOptions: {tickInset: 0.5,  minorTicks: 1}}"));
    	axes.put("yaxis",new JSONLiteral("{ tickOptions: {formatString: '$ %d'},rendererOptions: {forceTickAt0: true}}"));
    	axes.put("y2axis",new JSONLiteral( "{tickOptions: {formatString: '$ %d'},rendererOptions: {alignTicks: true,forceTickAt0: true}}"));
    	
    	options.put("axes", axes);
    	options.put("cursor",new JSONLiteral("{show: true,zoom: true,looseZoom: true,showTooltip: false}")); 
        /*
		        highlighter: {
    		            show: true, 
    		            showLabel: true, 
    		            tooltipAxes: 'y',
    		            sizeAdjust: 7.5 , tooltipLocation : 'ne'
    		        }
         */
    	options.put("highlighter", new JSONObject("{ show: true, showLabel: true, tooltipAxes: 'y',  sizeAdjust: 7.5 , tooltipLocation : 'ne' }"));	  
    	
    		
    	ret.put("options", options); 
    	return ret;
   
    }
    
   
    @Cached
    public List getTestData()
    {
        List<List<XYDataItem>> dataList = CollectionFactory.newList();
        List<XYDataItem> serie1 = CollectionFactory.newList();
        List<XYDataItem> serie2 = CollectionFactory.newList();

     
        serie1.add(new XYDataItem(2002, 112000));
        serie1.add(new XYDataItem(2003, 122000));
        serie1.add(new XYDataItem(2004, 104000));
        serie1.add(new XYDataItem(2005, 99000));
        serie1.add(new XYDataItem(2006, 121000));
        serie1.add(new XYDataItem(2007, 148000));
        serie1.add(new XYDataItem(2008, 114000));
        serie1.add(new XYDataItem(2009, 133000));
        serie1.add(new XYDataItem(2010, 161000));
        serie1.add(new XYDataItem(2011, 173000));

        
      
        serie2.add(new XYDataItem(2002, 10200));
        serie2.add(new XYDataItem(2003, 10800));
        serie2.add(new XYDataItem(2004, 11200));
        serie2.add(new XYDataItem(2005, 11800));
        serie2.add(new XYDataItem(2006, 12400));
        serie2.add(new XYDataItem(2007, 12800));
        serie2.add(new XYDataItem(2008, 13200));
        serie2.add(new XYDataItem(2009, 12600));
        serie2.add(new XYDataItem(2010, 13100));

        dataList.add(serie2);
        dataList.add(serie1);

        return dataList;
    }
    
   
}