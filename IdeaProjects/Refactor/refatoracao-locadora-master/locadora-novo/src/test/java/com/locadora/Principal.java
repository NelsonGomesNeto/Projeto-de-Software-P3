package test.java.com.locadora;

import main.java.com.locadora.Customer;
import main.java.com.locadora.Movie;
import main.java.com.locadora.Rental;

public class Principal {

	public static void main(String[] args) {
		
		Customer c = new Customer("Rafael Monteiro");
		c.addRental(new Rental(new Movie("Homem Aranha", 1), 2));
		c.addRental(new Rental(new Movie("Mulher Maravilha", 0), 1));
		
		System.out.println(c.statement());
		
	}
	
}
