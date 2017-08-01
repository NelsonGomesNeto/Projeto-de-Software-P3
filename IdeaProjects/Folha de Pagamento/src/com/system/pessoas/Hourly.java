package com.system.pessoas;

import com.system.pagamentos.AgendaDePagamento;
import com.system.pagamentos.CartãoDePonto;
import com.system.pagamentos.TaxaDeServiço;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alunoic on 28/07/17.
 */
public class Hourly extends Empregado {

  private double salárioHorário;
  private ArrayList<CartãoDePonto> cartões = new ArrayList<>();

  public Hourly() {
  }

  public Hourly(int ID, String nome, String endereço, double salárioHorário) {
    super(ID, nome, endereço);

    this.salárioHorário = salárioHorário;
    this.agendaDePagamento = new AgendaDePagamento("semanalmente", 5);
  }

  public Hourly(int ID, String nome, String endereço, String métodoDePagamento, int IDNoSindicato, boolean fazParteDoSindicato, ArrayList<TaxaDeServiço> taxas) {
    super(ID, nome, endereço, métodoDePagamento, IDNoSindicato, fazParteDoSindicato, taxas);

    this.agendaDePagamento = new AgendaDePagamento("semanalmente", 5);
  }

  public void lançarCartãoDePonto(Date data, double horasTrabalhadas) {

    CartãoDePonto cartãoDePonto = new CartãoDePonto(data, horasTrabalhadas);
    this.cartões.add(cartãoDePonto);
  }

  public int getQuantidadeDeCartoões() {

    return this.cartões.size();
  }

  public ArrayList<CartãoDePonto> getCartões() {

    return this.cartões;
  }

  public double getSalárioHorário() {
    return this.salárioHorário;
  }

  @Override
  public String toString() {
    return "Hourly{" +
      super.toString() +
      ", salárioHorário=" + salárioHorário +
      ", cartões=" + cartões +
      '}';
  }
}
