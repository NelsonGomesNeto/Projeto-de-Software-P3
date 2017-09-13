package main.java.com.locadora.price;

public interface Price {

	double getCharge(int daysRented);

	int getFrequentRenterPoints(int daysRented);
	
}