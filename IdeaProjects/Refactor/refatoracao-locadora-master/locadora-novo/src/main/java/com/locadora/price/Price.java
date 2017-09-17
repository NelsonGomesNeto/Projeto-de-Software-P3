package main.java.com.locadora.price;

public interface Price {

	double getCharge(int daysRented);

	Price removeDiscount();

	int getFrequentRenterPoints(int daysRented);
	
}