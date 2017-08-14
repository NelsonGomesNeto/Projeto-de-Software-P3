package com.system.main;

import java.io.FileNotFoundException;
import java.text.ParseException;


public class Main extends PayrollSystem {

	public Main() throws FileNotFoundException {
	}

	public static void main(String[] args) throws FileNotFoundException, ParseException {

    PayrollSystem main = new PayrollSystem();

		main.runMenu();

  }

}
