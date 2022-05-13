package com.book.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;

import com.book.model.Booking;
import com.book.model.Passenger;
import com.book.repository.BookingRepository;
import com.book.service.EmailService;

@RestController
@RequestMapping("/FlightBooking")
@CrossOrigin(origins="*",maxAge=3600)
public class FlightBookingController {

@Autowired
public BookingRepository book_repo;


//@Autowired
//private RestTemplate restTemplate;

@Autowired
EmailService emailService;


//For user to book flight and amount being set according to seat-passenger details

@PostMapping("/BookFlight")
public String bookFlight(@RequestBody Booking booking) {
	
	booking.setTotal_amount(setTotal_amount(booking));
	book_repo.save(booking);
	return emailService.sendEmail(booking);
	
}

//For User+Admin to get Details of booking (booking_id=PNR)

@GetMapping("/BookedFlight/{booking_id}")
public Optional<Booking> getBooking (@PathVariable("booking_id") long booking_id){
	return book_repo.findById(booking_id);
	
}

//error:-string to date 
//Admin to get all bookings on the date

@GetMapping("/Booking/{date}/{flight_id}")
public List<Booking>getBookingByDate(@PathVariable Date date,@PathVariable int flight_id){
	return book_repo.findByDate(date);
}

//For user to Cancel Booking OR Self Check-In

@PutMapping("/booking/{booking_id}")
public Booking updateBooking(@RequestBody Booking booking,@PathVariable("booking_id") long pnr) {
	Booking dbResponse=book_repo.findById(pnr).get();
	dbResponse.setBooking_cancelled(booking.isBooking_cancelled());
	dbResponse.setChecked_in(booking.isChecked_in());
	book_repo.save(dbResponse);

	 if(booking.checked_in==true) {System.out.println("checked in");return emailService.checkIn(dbResponse);}
	  if(booking.booking_cancelled==true)
	  {booking.setChecked_in(false);System.out.println("Booking Cancelled");return emailService.bookingCancelled(dbResponse);}
	return dbResponse;
	 
}

//Setting total amount for payment gateway fetched from passenger data 
public double setTotal_amount(Booking booking) {
	double tot_sum=0;
List<Passenger> list=	booking.getPassenger();
for (Passenger passenger : list) {
	tot_sum+=passenger.getAmount();

}
return tot_sum;


}
}
