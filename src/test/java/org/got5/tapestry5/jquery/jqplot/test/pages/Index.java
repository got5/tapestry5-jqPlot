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

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;

import org.got5.tapestry5.jquery.jqplot.components.*;
import org.got5.tapestry5.jquery.jqplot.data.DateValueDataItem;
import org.got5.tapestry5.jquery.jqplot.data.TextValueDataItem;
import org.got5.tapestry5.jquery.jqplot.data.XYDataItem;

/**
 * extract form chenillekit from homburgs
 */
public class Index
{
	
    private List<List<XYDataItem>> testData;

    @Component(parameters = {"dataItems=testData", "graphTitle=literal:XYLineChart"})
    private JqPlot chart1;

    @Component(parameters = {"dataItems=testPieData", "graphTitle=literal:PieChart"})
    private JqPlotPie chart2 ;
    
    @Component(parameters = {"dataItems=testData", "graphTitle=literal:XYLineChartWithZooming"})
    private JqPlotZooming chart3;
 
    @Component(parameters = {"dataItems=testDateData", 
    		                 "xAxisMinDate=minDate",
    		                 "xAxisMaxDate=maxDate",
    		                 "xAxisTickerInterval=literal:30 minutes",
    		                 "graphTitle=literal:XYDateChartWithZooming"})
    private JqPlotDateZooming chart4;
    
    public Date getMaxDate() {
    	return ((List<DateValueDataItem>) getTestDateData().get(0)).get(0).getDate();
    }
    
    public Date getMinDate() {
    	return ((List<DateValueDataItem>) getTestDateData().get(0)).get( ((List<DateValueDataItem>) getTestDateData().get(0)).size() -1 ).getDate();
    }
    
    @Cached
    public List getTestDateData()
    {
        List<List<DateValueDataItem>> dataList = CollectionFactory.newList();
        List<DateValueDataItem> list1 = CollectionFactory.newList();
        List<DateValueDataItem> list2 = CollectionFactory.newList();
        
        Date one = new Date( (new Date().getTime()) - 1000*60*60 );
        Date two = new Date( ((one.getTime()) - 1000*60*60) + 1000L  );
        Date three = new Date( ((two.getTime()) - 1000*60*60) + 1000L  );
        Date four = new Date( ((three.getTime()) - 1000*60*60) + 1000L  );
        Date five = new Date( ((four.getTime()) - 1000*60*60) + 1000L  );
        Date six = new Date( ((five.getTime()) - 1000*60*60) + 1000L  );
        Date seven = new Date( ((six.getTime()) - 1000*60*60) + 1000L  );
        Date eight = new Date( ((seven.getTime()) - 1000*60*60) + 1000L  );
        Date nine = new Date( ((eight.getTime()) - 1000*60*60) + 1000L  );
        Date ten = new Date( ((nine.getTime()) - 1000*60*60) + 1000L  );

        list1.add(new DateValueDataItem(one, 0.5));
        list1.add(new DateValueDataItem(two, 0.6));
        list1.add(new DateValueDataItem(three, 1.8));
        list1.add(new DateValueDataItem(four, 0.9));
        list1.add(new DateValueDataItem(five, 0.9));
        list1.add(new DateValueDataItem(six, 0.1));
        list1.add(new DateValueDataItem(seven, 2.1));
        list1.add(new DateValueDataItem(eight, 0.7));
        list1.add(new DateValueDataItem(nine, 5.0));
        list1.add(new DateValueDataItem(ten, 1.0));

        list2.add(new DateValueDataItem(one, 1.5));
        list2.add(new DateValueDataItem(two, 6));
        list2.add(new DateValueDataItem(three, 3.8));
        list2.add(new DateValueDataItem(four, 3.0));
        list2.add(new DateValueDataItem(five, 4.9));
        list2.add(new DateValueDataItem(six, 1.0));
        list2.add(new DateValueDataItem(seven, 0.1));
        list2.add(new DateValueDataItem(eight, 0.7));
        list2.add(new DateValueDataItem(nine, 2.2));
        list2.add(new DateValueDataItem(ten, 6.0));

        dataList.add(list1);
        dataList.add(list2);

        return dataList;
    }

    @Cached
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
    
    @Cached
    public List getTestPieData()
    {
        List<List<TextValueDataItem>> dataList = CollectionFactory.newList();
        List<TextValueDataItem> list1 = CollectionFactory.newList();
      
        list1.add(new TextValueDataItem("Heavy Industry",12));
        list1.add(new TextValueDataItem("Retail", 9));
        list1.add(new TextValueDataItem("Light Industry",14));
        list1.add(new TextValueDataItem("Out of home", 16));
        list1.add(new TextValueDataItem("Orientation", 2));

      
        dataList.add(list1);
      
        return dataList;
    }
}