package com.cgi.travel.controllers;

import com.cgi.travel.clients.RestClient;
import com.cgi.travel.models.Flight;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class FlightForm extends FormLayout{

	//private TextField flightCode=new TextField("Flight Code");
	private NativeSelect<ServiceOperator> serviceOperator=new NativeSelect<>("Service Operator");
	
	private TextField fromCity=new TextField("From City");
	private TextField toCity=new TextField("To City");
	
	private RestClient client=new RestClient();
	
	private Button save = new Button("Save");
	private Button delete = new Button("Delete");
	private MyUI myUI;
	private FlightService service=FlightService.getInstance();
	private Flight flight;
	private Binder<Flight> binder=new Binder<>(Flight.class);
	public FlightForm(MyUI myUI) {
		super();
		// TODO Auto-generated constructor stub
		this.myUI=myUI;
		setSizeUndefined();
	    HorizontalLayout buttons = new HorizontalLayout(save, delete);
	    addComponents(serviceOperator, fromCity, toCity, buttons);
	    serviceOperator.setItems(ServiceOperator.values());
	    save.setStyleName(ValoTheme.BUTTON_PRIMARY);
	    delete.setStyleName(ValoTheme.BUTTON_DANGER);
	    save.setClickShortcut(KeyCode.ENTER);
	    /*
	    binder.forField(flightCode)
	    .withConverter(
	      new StringToIntegerConverter("Must enter a number"))
	    .bind(Flight::getFlightCode, Flight::setFlightCode);
	    */
	    binder.bindInstanceFields(this);
	    save.addClickListener(e -> this.save());
	   // delete.addClickListener(e -> this.delete());
	}
	

	
	public void setFlight(Flight flight)
	{
		this.flight=flight;
		binder.setBean(this.flight);
		
	   // delete.setVisible(flight.isPersisted());
	    setVisible(true);
	    //flightCode.selectAll();
		
	}
	/*
	private void delete() {
	    serviceOp.delete(this.customer);
	    myUI.updateList();
	    setVisible(false);
	}
	*/

	private void save() {
		//System.out.println(this.flight);
		client.sendFlight(flight);
	    service.save(this.flight);
	    myUI.updateList();
	    setVisible(false);
	}
}
