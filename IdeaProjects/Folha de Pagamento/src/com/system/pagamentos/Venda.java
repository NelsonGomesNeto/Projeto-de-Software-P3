package com.system.pagamentos;

import java.util.Date;

/**
 * Created by alunoic on 31/07/17.
 */
public class Venda {

    private Date data;
    private double valorDaVenda;

    public Venda() {
    }

    public Venda(Date data, double valorDaVenda) {

        this.data = data;
        this.valorDaVenda = valorDaVenda;
    }

    public Date getData() {
        return this.data;
    }

    public double getValorDaVenda() {
        return this.valorDaVenda;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "data=" + data +
                ", valorDaVenda=" + valorDaVenda +
                '}';
    }
}
