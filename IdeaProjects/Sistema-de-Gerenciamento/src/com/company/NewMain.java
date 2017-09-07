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
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class NewMain {

	Scanner scanner = new Scanner(new File("input"));
	//Scanner scanner = new Scanner(System.in);
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");

	public NewMain() throws FileNotFoundException {
	}

	public void editActivity(ArrayList<Activity> activities) {
		System.out.println("Enter activity to change state: (-1 == None)");
		int id = scanner.nextInt(); scanner.nextLine();
		if (id == -1) {
			return;
		} else {
			activities.get(id - 1).nextState();
		}
	}

	public void printActivities(ArrayList<Activity> activities) {
		int counter  = 0;
		for (Activity activity : activities) {
			counter ++;
			System.out.println(counter + " - " + activity);
		}
	}

	public User selectUser(DataBase dataBase) {

		System.out.printf("Enter user name: ");
		String name = scanner.nextLine();

		ArrayList<User> users = dataBase.getUserByName(name);

		User user = null;
		if (users.size() == 1) {

			user = users.get(0);
		} else if (users.size() > 1){

			int counter = 0;
			for (User userToFind : users) {
				counter ++;
				System.out.println(counter + " - " + userToFind.getCPF());
			}
			int selected = scanner.nextInt();
			scanner.nextLine();
			user = users.get(selected);
		}

		return(user);
	}

	public int selectUserKind() {

		System.out.println("1 - Graduation Student");
		System.out.println("2 - Mastering Student");
		System.out.println("3 - PhD Student");
		System.out.println("4 - Professor");
		System.out.println("5 - Researcher");
		System.out.println("6 - Administrator");

		System.out.printf("Enter user kind: ");
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

	public User decorateUser(User user) {

		int kind = selectUserKind();
		switch (kind) {
			case 1:
				return(new GraduationStudent(user));
			case 2:
				return(new MasteringStudent(user));
			case 3:
				return(new PhDStudent(user));
			case 4:
				return(new ProfessorDecorator(user));
			case 5:
				return(new ResearcherDecorator(user));
			case 6:
				return(new AdminDecorator(user));
			default:
				return(user);
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

		user = decorateUser(user);

		dataBase.addUser(user);
		System.out.println("User created successfully!");
	}

	public void editUser(DataBase dataBase) {

		System.out.println("\n---------Edit User---------");
		User user = selectUser(dataBase);

		if (user == null) {
			System.out.println("User wasn't found!");
			return;
		}
		user = decorateUser(user);
		dataBase.setUser(user);
		System.out.println("User edited successfully!");
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

		User user = selectUser(dataBase);
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

		User user = selectUser(dataBase);
		if (user == null) {
			System.out.println("User wasn't found!");
			return;
		} else {
			System.out.println("\t" + user);
			if (user.hasActivities()) {
				System.out.println("\tActivities:");
				printActivities(user.getActivities());
				editActivity(user.getActivities());
			}
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
			if (resource.hasActivities()) {
				System.out.println("\tActivities:");
				printActivities(resource.getActivities());
				editActivity(resource.getActivities());
			}
		}
	}

	public void visualizeActivities(DataBase dataBase) {

		System.out.println("\n----Activities Visualization----");

		System.out.println("Activities:");
		printActivities(dataBase.getActivities());
		editActivity(dataBase.getActivities());
	}

	public void report(DataBase dataBase) {
		dataBase.report();
	}

	public static void main(String[] args) throws FileNotFoundException, ParseException {

		NewMain newMain = new NewMain();
		DataBase dataBase = new DataBase();

		boolean logged = true;
		// Main Menu
		while (logged) {

			System.out.println("\n----------Main Menu----------");
			System.out.println("1 - Create User");
			System.out.println("2 - Edit User");
			System.out.println("3 - Create Resource");
			System.out.println("4 - Create Activity"); // Allocate Resource
			System.out.println("5 - Consult User");
			System.out.println("6 - Consult Resource");
			System.out.println("7 - Visualize Activities");
			System.out.println("8 - Report");
			System.out.println("9 - Exit");

			int command = newMain.scanner.nextInt();
			newMain.scanner.nextLine();

			switch (command) {
				case 1: newMain.createUser(dataBase);
					break;
				case 2: newMain.editUser(dataBase);
					break;
        case 3: newMain.createResource(dataBase);
          break;
        case 4: newMain.createActivity(dataBase);
          break;
        case 5: newMain.consultUser(dataBase);
	        break;
        case 6: newMain.consultResource(dataBase);
	        break;
        case 7: newMain.visualizeActivities(dataBase);
	        break;
				case 8: newMain.report(dataBase);
					break;
				case 9: logged = false;
					break;
				default: System.out.println("Please select a valid command!");
					break;
			}
		}

		System.out.println("Bye! :)");
		return;
	}
}
