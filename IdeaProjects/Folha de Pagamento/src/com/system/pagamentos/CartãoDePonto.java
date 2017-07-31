package com.system.pagamentos;

import java.util.Date;

/**
 * Created by alunoic on 28/07/17.
 */
public class CartãoDePonto {

    private Date data;
    private double horasTrabalhadas;

    public CartãoDePonto(Date data, double horasTrabalhadas) {

        this.data = data;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Date getData() {
        return this.data;
    }

    public double getHorasTrabalhadas() {
        return this.horasTrabalhadas;
    }

    @Override
    public String toString() {
        return "CartãoDePonto{" +
                "data=" + data +
                ", horasTrabalhadas=" + horasTrabalhadas +
                '}';
    }
}
