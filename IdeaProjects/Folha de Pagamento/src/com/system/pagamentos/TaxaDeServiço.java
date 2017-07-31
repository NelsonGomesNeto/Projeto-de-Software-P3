package com.system.pagamentos;

import java.util.Date;

/**
 * Created by alunoic on 31/07/17.
 */
public class TaxaDeServiço {

    private Date data;
    private double valorDaTaxa;

    public TaxaDeServiço() {
    }

    public TaxaDeServiço(Date data, double valorDaTaxa) {

        this.data = data;
        this.valorDaTaxa = valorDaTaxa;
    }

    public Date getData() {
        return data;
    }

    public double getValorDaTaxa() {
        return valorDaTaxa;
    }

    @Override
    public String toString() {
        return "TaxaDeServiço{" +
                "data=" + data +
                ", valorDaTaxa=" + valorDaTaxa +
                '}';
    }
}
