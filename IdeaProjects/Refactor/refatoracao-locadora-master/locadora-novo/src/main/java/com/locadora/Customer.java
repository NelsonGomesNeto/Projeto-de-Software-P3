package main.java.com.locadora;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private String name;
	
	private List<Rental> rentals;

	public Customer(String name) {
		this.name = name;
		this.rentals = new ArrayList<>();
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String statement() {
		String result = "Rental Record for " + name + "\n";
		
		for (Rental each : rentals) {
			result += "* " + each.getMovie().getTitle() + "\t" + String.valueOf(Math.round(100 * each.getCharge()) / 100.0) + "\n";
		}
		
		result += "Amount owed is " + String.valueOf(Math.round(100 * getTotalAmount()) / 100.0) + "\n";
		result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
		
		return result;
	}
	
	private double getTotalAmount() {
		return rentals.stream().mapToDouble(Rental::getCharge).sum();
	}
	
	private int getTotalFrequentRenterPoints() {
		return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
	}

	public String getName() {
		return name;
	}
	
}