package com.system.pessoas;

import com.system.pagamentos.CartãoDePonto;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alunoic on 28/07/17.
 */
public class Hourly extends Empregado {

    private double salárioHorário;
    ArrayList<CartãoDePonto> cartões = new ArrayList<>();

    public Hourly() {
    }

    public Hourly(int ID, String nome, String endereço, double salárioHorário) {
        super(ID, nome, endereço);

        this.salárioHorário = salárioHorário;
    }

    public void lançarCartãoDePonto(Date data, double horasTrabalhadas) {

        CartãoDePonto cartãoDePonto = new CartãoDePonto(data, horasTrabalhadas);
        this.cartões.add(cartãoDePonto);
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
