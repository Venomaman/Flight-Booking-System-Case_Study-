package com.search.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.model.Flight;
import com.search.repository.FlightSearchRepository;

@RestController
@RequestMapping("/Search")
@CrossOrigin(origins = "http://localhost:4200/")
public class FlightSearchController {
	
	Logger logger = LoggerFactory.getLogger(FlightSearchController.class);
	
	@Autowired
	public FlightSearchRepository frepo;
	
	@GetMapping("/flights")
	public List<Flight> getAllFlights(){
		logger.trace("Flight Search Accessed!");
		return frepo.findAll();
		
	}
	
	// getting all the flights based on their location...
	
	@GetMapping("/find/{departure_location}/{arrival_location}")
	public List<Flight> getFlightByData(@PathVariable("departure_location") String dep_loc,@PathVariable("arrival_location") String arr_loc){
		logger.trace("Flight Search By loaction accessed!");
		return frepo.findByData(dep_loc, arr_loc);	
	}
	
	// Admin going to add the Flights....
	
	@PostMapping("/addFlights")
	public String addFlight(@RequestBody Flight flight) {
		frepo.save(flight);
	return "Added Flight:" +flight.getFlight();
	}
	
	//Admin going to update the new Flight....
	
	@PutMapping("/updateFlights/{id}")
	public Flight updateFlight(@RequestBody Flight flight, @PathVariable("id") int flights_id) {
		flight.setId(flights_id);
		frepo.save(flight);
		return flight;
	}
	
}
