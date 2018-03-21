package com.cgi.travel.controllers;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.cgi.travel.models.Flight;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI(path="/ui")
@Theme("valo")
public class MyUI extends UI {

	private FlightService customerService=FlightService.getInstance();
	//private Grid<Customer> grid=new Grid<>(Customer.class);
	//private TextField filterText =new TextField();
	private FlightForm flightForm=new FlightForm(this);
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	Image image = new Image("CGI logo:",
    		    new ClassResource("kingfisher.jpg"));
    	image.setWidth("100px");
    	image.setHeight("100px");
    	
    	final VerticalLayout logoLayout = new VerticalLayout();
    	logoLayout.addComponent(image);
    	 final VerticalLayout layout = new VerticalLayout();
    	/*
       
        filterText.setPlaceholder("Filter by Name");
        filterText.addValueChangeListener(e->updateList());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        */
        Button addFlightBtn = new Button("Add new Flight");
        addFlightBtn.addClickListener(e -> {
            //grid.asSingleSelect().clear();
            flightForm.setFlight(new Flight());
        });
        /*
        Button clearTextBtn=new Button(VaadinIcons.CLOSE);
        clearTextBtn.setDescription("Clear Filter Text");
        clearTextBtn.addClickListener(e->filterText.clear());
        CssLayout filteringLayout=new CssLayout();
        filteringLayout.addComponents(filterText,clearTextBtn);
        filteringLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        */
        HorizontalLayout toolbar = new HorizontalLayout(logoLayout,addFlightBtn);
     
        //grid.setColumns("firstName", "lastName", "email");
        HorizontalLayout main = new HorizontalLayout(flightForm);
       
        main.setSizeFull();
        //grid.setSizeFull();
        //main.setExpandRatio(grid, 1);

        
        layout.addComponents(toolbar,main);
        updateList();
        /*
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        layout.addComponents(name, button);
        */
        setContent(layout);
        flightForm.setVisible(false);
        /*
        grid.asSingleSelect().addValueChangeListener(e->{
        	if(e.getValue()==null)
        		customerForm.setVisible(false);
        	else
        		customerForm.setCustomer(e.getValue());
        });
        */
    }

	public void updateList() {
		//List<Customer> customers=customerService.findAll(filterText.getValue());
       // grid.setItems(customers);
	}

    
}
