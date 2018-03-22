package com.cgi.bakery.controllers;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsFunnel;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.WaterFallSum;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
@SpringUI(path="/charts")
@Theme("valo")
public class VaadinchartsUI extends UI {

	

	@Override
	protected void init(VaadinRequest request) {
		   final VerticalLayout layout = new VerticalLayout();
		   layout.setMargin(true);
		   setContent(layout);
		   
		   HorizontalLayout line01 = new HorizontalLayout();
		   HorizontalLayout line02 = new HorizontalLayout();
		   
		   line01.addComponent(getLineChart());
		   line01.addComponent(getWaterfallChart());
		   line02.addComponent(getPieChart());
		   line02.addComponent(getFunnelChart());
		   layout.addComponent(line01);
		   layout.addComponent(line02);
	}

private Chart getLineChart()
{
	   Chart chart = new Chart();
	   chart.setWidth("300px");
	   chart.setHeight("300px");
	   Configuration conf = chart.getConfiguration();
	   conf.getChart().setType(ChartType.LINE);
	   conf.setTitle("Line Chart");

	   DataSeries serie1 = new DataSeries("SERIE 1");
	   serie1.add(new DataSeriesItem(0,0));
	   serie1.add(new DataSeriesItem(1,1));
	   serie1.add(new DataSeriesItem(2,2));
	   serie1.add(new DataSeriesItem(3,3));
	   serie1.add(new DataSeriesItem(4,4));
	   conf.addSeries(serie1);
	   PlotOptionsLine serie1Opts = new PlotOptionsLine();
	   serie1Opts.setColor(SolidColor.BLUE);
	   serie1.setPlotOptions(serie1Opts);

	   DataSeries serie2 = new DataSeries("SERIE 2");
	   serie2.add(new DataSeriesItem(0,2));
	   serie2.add(new DataSeriesItem(1,2));
	   serie2.add(new DataSeriesItem(2,3));
	   serie2.add(new DataSeriesItem(3,4));
	   serie2.add(new DataSeriesItem(4,5));
	   conf.addSeries(serie2);
	   PlotOptionsLine serie2Opts = new PlotOptionsLine();
	   serie2Opts.setColor(SolidColor.RED);
	   serie2.setPlotOptions(serie2Opts);
	   
	   return chart;
}

private Chart getWaterfallChart()
{
	Chart chart = new Chart(ChartType.WATERFALL);
	chart.setWidth("300px");
	chart.setHeight("300px");

	Configuration conf = chart.getConfiguration();
	conf.setTitle("Waterfall Chart");
	conf.getLegend().setEnabled(false);

	XAxis xaxis = new XAxis();
	xaxis.setCategories("Initial value", "+90", "-70" , "Total");
	conf.addxAxis(xaxis);

	YAxis yaxis = new YAxis();
	yaxis.setTitle("Accumulator");
	conf.addyAxis(yaxis);
	final Color negative = SolidColor.RED;
	final Color positive = SolidColor.GREEN;

	DataSeries series = new DataSeries();

	DataSeriesItem start = new DataSeriesItem("Start", 100);
	start.setColor(positive);
	series.add(start);
	
	DataSeriesItem a90 = new DataSeriesItem("+90", +90);
	a90.setColor(positive);
	series.add(a90);
	
	DataSeriesItem m70 = new DataSeriesItem("-70", -70);
	m70.setColor(negative);
	series.add(m70);

	WaterFallSum end = new WaterFallSum("Total");
	end.setColor(positive);
	end.setIntermediate(false);
	series.add(end);

	conf.addSeries(series);		
	return chart;
}

private Chart getPieChart()
{
	Chart chart = new Chart(ChartType.PIE);
	chart.setWidth("300px");
	chart.setHeight("300px");		
	Configuration conf = chart.getConfiguration();
	conf.setTitle("Pie Chart");
	PlotOptionsPie options = new PlotOptionsPie();
	options.setInnerSize("50px");
	options.setSize("75%");
	options.setCenter("50%", "50%");
	conf.setPlotOptions(options);
	DataSeries series = new DataSeries();
	series.add(new DataSeriesItem("35%", 35));
	series.add(new DataSeriesItem("40%", 40));
	DataSeriesItem s25 = new DataSeriesItem("25%", 25);
	s25.setSliced(true);
	series.add(s25);
	conf.addSeries(series);		
	
	return chart;
}


private Chart getFunnelChart()
{
	Chart chart = new Chart(ChartType.FUNNEL);
	chart.setWidth("300px");
	chart.setHeight("300px");		

	Configuration conf = chart.getConfiguration();
	conf.setTitle("Funnel Chart");
	conf.getLegend().setEnabled(false);

	PlotOptionsFunnel options = new PlotOptionsFunnel();
	options.setNeckHeight("50%");
	options.setNeckWidth("50%");

	conf.setPlotOptions(options);

	DataSeries series = new DataSeries();
	series.add(new DataSeriesItem("100", 100));
	series.add(new DataSeriesItem("50", 50));
	series.add(new DataSeriesItem("30", 30));
	series.add(new DataSeriesItem("20", 20));
	conf.addSeries(series);
	return chart;
}
}