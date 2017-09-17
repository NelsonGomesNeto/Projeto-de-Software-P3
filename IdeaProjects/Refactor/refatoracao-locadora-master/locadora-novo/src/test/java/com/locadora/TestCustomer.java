package test.java.com.locadora;

import static org.junit.Assert.*;

import main.java.com.locadora.Customer;
import main.java.com.locadora.Movie;
import main.java.com.locadora.Rental;
import org.junit.Before;
import org.junit.Test;

public class TestCustomer {

	private Customer client;

	@Before
	public void setUp() {
		client = new Customer("João");
	}

	@Test
	public void testNameCreation() {
		String result = client.statement();
		assertContain(result, "Rental Record for João");
	}

	@Test
	public void testOneRegularOneDay() {
		rentMovie("Indiana Jones", Movie.REGULAR, 2);
		String result = client.statement();
		assertContain(result, "Amount owed is 2.0");
		assertContain(result, "You earned 1 frequent renter points");
	}

	@Test
	public void testOneRegularTreeDays() {
		rentMovie("Indiana Jones", Movie.REGULAR, 3);
		String result = client.statement();
		assertContain(result, "Amount owed is 3.5");
		assertContain(result, "You earned 2 frequent renter points");
	}

	@Test
	public void testOneChildrensOneDay() {
		rentMovie("Procurando Nemo", Movie.CHILDREN, 1);
		String result = client.statement();
		assertContain(result, "Amount owed is 1.6");
		assertContain(result, "You earned 1 frequent renter points");
	}

	@Test
	public void testOneChildrensFiveDays() {
		rentMovie("Procurando Nemo", Movie.CHILDREN, 5);
		String result = client.statement();
		assertContain(result, "Amount owed is 5.2");
		assertContain(result, "You earned 2 frequent renter points");
	}

	@Test
	public void testOneNewReleaseOneDay() {
		rentMovie("Homem Aranha 2", Movie.NEW_RELEASE, 1);
		String result = client.statement();
		assertContain(result, "Amount owed is 1.4");
		assertContain(result, "You earned 2 frequent renter points");
	}

	@Test
	public void testOneNewReleaseTreeDays() {
		rentMovie("Homem Aranha 2", Movie.NEW_RELEASE, 3);
		String result = client.statement();
		assertContain(result, "Amount owed is 2.4");
		assertContain(result, "You earned 3 frequent renter points");
	}

	@Test
	public void testManyRents() {
		rentMovie("Homem Aranha 2", Movie.NEW_RELEASE, 2);
		rentMovie("Troia", Movie.NEW_RELEASE, 3);
		rentMovie("Procurando Nemo", Movie.CHILDREN, 3);
		rentMovie("Indiana Jones", Movie.REGULAR, 2);
		rentMovie("Rei Le�o", Movie.CHILDREN, 4);
		rentMovie("E o vento levou...", Movie.REGULAR, 3);
		String result = client.statement();
		assertContain(result, "Amount owed is 16.1");
		assertContain(result, "You earned 12 frequent renter points");
	}

	private void rentMovie(String title, int type, int days) {
		Movie movie = new Movie(title);
		movie.addDiscount(type);
		Rental rent = new Rental(movie, days);
		client.addRental(rent);
	}

	private void assertContain(String result, String content) {
		assertTrue(result.indexOf(content) >= 0);
	}

}