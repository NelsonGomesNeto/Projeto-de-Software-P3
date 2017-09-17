package main.java.com.locadora;

import main.java.com.locadora.price.*;

public class Movie implements Cloneable {

	public static final int CHILDREN = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHRISTMAS = 3;

	private String title;

	private Price price;

	
	public Movie(String title) {
		this.title = title;
		price = new RegularPrice();
	}

	@Override
	public Movie clone() throws CloneNotSupportedException {
		return (Movie) super.clone();
	}
	
	public double getCharge(int daysRented) {
		return price.getCharge(daysRented);
	}

	public String getPriceState() { return this.price.toString(); }
	
	public void addDiscount(int priceCode) {
		switch (priceCode) {
			case Movie.REGULAR :
				break;
			case Movie.NEW_RELEASE :
				price = new NewReleasePriceDecorator(price);
				break;
			case Movie.CHILDREN :
				price = new ChildrenPriceDecorator(price);
				break;
			case Movie.CHRISTMAS :
				price = new ChristmasPriceDecorator(price);
				break;
			default :
				throw new IllegalArgumentException("Incorrect Price Code.");
		}
	}

	public void removeDiscount() { this.price = this.price.removeDiscount(); }
	
	public int getFrequentRenterPoints(int daysRented) {
		return price.getFrequentRenterPoints(daysRented);
	}

	public String getTitle() {
		return title;
	}
}