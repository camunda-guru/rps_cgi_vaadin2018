package com.cgi.travel.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cgi.travel.configurations.OracleHelper;
import com.cgi.travel.models.Flight;

/**
 * An in memory dummy "database" for the example purposes. In a typical Java app
 * this class would be replaced by e.g. EJB or a Spring based service class.
 * <p>
 * In demos/tutorials/examples, get a reference to this service class with
 * {@link CustomerService#getInstance()}.
 */
public class FlightService {

	private static FlightService  instance;
	private static final Logger LOGGER = Logger.getLogger(FlightService .class.getName());

	private final HashMap<Long, Flight> flights = new HashMap<>();
	private long nextId = 0;

	private FlightService() {
	}

	
	public static FlightService getInstance() {
		if (instance == null) {
			instance = new FlightService();
			//instance.ensureTestData();
		}
		return instance;
	}

	/*
	public synchronized List<Flight> findAll() {
		return findAll(null);
	}
*/
	
	public synchronized List<Flight> findAll(long filter) {
		return OracleHelper.getFilteredFlights(filter);
		
		
		
		/*
		for (Flight flight : flights.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| flight.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(flight.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(FlightService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Flight>() {

			@Override
			public int compare(Flight o1, Flight o2) {
				return o2.getFlightCode() > o1.getFlightCode();
			}
		});
		
		return arrayList;
		*/
	}

	/*
	public synchronized List<Flight> findAll(String stringFilter, int start, int maxresults) {
		ArrayList<Flight> arrayList = new ArrayList<>();
		for (Flight flight : flights.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| flight.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(flight.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(FlightService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Flight>() {

			@Override
			public int compare(Flight o1, Flight o2) {
				return o2.getFlightCode().compareTo(o1.getFlightCode());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}
*/
	
	public synchronized long count() {
		return flights.size();
	}

	
	public synchronized void delete(Flight value) {
		flights.remove(value.getFlightCode());
	}

	
	public synchronized void save(Flight entry) {
		
		if (entry == null) {
			LOGGER.log(Level.SEVERE,
					"Flight is null. Are you sure you have connected your form");
			return;
		}
		
		try {
			entry = (Flight) entry.clone();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		flights.put(entry.getFlightCode(), entry);
	}

	
	

}