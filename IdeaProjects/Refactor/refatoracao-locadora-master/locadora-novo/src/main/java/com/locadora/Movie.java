package main.java.com.locadora;

import main.java.com.locadora.price.ChildrenPrice;
import main.java.com.locadora.price.NewReleasePrice;
import main.java.com.locadora.price.Price;
import main.java.com.locadora.price.RegularPrice;

public class Movie {

	public static final int CHILDREN = 2;

	public static final int REGULAR = 0;

	public static final int NEW_RELEASE = 1;

	private String title;

	private Price price;

	
	public Movie(String title, int priceCode) {
		this.title = title;
		this.setPriceCode(priceCode);
		price = new RegularPrice();
	}
	
	public double getCharge(int daysRented) {
		return price.getCharge(daysRented);
	}
	
	public void setPriceCode(int priceCode) {
		switch (priceCode) {
			case Movie.REGULAR :
				price = new RegularPrice();
				break;
			case Movie.NEW_RELEASE :
				price = new NewReleasePrice();
				break;
			case Movie.CHILDREN :
				price = new ChildrenPrice();
				break;
			default :
				throw new IllegalArgumentException("Incorrect Price Code.");
		}
	}
	
	public int getFrequentRenterPoints(int daysRented) {
		return price.getFrequentRenterPoints(daysRented);
	}

	public int getPriceCode() {
		return price.getPriceCode();
	}

	public String getTitle() {
		return title;
	}
	
}