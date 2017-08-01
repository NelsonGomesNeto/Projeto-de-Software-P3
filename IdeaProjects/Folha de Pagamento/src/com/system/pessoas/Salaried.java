package com.system.pessoas;

import com.system.pagamentos.TaxaDeServiço;
import com.system.pessoas.Empregado;

import java.net.IDN;
import java.util.ArrayList;

/**
 * Created by alunoic on 28/07/17.
 */
public class Salaried extends Empregado {

  double salárioMensal;

  public Salaried() {
  }

  public Salaried(int ID, String nome, String endereço, double salárioMensal) {
    super(ID, nome, endereço);

    this.salárioMensal = salárioMensal;
  }

  public Salaried(int ID, String nome, String endereço, String métodoDePagamento, int IDNoSindicato, boolean fazParteDoSindicato, ArrayList<TaxaDeServiço> taxas) {
    super(ID, nome, endereço, métodoDePagamento, IDNoSindicato, fazParteDoSindicato, taxas);

  }

  public double getSalárioMensal() {
    return this.salárioMensal;
  }

  @Override
  public String toString() {
    return "Salaried{" +
      super.toString() +
      ", salárioMensal=" + salárioMensal +
      '}';
  }
}
