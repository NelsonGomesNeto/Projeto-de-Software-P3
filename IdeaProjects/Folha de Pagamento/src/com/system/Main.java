package com.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.system.pagamentos.CartãoDePonto;
import com.system.pagamentos.Venda;
import com.system.pessoas.Commissioned;
import com.system.pessoas.Hourly;
import com.system.pessoas.Salaried;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;


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

    return (selected);
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

    return (ID + 1);
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
    System.out.println("Cartão de Ponto lançado com sucesso!\n");

  }

  public void lançarResultadoDeVenda(ArrayList<Hourly> listHourly, ArrayList<Salaried> listSalaried, ArrayList<Commissioned> listCommissioned) throws ParseException {

    int selected = selecionarEmpregado(3, listHourly, listSalaried, listCommissioned);

    if (selected == -1) {
      return;
    }
    scan.nextLine();

    Date data = dateFormat.parse(scan.nextLine());
    double valorDaVenda = scan.nextDouble();

    listCommissioned.get(selected).lançarResultadoDeVenda(data, valorDaVenda);
    System.out.println(listHourly.get(selected));
    System.out.println("Resultado de Venda lançado com sucesso!\n");
  }

  public void lançarTaxaDeServiço(ArrayList<Hourly> listHourly, ArrayList<Salaried> listSalaried, ArrayList<Commissioned> listCommissioned) throws ParseException {

    System.out.println("----Lançar Taxa de Serviço----");
    System.out.println("1 - Empregados Horistas");
    System.out.println("2 - Empregados Salariados");
    System.out.println("3 - Empregados Comissionados");

    int command = scan.nextInt();

    int selected = selecionarEmpregado(command, listHourly, listSalaried, listCommissioned);

    if (selected == -1) {

      return;
    }
    scan.nextLine();

    Date data = dateFormat.parse(scan.nextLine());
    double valorDaTaxa = scan.nextDouble();

    switch (command) {
      case 1:
        listHourly.get(selected).lançarTaxaDeServiço(data, valorDaTaxa);
        break;
      case 2:
        listSalaried.get(selected).lançarTaxaDeServiço(data, valorDaTaxa);
        break;
      case 3:
        listCommissioned.get(selected).lançarTaxaDeServiço(data, valorDaTaxa);
        break;
      default:
        break;
    }
  }

  public void alterarDetalhesDeUmEmpregado(ArrayList<Hourly> listHourly, ArrayList<Salaried> listSalaried, ArrayList<Commissioned> listCommissioned) throws ParseException {

    System.out.println("----Alterar detalhes de um Empregado----");
    System.out.println("1 - Empregados Horistas");
    System.out.println("2 - Empregados Salariados");
    System.out.println("3 - Empregados Comissionados");

    int command = scan.nextInt();

    int selected = selecionarEmpregado(command, listHourly, listSalaried, listCommissioned);

    if (selected == -1) {

      return;
    }
    scan.nextLine();

    System.out.printf("Nome: ");
    String nome = scan.nextLine();
    System.out.printf("Endereço: ");
    String endereço = scan.nextLine();

    System.out.printf("Tipo: ");
    String tipo = scan.nextLine();

    switch (command) { // Checa se é possível trocar o tipo

      case 1:
        if (listHourly.get(selected).getQuantidadeDeCartoões() > 0 && !tipo.equalsIgnoreCase("Hourly")) {

          System.out.println("Você não pode alterar o tipo desse empregado!");
          return;
        }
        break;
      case 3:
        if (listCommissioned.get(selected).getQuantidadeDeVendas() > 0 && tipo.equalsIgnoreCase("Commissioned")) {

          System.out.println("Você não pode alterar o tipo desse empregado!");
          return;
        }
        break;
      default:
        break;
    }


    System.out.println("Método de Pagamento: ");
    String métodoDePagamento = scan.nextLine();

    switch (command) {

      case 1:
        listHourly.get(selected).setMétodoDePagamento(métodoDePagamento);
        break;
      case 2:
        listSalaried.get(selected).setMétodoDePagamento(métodoDePagamento);
        break;
      case 3:
        listCommissioned.get(selected).setMétodoDePagamento(métodoDePagamento);
        break;
      default:
        break;
    }

    System.out.printf("Pertence ao sindicado: (S/N)");
    String pertenceAoSindicato = scan.nextLine();

    if (pertenceAoSindicato.equalsIgnoreCase("S")) {

      switch (command) {

        case 1:
          if (!listHourly.get(selected).fazParteDoSindicato) {
            listHourly.get(selected).setFazParteDoSindicato(true);
            listHourly.get(selected).setIDNoSindicato(2 * 10000 + listHourly.get(selected).ID);
          }
          break;
        case 2:
          if (!listSalaried.get(selected).fazParteDoSindicato) {
            listSalaried.get(selected).setFazParteDoSindicato(true);
            listSalaried.get(selected).setIDNoSindicato(2 * 10000 + listSalaried.get(selected).ID);
          }
          break;
        case 3:
          if (!listCommissioned.get(selected).fazParteDoSindicato) {
            listCommissioned.get(selected).setFazParteDoSindicato(true);
            listCommissioned.get(selected).setIDNoSindicato(2 * 10000 + listCommissioned.get(selected).ID);
          }
          break;
        default:
          break;
      }
    }

    switch (tipo) { // Pode ser removido

      case "Hourly":
        switch (command) {
          case 1:
            //listHourly.add(new Hourly(listHourly.get(selected).ID, nome, endereço, métodoDePagamento,
              //listHourly.get(selected).IDNoSindicato, listHourly.get(selected).fazParteDoSindicato, listHourly.get(selected).getTaxas()));
            break;
          case 2:
            listHourly.add(new Hourly(listSalaried.get(selected).ID, nome, endereço, métodoDePagamento,
              listSalaried.get(selected).IDNoSindicato, listSalaried.get(selected).fazParteDoSindicato, listSalaried.get(selected).getTaxas()));
            listSalaried.remove(selected);
            break;
          case 3:
            listHourly.add(new Hourly(listCommissioned.get(selected).ID, nome, endereço, métodoDePagamento,
              listCommissioned.get(selected).IDNoSindicato, listCommissioned.get(selected).fazParteDoSindicato, listCommissioned.get(selected).getTaxas()));
            listCommissioned.remove(selected);
            break;
          default:
            break;
        }
        break;
      case "Salaried":
        switch (command) {
          case 1:
            listSalaried.add(new Salaried(listHourly.get(selected).ID, nome, endereço, métodoDePagamento,
              listHourly.get(selected).IDNoSindicato, listHourly.get(selected).fazParteDoSindicato, listHourly.get(selected).getTaxas()));
            listHourly.remove(selected);
            break;
          case 2:
            //listSalaried.add(new Salaried(listSalaried.get(selected).ID, nome, endereço, métodoDePagamento,
              //listSalaried.get(selected).IDNoSindicato, listSalaried.get(selected).fazParteDoSindicato, listSalaried.get(selected).getTaxas()));
            break;
          case 3:
            listSalaried.add(new Salaried(listCommissioned.get(selected).ID, nome, endereço, métodoDePagamento,
              listCommissioned.get(selected).IDNoSindicato, listCommissioned.get(selected).fazParteDoSindicato, listCommissioned.get(selected).getTaxas()));
            listCommissioned.remove(selected);
            break;
          default:
            break;
        }
        break;
      case "Commissioned":
        switch (command) {
          case 1:
            listCommissioned.add(new Commissioned(listHourly.get(selected).ID, nome, endereço, métodoDePagamento,
              listHourly.get(selected).IDNoSindicato, listHourly.get(selected).fazParteDoSindicato, listHourly.get(selected).getTaxas()));
            listHourly.remove(selected);
            break;
          case 2:
            listCommissioned.add(new Commissioned(listSalaried.get(selected).ID, nome, endereço, métodoDePagamento,
              listSalaried.get(selected).IDNoSindicato, listSalaried.get(selected).fazParteDoSindicato, listSalaried.get(selected).getTaxas()));
            listSalaried.remove(selected);
            break;
          case 3:
            //listCommissioned.add(new Commissioned(listCommissioned.get(selected).ID, nome, endereço, métodoDePagamento,
              //listCommissioned.get(selected).IDNoSindicato, listCommissioned.get(selected).fazParteDoSindicato, listCommissioned.get(selected).getTaxas()));
            break;
          default:
            break;
        }
        break;
      default:
        break;
    }

    System.out.println("Empregado alterado com sucesso!");
  }

  public void rodarFolhaDePagamento(ArrayList<Hourly> listHourly, ArrayList<Salaried> listSalaried, ArrayList<Commissioned> listCommissioned) throws ParseException {

    System.out.println("----Rodar Folha de Pagamento----");

    System.out.printf("Data inicial: ");
    Date dataInicial = dateFormat.parse(scan.nextLine());

    System.out.printf("Data final: ");
    Date dataFinal = dateFormat.parse(scan.nextLine());

    double pagamentoTotal = 0;

    System.out.println("Horistas: ");
    for (Hourly hourly : listHourly) {

      double pagamentoAtual = 0;

      for (CartãoDePonto cartão : hourly.getCartões()) {


        if (cartão.getData().after(dataInicial) && cartão.getData().before(dataFinal)) {

          if (cartão.getHorasTrabalhadas() > 8) {

            pagamentoAtual += (cartão.getHorasTrabalhadas() * hourly.getSalárioHorário() * 1.5);
          } else {

            pagamentoAtual += (cartão.getHorasTrabalhadas() * hourly.getSalárioHorário());
          }

        }
      }

      if (pagamentoAtual != 0) {

        System.out.println("\t" + hourly.getNome() + " R$" + pagamentoAtual + ", via: " + hourly.getMétodoDePagamento());
      }
      pagamentoTotal += pagamentoAtual;
    }

    System.out.println("Assaliados: ");
    for (Salaried salaried : listSalaried) {

      double pagamentoAtual = salaried.getSalárioMensal();

      if (pagamentoAtual != 0) {

        System.out.println("\t" + salaried.getNome() + " R$" + pagamentoAtual + ", via: " + salaried.getMétodoDePagamento());
      }
      pagamentoTotal += salaried.getSalárioMensal();
    }

    System.out.println("Comissionados: ");
    for (Commissioned commissioned : listCommissioned) {

      double pagamentoAtual = 0;
      for (Venda venda : commissioned.getVendas()) {

        if (venda.getData().after(dataInicial) && venda.getData().before(dataFinal)) {

          pagamentoAtual += commissioned.getTaxaDeComissão() * venda.getValorDaVenda();
        }
      }

      if (pagamentoAtual != 0) {

        System.out.println("\t" + commissioned.getNome() + " R$" + pagamentoAtual + ", via: " + commissioned.getMétodoDePagamento());
      }
      pagamentoTotal += pagamentoAtual;
    }

    System.out.println("O total é de: R$" + pagamentoTotal + "\n");
  }

  public void undo(ArrayList<Hourly> listHourly, ArrayList<Salaried> listSalaried, ArrayList<Commissioned> listCommissioned,
                   Stack<ArrayList<Hourly>> pilhaUndoHourly, Stack<ArrayList<Salaried>> pilhaUndoSalaried, Stack<ArrayList<Commissioned>> pilhaUndoCommissioned,
                   Stack<ArrayList<Hourly>> pilhaRedoHourly, Stack<ArrayList<Salaried>> pilhaRedoSalaried, Stack<ArrayList<Commissioned>> pilhaRedoCommissioned) {

    if (!pilhaUndoHourly.empty()) {

      Gson gson = new Gson();
      Type type = new TypeToken<ArrayList<Hourly>>() {}.getType();
      String json = gson.toJson(pilhaUndoHourly.pop());
      pilhaRedoHourly.push(gson.fromJson(json, type));

      type = new TypeToken<ArrayList<Salaried>>() {}.getType();
      json = gson.toJson(pilhaUndoSalaried.pop());
      pilhaRedoSalaried.push(gson.fromJson(json, type));

      type = new TypeToken<ArrayList<Commissioned>>() {}.getType();
      json = gson.toJson(pilhaUndoCommissioned.pop());
      pilhaRedoCommissioned.push(gson.fromJson(json, type));

      listHourly = pilhaUndoHourly.pop();
      listSalaried = pilhaUndoSalaried.pop();
      listCommissioned = pilhaUndoCommissioned.pop();

      System.out.println("Undo concluído!");
    } else {

      System.out.println("Não é possível dar undo!");
    }
  }

  public void redo(ArrayList<Hourly> listHourly, ArrayList<Salaried> listSalaried, ArrayList<Commissioned> listCommissioned,
                   Stack<ArrayList<Hourly>> pilhaUndoHourly, Stack<ArrayList<Salaried>> pilhaUndoSalaried, Stack<ArrayList<Commissioned>> pilhaUndoCommissioned,
                   Stack<ArrayList<Hourly>> pilhaRedoHourly, Stack<ArrayList<Salaried>> pilhaRedoSalaried, Stack<ArrayList<Commissioned>> pilhaRedoCommissioned) {

    if (!pilhaRedoHourly.empty()) {

      listHourly = pilhaRedoHourly.pop();
      listSalaried = pilhaRedoSalaried.pop();
      listCommissioned = pilhaRedoCommissioned.pop();

      System.out.println("Redo concluído!");
    } else {

      System.out.println("Não é possível dar redo!");
    }
  }

  public static void main(String[] args) throws FileNotFoundException, ParseException {

    Main main = new Main();
    Gson gson = new Gson();

    String json;
    int ID = 0;

    ArrayList<Hourly> listHourly = new ArrayList<>();
    ArrayList<Salaried> listSalaried = new ArrayList<>();
    ArrayList<Commissioned> listCommissioned = new ArrayList<>();

    Stack<ArrayList<Hourly>> pilhaUndoHourly = new Stack<>();
    pilhaUndoHourly.push(new ArrayList<Hourly>());
    Stack<ArrayList<Salaried>> pilhaUndoSalaried = new Stack<>();
    pilhaUndoSalaried.push(new ArrayList<Salaried>());
    Stack<ArrayList<Commissioned>> pilhaUndoCommissioned = new Stack<>();
    pilhaUndoCommissioned.push(new ArrayList<Commissioned>());

    Stack<ArrayList<Hourly>> pilhaRedoHourly = new Stack<>();
    Stack<ArrayList<Salaried>> pilhaRedoSalaried = new Stack<>();
    Stack<ArrayList<Commissioned>> pilhaRedoCommissioned = new Stack<>();

    while (true) {

      System.out.println("\n------------Menu------------");
      System.out.println("1 - Adicionar empregrado: ");
      System.out.println("2 - Remover empregado: ");
      System.out.println("3 - Lançar cartão de ponto: ");
      System.out.println("4 - Lançar resultado de venda: ");
      System.out.println("5 - Lançar taxa de serviço: ");
      System.out.println("6 - Alterar detalhes de um empregado: ");
      System.out.println("7 - Rodar folha de pagamento: ");
      System.out.println("8 - Undo: ");
      System.out.println("9 - Redo: ");
      System.out.println("13 - Sair");

      int command = main.scan.nextInt();
      main.scan.nextLine();

      switch (command) {
        case 1:
          ID = main.adicionarEmpregado(ID, listHourly, listSalaried, listCommissioned);
          break;
        case 2:
          main.removerEmpregado(listHourly, listSalaried, listCommissioned);
          break;
        case 3:
          main.lançarCartãoDePonto(listHourly, listSalaried, listCommissioned);
          break;
        case 4:
          main.lançarResultadoDeVenda(listHourly, listSalaried, listCommissioned);
          break;
        case 5:
          main.lançarTaxaDeServiço(listHourly, listSalaried, listCommissioned);
          break;
        case 6:
          main.alterarDetalhesDeUmEmpregado(listHourly, listSalaried, listCommissioned);
          break;
        case 7:
          main.rodarFolhaDePagamento(listHourly, listSalaried, listCommissioned);
          break;
        case 8:
          main.undo(listHourly, listSalaried, listCommissioned, pilhaUndoHourly, pilhaUndoSalaried, pilhaUndoCommissioned,
            pilhaRedoHourly, pilhaRedoSalaried, pilhaRedoCommissioned);
          break;
        case 9:
          main.redo(listHourly, listSalaried, listCommissioned, pilhaUndoHourly, pilhaUndoSalaried, pilhaUndoCommissioned,
            pilhaRedoHourly, pilhaRedoSalaried, pilhaRedoCommissioned);
          break;
        case 13:
          System.out.printf("Até logo!");
          return;
        default:
          break;
      }

      if (command != 8 && command != 9 && command != 7) {

        Type type = new TypeToken<ArrayList<Hourly>>() {
        }.getType();
        json = gson.toJson(listHourly);
        pilhaUndoHourly.push(gson.fromJson(json, type));

        type = new TypeToken<ArrayList<Salaried>>() {
        }.getType();
        json = gson.toJson(listSalaried);
        pilhaUndoSalaried.push(gson.fromJson(json, type));

        type = new TypeToken<ArrayList<Commissioned>>() {
        }.getType();
        json = gson.toJson(listCommissioned);
        pilhaUndoCommissioned.push(gson.fromJson(json, type));
      }
    }

  }

}
