package com.company;

import com.company.activity.Activity;
import com.company.activity.Laboratory;
import com.company.activity.Presentation;
import com.company.database.DataBase;
import com.company.resource.Resource;
import com.company.user.*;
import com.company.user.student.MasteringStudent;
import com.company.user.student.PhDStudent;
import com.company.user.student.GraduationStudent;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class NewMain {

	Scanner scanner = new Scanner(new File("input"));
	//Scanner scanner = new Scanner(System.in);
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");

	public NewMain() throws FileNotFoundException {
	}

	public int selectUserKind() {

		System.out.println("1 - Graduation Student");
		System.out.println("2 - MasteringStudent Student");
		System.out.println("3 - PhD Student");
		System.out.println("4 - Professor");
		System.out.println("5 - Researcher");
		System.out.println("6 - Administrator");

		int kind = scanner.nextInt();
		scanner.nextLine();

		return(kind);
	}

	public String selectActivityKind() {

		System.out.println("1 - Laboratory");
		System.out.println("2 - Presentation");
		System.out.println("3 - Traditional Class");

		int kind = scanner.nextInt();
		scanner.nextLine();

		switch (kind) {
			case 1:
				return("Laboratory");
			case 2:
				return("Presentation");
			default: // 3
				return("Traditional Class");
		}
	}

	public void createUser(DataBase dataBase) {

		System.out.println("\n-------Create User-------");

		System.out.printf("Enter the name: ");
		String name = scanner.nextLine();

		System.out.printf("Enter the CPF: ");
		String CPF = scanner.nextLine();
		if (!dataBase.validCPF(CPF)) {
			System.out.println("User already registred!");
			return;
		}

		System.out.printf("Enter e-mail: ");
		String email = scanner.nextLine();

		User user = new SimpleUser(name, CPF, email);

		System.out.println("Enter the kind of user: ");
		int kind = selectUserKind();
		switch (kind) {
			case 1:
				user = new GraduationStudent(user);
				break;
			case 2:
				user = new MasteringStudent(user);
				break;
			case 3:
				user = new PhDStudent(user);
				break;
			case 4:
				user = new ProfessorDecorator(user);
				break;
			case 5:
				user = new ResearcherDecorator(user);
				break;
			case 6:
				user = new AdminDecorator(user);
				break;
			default:
				break;
		}

		dataBase.addUser(user);
		System.out.println("User created successfully!");
	}

	public void createResource(DataBase dataBase) {

		System.out.println("\n-------Create Resource-------");

		System.out.printf("Enter name: ");
		String name = scanner.nextLine();

		Resource resource = new Resource(name);
		dataBase.addResource(resource);

		System.out.println("Resource created successfully!");
	}

	public void createActivity(DataBase dataBase) throws ParseException {

		System.out.println("\n-------Create Activity-------");

		System.out.printf("Enter desired resource: ");
		String desiredResource = scanner.nextLine();
		Resource resource = dataBase.getResourceByName(desiredResource);
		if (desiredResource == null) {
			System.out.println("Resource wasn't found!");
			return;
		}

		System.out.printf("Enter allocator: ");
		String allocatorName = scanner.nextLine();
		User user = dataBase.getUserByName(allocatorName);
		if (user == null) {
			System.out.println("User wasn't found!");
			return;
		}

		String activityKind = selectActivityKind();
		if (!user.canAllocate(activityKind)) {
			System.out.println("This user can't allocate his kind of activity");
			return;
		}

		System.out.printf("Enter start time: ");
		Date dateBegin = dateFormat.parse(scanner.nextLine());
		System.out.printf("Enter end time: ");
		Date dateEnd = dateFormat.parse(scanner.nextLine());

		if (!user.isAvailable(dateBegin, dateEnd)) {
			System.out.println("User isn't available in this period!");
			return;
		}
		if (!resource.isAvailable(dateBegin, dateEnd)) {
			System.out.println("Resource isn't available in this period!");
			return;
		}

		System.out.printf("Enter title: ");
		String title = scanner.nextLine();
		System.out.printf("Enter description: ");
		String description = scanner.nextLine();
		System.out.printf("Enter participants: ");
		String participants = scanner.nextLine();

		Activity activity = null;
		switch (activityKind) {
			case "Laboratory":
				activity = new Laboratory(title, user, resource, description, participants, dataBase.allocating, dateBegin, dateEnd);
				break;
			case "Presentation":
				activity = new Presentation(title, user, resource, description, participants, dataBase.allocating, dateBegin, dateEnd);
				break;
			case "Traditional Class":
				activity = new Presentation(title, user, resource, description, participants, dataBase.allocating, dateBegin, dateEnd);
				break;
			default:
				break;
		}

		dataBase.addActivity(activity);
		user.addActivity(activity);
		resource.addActivity(activity);

		System.out.println("Activity successfully created!");
	}

	public void consultUser(DataBase dataBase) {

		System.out.println("\n-------Consult User--------");

		System.out.printf("Search user:");
		String searchedUser = scanner.nextLine();

		User user = dataBase.getUserByName(searchedUser);
		if (user == null) {
			System.out.println("User wasn't found!");
			return;
		} else {
			System.out.println("\t" + user);
		}
	}

	public void consultResource(DataBase dataBase) {

		System.out.println("\n-------Consult Resource--------");

		System.out.printf("Search resource: ");
		String searchedResource = scanner.nextLine();

		Resource resource = dataBase.getResourceByName(searchedResource);
		if (resource == null) {
			System.out.println("Resource wasn't found!");
			return;
		} else {
			System.out.println("\t" + resource);
		}
	}

	public void visualizeActivities(DataBase dataBase) {

		System.out.println("\n----Activities Visualization----");

		System.out.println("Activities:");
		int counter = 0;
		for (Activity activity : dataBase.getActivities()) {
			counter ++;
			System.out.println(counter + " - " + activity);
		}
	}

	public void report(DataBase dataBase) {
		dataBase.report();
	}

	public static void main(String[] args) throws FileNotFoundException, ParseException {

		NewMain newMain = new NewMain();
		DataBase dataBase = new DataBase();

		boolean logged = true;
		// Menu principal
		while (logged) {

			System.out.println("\n----------Menu Principal----------");
			System.out.println("1 - Create User");
			System.out.println("2 - Create Resource");
			System.out.println("3 - Create Activity"); // Alocar recurso
			System.out.println("4 - Consult User");
			System.out.println("5 - Consultar Resource");
			System.out.println("6 - Visualizar Atividades");
			System.out.println("7 - Relatório");
			System.out.println("8 - Sair");

			int command = newMain.scanner.nextInt();
			newMain.scanner.nextLine();

			switch (command) {
				case 1: newMain.createUser(dataBase);
					break;
        case 2: newMain.createResource(dataBase);
          break;
        case 3: newMain.createActivity(dataBase);
          break;
        case 4: newMain.consultUser(dataBase);
	        break;
        case 5: newMain.consultResource(dataBase);
	        break;
        case 6: newMain.visualizeActivities(dataBase);
	        break;
				case 7: newMain.report(dataBase);
					break;
				case 8: logged = false;
					break;
				default: System.out.println("Please select a valid command!");
					break;
			}
		}

		System.out.println("Bye! :)");
		return;
	}
}
