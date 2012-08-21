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

import java.util.List;

import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;

import org.got5.tapestry5.jquery.jqplot.components.*;
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