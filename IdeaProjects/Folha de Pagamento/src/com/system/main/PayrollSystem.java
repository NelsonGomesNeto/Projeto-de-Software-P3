package com.system.main;

import com.system.database.DataBase;
import com.system.payments.PaymentSchedule;
import com.system.payments.Sale;
import com.system.payments.ServiceFee;
import com.system.payments.TimeCard;
import com.system.people.Commissioned;
import com.system.people.Hourly;
import com.system.people.Salaried;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PayrollSystem {

	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	DataBase undo = new DataBase();
	DataBase redo = new DataBase();

	int ID;

	ArrayList<Hourly> listHourly = new ArrayList<>();
	ArrayList<Salaried> listSalaried = new ArrayList<>();
	ArrayList<Commissioned> listCommissioned = new ArrayList<>();
	ArrayList<PaymentSchedule> listSchedule = new ArrayList<>();

	public PayrollSystem() throws FileNotFoundException {

		ID = 0;

		listSchedule.add(new PaymentSchedule("weekly", 5));
		listSchedule.add(new PaymentSchedule("monthly", 32));
		listSchedule.add(new PaymentSchedule("bi-weekly", 5));

		undo.pushAll(listHourly, listSalaried, listCommissioned);
	}

	protected Scanner scan = new Scanner(System.in);

	public void runMenu() throws ParseException {

		while (true) {

			System.out.println("\n------------Menu------------");
			System.out.println("1  - Add employee: ");
			System.out.println("2  - Remove employee: ");
			System.out.println("3  - Launch Time Card: ");
			System.out.println("4  - Launch Sale: ");
			System.out.println("5  - Launch Service Fee: ");
			System.out.println("6  - Edit Employee Details: ");
			System.out.println("7  - Run Payroll: ");
			System.out.println("8  - Undo: ");
			System.out.println("9  - Redo: ");
			System.out.println("10 - Edit Payment Schedule: ");
			System.out.println("11 - Create Payment Schedule: ");
			System.out.println("12 - Load input");
			System.out.println("13 - Leave");

			int command = scan.nextInt();
			scan.nextLine();

			try {

				switch (command) {
					case 1: ID = addEmployee();
						break;
					case 2: removeEmployee();
						break;
					case 3: launchTimeCard();
						break;
					case 4: launchSale();
						break;
					case 5: launchServiceFee();
						break;
					case 6: editEmployeeDetails();
						break;
					case 7: runPayroll();
						break;
					case 8: undo();
						break;
					case 9: redo();
						break;
					case 10: editPaymentSchedule();
						break;
					case 11: createPaymentSchedule();
						break;
					case 12: loadInputFromFile();
						break;
					case 13:
						System.out.printf("Good bye!");
						return;
					default:
						break;
				}
			} catch (EOFException e) {

			}

			if (command != 8 && command != 9 && command != 7 && command != 13) {

				undo.pushAll(listHourly, listSalaried, listCommissioned);
			}
		}
	}

	public int selectEmployee(int kind) throws EOFException {

		int i = 0;
		switch (kind) {

			case 1:
				for (Hourly emp : listHourly) {

					i = listHourly.indexOf(emp);
					System.out.println(i + " - id: " + emp.getId() + "; Name: " + emp.getName());

				}
				break;
			case 2:
				for (Salaried emp : listSalaried) {

					i = listSalaried.indexOf(emp);
					System.out.println(i + " - id: " + emp.getId() + "; Name: " + emp.getName());
				}
				break;
			case 3:
				for (Commissioned emp : listCommissioned) {

					i = listCommissioned.indexOf(emp);
					System.out.println(i + " - id: " + emp.getId() + "; Name: " + emp.getName());
				}
				break;
		}

		System.out.println("Select a employee (-1 == None): ");
		int selected = scan.nextInt();

		return (selected);
	}

	public int addEmployee() throws EOFException {

		System.out.println("----Add Employee----");

		System.out.printf("Name: ");
		String name = scan.nextLine();
		System.out.printf("Address: ");
		String address = scan.nextLine();

		System.out.printf("Tipo: ");
		String kind = scan.nextLine();

		if (kind.equalsIgnoreCase("Hourly")) {

			System.out.printf("Wage: ");
			double wage = scan.nextDouble();

			Hourly hourly = new Hourly(ID, name, address, wage);
			listHourly.add(hourly);
			//PayrollSystem.out.println(hourly);

		} else if (kind.equalsIgnoreCase("Salaried")) {

			System.out.printf("Salary: ");
			double salary = scan.nextDouble();

			Salaried salaried = new Salaried(ID, name, address, salary);
			listSalaried.add(salaried);
			//PayrollSystem.out.println(salaried);

		} else { // Commissioned

			System.out.printf("Commission Fee: ");
			double commissionFee = scan.nextDouble();

			Commissioned commissioned = new Commissioned(ID, name, address, commissionFee);
			listCommissioned.add(commissioned);
			//PayrollSystem.out.println(commissioned);

		}

		return (ID + 1);
	}

	public void removeEmployee() throws EOFException {

		System.out.println("----Remove Employee----");
		System.out.println("1 - Hourly Employees");
		System.out.println("2 - Salaried Employees");
		System.out.println("3 - Commissioned Employees");

		int command = scan.nextInt();

		int selected = selectEmployee(command);

		if (selected == -1) {

			return;
		}

		switch (command) {

			case 1:
				listHourly.remove(selected);
				break;
			case 2:
				listSalaried.remove(selected);
				break;
			case 3:
				listCommissioned.remove(selected);
				break;
			default:
				break;
		}

		System.out.println("Employee successfully removed!");

	}

	public void launchTimeCard() throws ParseException, EOFException {

		int selected = selectEmployee(1);

		if (selected == -1) {
			return;
		}
		scan.nextLine();

		Date date = dateFormat.parse(scan.nextLine());
		double workedHours = scan.nextDouble();

		date = listHourly.get(selected).paymentSchedule.newPaymentDay(date);

		listHourly.get(selected).lançarCartãoDePonto(date, workedHours);
		//PayrollSystem.out.println(listHourly.get(selected));
		System.out.println("Time Card successfully launched!\n");

	}

	public void launchSale() throws ParseException, EOFException {

		int selected = selectEmployee(3);

		if (selected == -1) {
			return;
		}
		scan.nextLine();

		Date date = dateFormat.parse(scan.nextLine());
		double saleValue = scan.nextDouble();

		date = listCommissioned.get(selected).paymentSchedule.newPaymentDay(date);

		listCommissioned.get(selected).launchSaleResult(date, saleValue);
		//PayrollSystem.out.println(listHourly.get(selected));
		System.out.println("Sale successfully launched!\n");
	}

	public void launchServiceFee() throws ParseException, EOFException {

		System.out.println("----Launch Service Fee----");
		System.out.println("1 - Hourly Employees");
		System.out.println("2 - Salaried Employees");
		System.out.println("3 - Commissioned Employees");

		int command = scan.nextInt();

		int selected = selectEmployee(command);

		if (selected == -1) {

			return;
		}
		scan.nextLine();

		Date date = dateFormat.parse(scan.nextLine());
		double feeValue = scan.nextDouble();

		switch (command) {
			case 1:
				date = listHourly.get(selected).paymentSchedule.newPaymentDay(date);
				listHourly.get(selected).launchServiceFee(date, feeValue);
				break;
			case 2:
				date = listSalaried.get(selected).paymentSchedule.newPaymentDay(date);
				listSalaried.get(selected).launchServiceFee(date, feeValue);
				break;
			case 3:
				date = listCommissioned.get(selected).paymentSchedule.newPaymentDay(date);
				listCommissioned.get(selected).launchServiceFee(date, feeValue);
				break;
			default:
				break;
		}
	}

	public void editEmployeeDetails() throws ParseException, EOFException {

		System.out.println("----Edit Employee Details----");
		System.out.println("1 - Hourly Employees");
		System.out.println("2 - Salaried Employees");
		System.out.println("3 - Commissioned Employees");

		int command = scan.nextInt();

		int selected = selectEmployee(command);

		if (selected == -1) {

			return;
		}
		scan.nextLine();

		System.out.printf("Name: ");
		String name = scan.nextLine();
		System.out.printf("Address: ");
		String address = scan.nextLine();

		System.out.printf("Kind: ");
		String kind = scan.nextLine();

		switch (command) { // Checa se é possível trocar o kind

			case 1:
				if (listHourly.get(selected).getQuantidadeDeCartoões() > 0 && !kind.equalsIgnoreCase("Hourly")) {

					System.out.println("You can't edit this kind of employee!");
					return;
				}
				break;
			case 3:
				if (listCommissioned.get(selected).getNumberOfSales() > 0 && kind.equalsIgnoreCase("Commissioned")) {

					System.out.println("You can't edit this kind of employee!");
					return;
				}
				break;
			default:
				break;
		}


		System.out.println("Payment Method: ");
		String paymentMethod = scan.nextLine();

		switch (command) {

			case 1:
				listHourly.get(selected).setPaymentMethod(paymentMethod);
				break;
			case 2:
				listSalaried.get(selected).setPaymentMethod(paymentMethod);
				break;
			case 3:
				listCommissioned.get(selected).setPaymentMethod(paymentMethod);
				break;
			default:
				break;
		}

		System.out.printf("Belongs to syndicate: (S/N)");
		String isPartOfSyndicate = scan.nextLine();

		if (isPartOfSyndicate.equalsIgnoreCase("S")) {

			switch (command) {

				case 1:
					if (!listHourly.get(selected).isPartOfSyndicate) {
						listHourly.get(selected).setPartOfSyndicate(true);
						listHourly.get(selected).setIdInSyndicate(2 * 10000 + listHourly.get(selected).id);
					}
					break;
				case 2:
					if (!listSalaried.get(selected).isPartOfSyndicate) {
						listSalaried.get(selected).setPartOfSyndicate(true);
						listSalaried.get(selected).setIdInSyndicate(2 * 10000 + listSalaried.get(selected).id);
					}
					break;
				case 3:
					if (!listCommissioned.get(selected).isPartOfSyndicate) {
						listCommissioned.get(selected).setPartOfSyndicate(true);
						listCommissioned.get(selected).setIdInSyndicate(2 * 10000 + listCommissioned.get(selected).id);
					}
					break;
				default:
					break;
			}
		}

		switch (kind) { // Pode ser removido

			case "Hourly":
				switch (command) {
					case 1:
						//listHourly.add(new Hourly(listHourly.get(selected).id, name, address, paymentMethod,
						//listHourly.get(selected).idInSyndicate, listHourly.get(selected).isPartOfSyndicate, listHourly.get(selected).getFees()));
						break;
					case 2:
						listHourly.add(new Hourly(listSalaried.get(selected).id, name, address, paymentMethod,
							listSalaried.get(selected).idInSyndicate, listSalaried.get(selected).isPartOfSyndicate, listSalaried.get(selected).getFees()));
						listSalaried.remove(selected);
						break;
					case 3:
						listHourly.add(new Hourly(listCommissioned.get(selected).id, name, address, paymentMethod,
							listCommissioned.get(selected).idInSyndicate, listCommissioned.get(selected).isPartOfSyndicate, listCommissioned.get(selected).getFees()));
						listCommissioned.remove(selected);
						break;
					default:
						break;
				}
				break;
			case "Salaried":
				switch (command) {
					case 1:
						listSalaried.add(new Salaried(listHourly.get(selected).id, name, address, paymentMethod,
							listHourly.get(selected).idInSyndicate, listHourly.get(selected).isPartOfSyndicate, listHourly.get(selected).getFees()));
						listHourly.remove(selected);
						break;
					case 2:
						//listSalaried.add(new Salaried(listSalaried.get(selected).id, name, address, paymentMethod,
						//listSalaried.get(selected).idInSyndicate, listSalaried.get(selected).isPartOfSyndicate, listSalaried.get(selected).getFees()));
						break;
					case 3:
						listSalaried.add(new Salaried(listCommissioned.get(selected).id, name, address, paymentMethod,
							listCommissioned.get(selected).idInSyndicate, listCommissioned.get(selected).isPartOfSyndicate, listCommissioned.get(selected).getFees()));
						listCommissioned.remove(selected);
						break;
					default:
						break;
				}
				break;
			case "Commissioned":
				switch (command) {
					case 1:
						listCommissioned.add(new Commissioned(listHourly.get(selected).id, name, address, paymentMethod,
							listHourly.get(selected).idInSyndicate, listHourly.get(selected).isPartOfSyndicate, listHourly.get(selected).getFees()));
						listHourly.remove(selected);
						break;
					case 2:
						listCommissioned.add(new Commissioned(listSalaried.get(selected).id, name, address, paymentMethod,
							listSalaried.get(selected).idInSyndicate, listSalaried.get(selected).isPartOfSyndicate, listSalaried.get(selected).getFees()));
						listSalaried.remove(selected);
						break;
					case 3:
						//listCommissioned.add(new Commissioned(listCommissioned.get(selected).id, name, address, paymentMethod,
						//listCommissioned.get(selected).idInSyndicate, listCommissioned.get(selected).isPartOfSyndicate, listCommissioned.get(selected).getFees()));
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}

		System.out.println("Employee successfully edited!");
	}

	public void runPayroll() throws ParseException, EOFException {

		System.out.println("----Run Payroll----");

		System.out.printf("Initial date: ");
		Date initialDate = dateFormat.parse(scan.nextLine());

		System.out.printf("Final date: ");
		Date finalDate = dateFormat.parse(scan.nextLine());

		double totalPayment = 0;

		System.out.println("Hourly: ");
		for (Hourly hourly : listHourly) {

			double currentPayment = 0;

			for (TimeCard card : hourly.getCards()) {

				if (card.getDate().after(initialDate) && card.getDate().before(finalDate)) {

					if (card.getWorkedHours() > 8) {

						currentPayment += (card.getWorkedHours() * hourly.getWage() * 1.5);
					} else {

						currentPayment += (card.getWorkedHours() * hourly.getWage());
					}

				}
			}

			for (ServiceFee fee : hourly.getFees()) {

				if (fee.getDate().after(initialDate) && fee.getDate().before(finalDate)) {

					currentPayment -= fee.getFeeValue();
				}
			}

			if (currentPayment != 0) {

				System.out.println("\t" + hourly.getName() + " R$" + currentPayment + ", via: " + hourly.getPaymentMethod());
			}
			totalPayment += currentPayment;
		}

		System.out.println("Salaried: ");
		for (Salaried salaried : listSalaried) {

			double currentPayment = 0;

			Date newPaymentDate = salaried.paymentSchedule.newPaymentDay(initialDate);
			if (newPaymentDate.after(initialDate) && newPaymentDate.before(finalDate)) {
				currentPayment = salaried.getSalary();
			}

			for (ServiceFee fee : salaried.getFees()) {

				if (fee.getDate().after(initialDate) && fee.getDate().before(finalDate)) {

					currentPayment -= fee.getFeeValue();
				}
			}

			if (currentPayment != 0) {

				System.out.println("\t" + salaried.getName() + " R$" + currentPayment + ", via: " + salaried.getPaymentMethod());
			}
			totalPayment += salaried.getSalary();
		}

		System.out.println("Commissioned: ");
		for (Commissioned commissioned : listCommissioned) {

			double currentPayment = 0;
			for (Sale sale : commissioned.getSales()) {

				if (sale.getDate().after(initialDate) && sale.getDate().before(finalDate)) {

					currentPayment += commissioned.getCommissionFee() * sale.getSaleValue();
				}
			}

			for (ServiceFee fee : commissioned.getFees()) {

				if (fee.getDate().after(initialDate) && fee.getDate().before(finalDate)) {

					currentPayment -= fee.getFeeValue();
				}
			}

			if (currentPayment != 0) {

				System.out.println("\t" + commissioned.getName() + " R$" + currentPayment + ", via: " + commissioned.getPaymentMethod());
			}
			totalPayment += currentPayment;
		}

		System.out.println("The total is: R$" + totalPayment + "\n");
	}

	public void undo() throws EOFException {

		if (undo.getSize() != 0) {

			redo.pushAll(undo.popHourly(), undo.popSalaried(), undo.popCommissioned());

			listHourly = undo.popHourly();
			listSalaried = undo.popSalaried();
			listCommissioned = undo.popCommissioned();

			System.out.println("Undone!");
		} else {

			System.out.println("It's not possible to undo!");
		}
	}

	public void redo() throws EOFException {

		if (redo.getSize() != 0) {

			listHourly = redo.popHourly();
			listSalaried = redo.popSalaried();
			listCommissioned = redo.popCommissioned();

			System.out.println("Redone!");
		} else {

			System.out.println("It's not possible to redo!");
		}
	}

	public void editPaymentSchedule() throws EOFException {

		System.out.println("----Edit Payment Schedule----");
		System.out.println("1 - Hourly Employees");
		System.out.println("2 - Salaried Employees");
		System.out.println("3 - Commissioned Employees");

		int command = scan.nextInt();

		int selectedEmployee = selectEmployee(command);

		if (selectedEmployee == -1) {

			return;
		}

		int i = 0;
		for (PaymentSchedule paymentSchedule : listSchedule) {

			System.out.println(i + " - Period: " + paymentSchedule.period + ", Day: " + paymentSchedule.day);
		}
		System.out.printf("Select Schedule: ");
		int selectedSchedule = scan.nextInt();

		switch (command) {
			case 1:
				listHourly.get(selectedEmployee).setPaymentSchedule(listSchedule.get(selectedSchedule));
				break;
			case 2:
				listSalaried.get(selectedEmployee).setPaymentSchedule(listSchedule.get(selectedSchedule));
				break;
			case 3:
				listCommissioned.get(selectedEmployee).setPaymentSchedule(listSchedule.get(selectedSchedule));
		}

		System.out.println("Schedule successfully edited!");
	}

	public void createPaymentSchedule() throws EOFException {

		System.out.println("----Create Payment Schedule----");

		System.out.printf("Payment period: ");
		String periodo = scan.nextLine();
		System.out.printf("Day: ");
		int dia = scan.nextInt();

		listSchedule.add(new PaymentSchedule(periodo, dia));
	}

	public void loadInputFromFile() {

		System.out.println("----Load input from file----");

		System.out.printf("Name of the file to read: ");
		String file = scan.nextLine();
		try {

			this.scan = new Scanner(new File(file));
		} catch (FileNotFoundException e) {

			System.out.println("There wasn't found such a file named: " + file);
		}
	}

}
