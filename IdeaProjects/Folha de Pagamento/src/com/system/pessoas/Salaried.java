package com.system.pessoas;

import com.system.pessoas.Empregado;

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

    @Override
    public String toString() {
        return "Salaried{" +
                super.toString() +
                ", salárioMensal=" + salárioMensal +
                '}';
    }
}
