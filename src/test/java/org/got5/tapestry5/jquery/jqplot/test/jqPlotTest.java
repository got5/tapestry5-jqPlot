package org.got5.tapestry5.jquery.jqplot.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

public class jqPlotTest extends SeleniumTestCase {
	
	@Test
	public void testCharts() {
		
		open("/index");
		
		//check if the jqplot-xaxis-tick is displayed for chart1
		assertTrue(isElementPresent("//*[@id='chart1']/div[2]/div[@class='jqplot-xaxis-tick']"));
		
		//check if the jqplot-table-legend is displayed for chart2
		assertTrue(isElementPresent("//*[@id='chart2']/table/tbody/tr[@class='jqplot-table-legend']"));
		
		//check if the jqplot-xaxis-tick is displayed for chart3
		assertTrue(isElementPresent("//*[@id='chart3']/div[2]/div[@class='jqplot-xaxis-tick']"));
		//check if the jqplot-cursor-tooltip is displayed for chart3
		assertTrue(isElementPresent("//*[@id='chart3']/div[@class='jqplot-cursor-tooltip']"));
		
	
		//check if the jqplot-table-legend is displayed for chart4
		assertTrue(isElementPresent("//*[@id='chart4']/table[@class='jqplot-table-legend']"));
				
		
	}
}
