package org.got5.tapestry5.jquery.jqplot.test.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.json.JSONLiteral;
import org.apache.tapestry5.json.JSONObject;
import org.got5.tapestry5.jquery.jqplot.components.JqPlotBar;
import org.got5.tapestry5.jquery.jqplot.data.XYDataItem;



public class BarStacked{

	
	
	@Component(parameters = {"dataItems=testData", "graphTitle=literal:XYBarChart","params=options"})
    private JqPlotBar chart4;

	
	public JSONObject getOptions(){
    	JSONObject json = new JSONObject();
    	json.put("stackSeries", new JSONLiteral("true"));
    	json.put("captureRightClick", new JSONLiteral("true"));
    	
    	
    	JSONObject legend = new JSONObject();
    	legend.put("show", new JSONLiteral("true"));
    	legend.put("location", "e");
    	legend.put("placement", "outside");
    	
    	
    	json.put("legend", legend);
    	
    	
    	JSONObject options = new JSONObject();
    	
    	JSONObject renderer= new JSONObject();
    	renderer.put("renderer", new JSONLiteral("jQuery.jqplot.BarRenderer"));  
    	renderer.put("rendererOptions",new JSONLiteral("{fillToZero: true}"));	  
    	// The "seriesDefaults" option is an options object that will
        // be applied to all series in the chart.
    	json.put("seriesDefaults", renderer);  
    	
    	options.put("options", json);
    	
    	return options;
    }
	 
//	public JSONObject getOptions() 
//	{
//		JSONObject ret =  new JSONObject(); 
//		JSONObject options = new JSONObject();
//		options.put("stackSeries", new JSONLiteral("true"));
//		options.put("legend",new JSONLiteral("{show: true,location: 's',placement:'outside'}"));
//		options.put("animate", new JSONLiteral("true"));
//		
//		ret.put("options", options); 
//		return ret;
//	}

	public List getTestData()
    {
        List<List<XYDataItem>> dataList = CollectionFactory.newList();
        List<XYDataItem> list1 = CollectionFactory.newList();
        List<XYDataItem> list2 = CollectionFactory.newList();


        list1.add(new XYDataItem(0, 0.5));
        list1.add(new XYDataItem(1, 0.6));
        list1.add(new XYDataItem(2, 1.8));
        list1.add(new XYDataItem(3, 0.9));
        list1.add(new XYDataItem(4, 2));


        list2.add(new XYDataItem(0, 1.5));
        list2.add(new XYDataItem(1, 2));
        list2.add(new XYDataItem(2, 4.5));
        list2.add(new XYDataItem(3, 3.5));
        list2.add(new XYDataItem(4, 5.5));


        dataList.add(list1);
        dataList.add(list2);


        return dataList;
    }

}
