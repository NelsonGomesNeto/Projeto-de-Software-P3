package main.java.com.locadora;

public class Rental {
	
	Movie movie;

	int daysRented;

	public Rental(Movie movie, int daysRented) {
		try {
			this.movie = movie.clone();
		} catch (CloneNotSupportedException c) {
			c.printStackTrace();
		}
		this.daysRented = daysRented;
	}
	
	public double getCharge() {
		return movie.getCharge(daysRented);
	}
	
	public int getFrequentRenterPoints() {
		return movie.getFrequentRenterPoints(daysRented);
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}
	
}