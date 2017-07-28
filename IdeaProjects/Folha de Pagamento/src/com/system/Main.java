package com.system;

import com.system.pessoas.Commissioned;
import com.system.pessoas.Hourly;
import com.system.pessoas.Salaried;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



public class Main {

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    Scanner scan = new Scanner(new File("entrada"));
    //Scanner scan = new Scanner(System.in);

    public Main() throws FileNotFoundException {
    }

    public int selecionarEmpregado(int kind, ArrayList<Hourly> listHourly, ArrayList<Salaried> listSalaried, ArrayList<Commissioned> listCommissioned) {

        int i = 0;
        switch (kind) {
            
            case 1:
                for (Hourly emp : listHourly) {

                    i = listHourly.indexOf(emp);
                    System.out.println(i + " - ID: " + emp.getID() + "; Nome: " + emp.getNome());

                }
                break;
            case 2:
                for (Salaried emp : listSalaried) {

                    i = listSalaried.indexOf(emp);
                    System.out.println(i + " - ID: " + emp.getID() + "; Nome: " + emp.getNome());
                }
                break;
            case 3:
                for (Commissioned emp : listCommissioned) {

                    i = listCommissioned.indexOf(emp);
                    System.out.println(i + " - ID: " + emp.getID() + "; Nome: " + emp.getNome());
                }
                break;
        }

        System.out.println("Selecione o empregado (-1 == Nenhum): ");
        int selected = scan.nextInt();

        return(selected);
    }

    public int adicionarEmpregado(int ID, ArrayList<Hourly> listHourly, ArrayList<Salaried> listSalaried, ArrayList<Commissioned> listCommissioned) {

        System.out.println("----Adicionar Empregado----");

        System.out.printf("Nome: ");
        String nome = scan.nextLine();
        System.out.printf("Endereço: ");
        String endereço = scan.nextLine();

        System.out.printf("Tipo: ");
        String tipo = scan.nextLine();

        if (tipo.equalsIgnoreCase("Hourly")) {

            System.out.printf("Salário Horário: ");
            double salárioHorário = scan.nextDouble();

            Hourly hourly = new Hourly(ID, nome, endereço, salárioHorário);
            listHourly.add(hourly);
            System.out.println(hourly);

        } else if (tipo.equalsIgnoreCase("Salaried")) {

            System.out.printf("Salário Mensal: ");
            double salárioMensal = scan.nextDouble();

            Salaried salaried = new Salaried(ID, nome, endereço, salárioMensal);
            listSalaried.add(salaried);
            System.out.println(salaried);

        } else { // Commissioned

            System.out.printf("Taxa de comissão: ");
            double taxaDeComissão = scan.nextDouble();

            Commissioned comissioned = new Commissioned(ID, nome, endereço, taxaDeComissão);
            listCommissioned.add(comissioned);
            System.out.println(comissioned);

        }

        return(ID + 1);
    }

    public void removerEmpregado(ArrayList<Hourly> listHourly, ArrayList<Salaried> listSalaried, ArrayList<Commissioned> listCommissioned) {

        System.out.println("----Remover Empregado----");
        System.out.println("1 - Empregados Horistas");
        System.out.println("2 - Empregados Salariados");
        System.out.println("3 - Empregados Comissionados");


        int command = scan.nextInt();

        int selected = selecionarEmpregado(command, listHourly, listSalaried, listCommissioned);

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

        System.out.println("Usuário removido com sucesso!");

    }

    public void lançarCartãoDePonto(ArrayList<Hourly> listHourly, ArrayList<Salaried> listSalaried, ArrayList<Commissioned> listCommissioned) throws ParseException {

        int selected = selecionarEmpregado(1, listHourly, listSalaried, listCommissioned);

        if (selected == -1) {
            return;
        }
        scan.nextLine();

        Date data = dateFormat.parse(scan.nextLine());
        double horasTrabalhadas = scan.nextDouble();

        listHourly.get(selected).lançarCartãoDePonto(data, horasTrabalhadas);
        System.out.println(listHourly.get(selected));
        System.out.println("Cartão de Ponto lançado com sucesso!");

    }

    public void lançarResultadoDeVenda(ArrayList<Hourly> listHourly, ArrayList<Salaried> listSalaried, ArrayList<Commissioned> listCommissioned) {

        int selected = selecionarEmpregado(3, listHourly, listSalaried, listCommissioned);

        if (selected == -1) {
            return;
        }
        scan.nextLine();



    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        Main main = new Main();

        int ID = 0;

        ArrayList<Hourly> listHourly = new ArrayList<>();
        ArrayList<Salaried> listSalaried = new ArrayList<>();
        ArrayList<Commissioned> listCommissioned = new ArrayList<>();

        while (true) {

            System.out.println("\n------------Menu------------");
            System.out.println("1 - Adicionar empregrado: ");
            System.out.println("2 - Remover empregado: ");
            System.out.println("3 - Lançar cartão de ponto: ");
            System.out.println("4 - Lançar resultado de venda: ");
            System.out.println("13 - Sair");

            int command = main.scan.nextInt();
            main.scan.nextLine();

            switch (command) {
                case 1: ID = main.adicionarEmpregado(ID, listHourly, listSalaried, listCommissioned);
                    break;
                case 2: main.removerEmpregado(listHourly, listSalaried, listCommissioned);
                    break;
                case 3: main.lançarCartãoDePonto(listHourly, listSalaried, listCommissioned);
                    break;
                case 4: main.lançarResultadoDeVenda(listHourly, listSalaried, listCommissioned);
                case 13:
                    System.out.printf("Até logo!");
                    return;
                default:
                    break;
            }
        }
    }

}
