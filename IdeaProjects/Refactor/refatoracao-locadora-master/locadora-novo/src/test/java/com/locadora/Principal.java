package test.java.com.locadora;

import main.java.com.locadora.Customer;
import main.java.com.locadora.Movie;
import main.java.com.locadora.Rental;

public class Principal {

	public static void main(String[] args) {
		
		Customer rafaelMonteiro = new Customer("Rafael Monteiro");
		Customer nelsonGomes = new Customer("Nelson Gomes");

		Movie spiderMan = new Movie("Spider Man");
		System.out.println(spiderMan.getPriceState());
		spiderMan.addDiscount(Movie.NEW_RELEASE);
		System.out.println(spiderMan.getPriceState());
		rafaelMonteiro.addRental(new Rental(spiderMan, 2));
		System.out.println(rafaelMonteiro.statement());

		spiderMan.removeDiscount();
		System.out.println(spiderMan.getPriceState());
		nelsonGomes.addRental(new Rental(spiderMan, 2));
		System.out.println(nelsonGomes.statement());
		System.out.println(rafaelMonteiro.statement());

		Movie insideOut = new Movie("Inside Out");
		System.out.println(insideOut.getPriceState());
		insideOut.addDiscount(Movie.NEW_RELEASE);
		insideOut.addDiscount(Movie.CHILDREN);
		insideOut.addDiscount(Movie.CHRISTMAS);
		System.out.println(insideOut.getPriceState());
		nelsonGomes.addRental(new Rental(insideOut, 20));
		System.out.println(nelsonGomes.statement());

	}
	
}
