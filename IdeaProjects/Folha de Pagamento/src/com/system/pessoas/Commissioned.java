package com.system.pessoas;

/**
 * Created by alunoic on 28/07/17.
 */
public class Commissioned extends Empregado {

    double taxaDeComissão;

    public Commissioned() {
    }

    public Commissioned(int ID, String nome, String endereço, double taxaDeComissão) {
        super(ID, nome, endereço);

        this.taxaDeComissão = taxaDeComissão;
    }

    @Override
    public String toString() {
        return "Commissioned{" +
                super.toString() +
                ", taxaDeComissão=" + taxaDeComissão +
                '}';
    }
}
