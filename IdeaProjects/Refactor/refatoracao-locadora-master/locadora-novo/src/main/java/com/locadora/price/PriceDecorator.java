package main.java.com.locadora.price;

public abstract class PriceDecorator implements Price {

	protected Price decoratedPrice;

	public PriceDecorator(Price priceToDecorate) { this.decoratedPrice = priceToDecorate;	}

	public Price removeDiscount() { return this.decoratedPrice; }

	@Override
	public double getCharge(int daysRented) { return this.decoratedPrice.getCharge(daysRented); }

	@Override
	public int getFrequentRenterPoints(int daysRented) { return this.decoratedPrice.getFrequentRenterPoints(daysRented);	}

}
