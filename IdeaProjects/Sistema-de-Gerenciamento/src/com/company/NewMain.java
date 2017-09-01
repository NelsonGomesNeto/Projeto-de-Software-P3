package com.company;

import com.company.database.DataBase;
import com.company.user.*;
import com.company.user.student.MasteringStudent;
import com.company.user.student.PhDStudent;
import com.company.user.student.StudentDecorator;
import com.company.user.student.GraduationStudent;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NewMain {

    Scanner scanner = new Scanner(new File("input"));

    public NewMain() throws FileNotFoundException {
    }

    public int selectKind() {

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
        int kind = selectKind();
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
        System.out.println("User added sucessfully!");
    }

    public void report(DataBase dataBase) {
        dataBase.report();
    }

    public static void main(String[] args) throws FileNotFoundException {

        NewMain newMain = new NewMain();
        DataBase dataBase = new DataBase();

        boolean logged = true;
        // Menu principal
        while (logged) {

            System.out.println("\n----------Menu Principal----------");
            System.out.println("1 - Create User");
            System.out.println("2 - Cadastrar Resource");
            System.out.println("3 - Cadastrar Atividade"); // Alocar recurso
            System.out.println("4 - Consultar Usuário");
            System.out.println("5 - Consultar Resource");
            System.out.println("6 - Visualizar Atividades");
            System.out.println("7 - Relatório");
            System.out.println("8 - Sair");

            int comando = newMain.scanner.nextInt();
            newMain.scanner.nextLine();

            switch (comando) {
                case 1: newMain.createUser(dataBase);
                    break;
//                case 2: newMain.cadastrarRecurso();
//                    break;
//                case 3: newMain.cadastrarAtividade();
//                    break;
//                case 4: newMain.consultarUsuario();
//                    break;
//                case 5: newMain.consultarRecurso();
//                    break;
//                case 6: newMain.visualizarAtividades();
//                    break;
                case 7: newMain.report(dataBase);
                    break;
                case 8: logged = false;
                    break;
                default: System.out.println("Por favor, selecione um comando válido!");
                    break;
            }
        }

        System.out.println("Até logo! :)");
        return;
    }
}
