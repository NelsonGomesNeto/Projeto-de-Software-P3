package com.system.pessoas;

import com.system.pagamentos.AgendaDePagamento;
import com.system.pagamentos.TaxaDeServiço;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by alunoic on 28/07/17.
 */
public class Empregado {

  public int ID;
  public String nome;
  private String endereço;

  private String métodoDePagamento = "Depósito Bancário";

  public int IDNoSindicato;
  public boolean fazParteDoSindicato;
  private ArrayList<TaxaDeServiço> taxas = new ArrayList<>();

  public AgendaDePagamento agendaDePagamento = null;

  public Empregado() {
  }

  public Empregado(int ID, String nome, String endereço) {

    this.ID = ID;
    this.nome = nome;
    this.endereço = endereço;
  }

  public Empregado(int ID, String nome, String endereço, String métodoDePagamento, int IDNoSindicato, boolean fazParteDoSindicato, ArrayList<TaxaDeServiço> taxas) {

    this.ID = ID;
    this.nome = nome;
    this.endereço = endereço;
    this.métodoDePagamento = métodoDePagamento;
    this.IDNoSindicato = IDNoSindicato;
    this.fazParteDoSindicato = fazParteDoSindicato;
    this.taxas = taxas;
  }

  public int getID() {
    return ID;
  }

  public ArrayList<TaxaDeServiço> getTaxas() {
    return taxas;
  }

  public void setFazParteDoSindicato(boolean fazParteDoSindicato) {
    this.fazParteDoSindicato = fazParteDoSindicato;
  }

  public void setIDNoSindicato(int IDNoSindicato) {

    this.IDNoSindicato = IDNoSindicato;
  }

  public String getMétodoDePagamento() {
    return métodoDePagamento;
  }

  public void setMétodoDePagamento(String métodoDePagamento) {
    this.métodoDePagamento = métodoDePagamento;
  }

  public void setAgendaDePagamento(AgendaDePagamento agendaDePagamento) {
    this.agendaDePagamento = agendaDePagamento;
  }

  public boolean lançarTaxaDeServiço(Date data, double valorDaTaxa) {

    if (this.fazParteDoSindicato) {

      TaxaDeServiço taxa = new TaxaDeServiço(data, valorDaTaxa);
      this.taxas.add(taxa);
      System.out.println("Taxa de Serviço lançada com sucesso!\n");
      return(true);
    } else {

      System.out.println("Este empregado não faz parte do sindicato!\n");
      return(false);
    }
  }

  public String getNome() {
    return nome;
  }

  @Override
  public String toString() {
    return "ID=" + ID +
      ", nome='" + nome + '\'' +
      ", endereço='" + endereço + '\'';
  }
}
