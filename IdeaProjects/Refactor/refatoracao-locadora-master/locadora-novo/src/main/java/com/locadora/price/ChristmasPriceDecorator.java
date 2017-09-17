package main.java.com.locadora.price;

public class ChristmasPriceDecorator extends PriceDecorator {

	public ChristmasPriceDecorator(Price priceToDecorate) { super(priceToDecorate); }

	@Override
	public double getCharge(int daysRented) {
		return this.decoratedPrice.getCharge(daysRented) * 0.9;
	}

	@Override
	public int getFrequentRenterPoints(int daysRented) {
		return this.decoratedPrice.getFrequentRenterPoints(daysRented) + 1;
	}

	@Override
	public String toString() {
		return decoratedPrice.toString() + ", Christmas (10%)";
	}
}
