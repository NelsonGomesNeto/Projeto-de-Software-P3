package main.java.com.locadora.price;

public abstract class PriceDecorator implements Price {

	protected Price decoratedPrice;

	public PriceDecorator(Price priceToDecorate) { this.decoratedPrice = priceToDecorate;	}

	@Override
	public double getCharge(int daysRented) { return decoratedPrice.getCharge(daysRented); }

	@Override
	public int getFrequentRenterPoints(int daysRented) { return decoratedPrice.getFrequentRenterPoints(daysRented);	}
}
