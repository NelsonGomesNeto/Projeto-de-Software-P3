package com.system.pessoas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by alunoic on 28/07/17.
 */
public class Empregado {

    public int ID;
    public String nome;
    private String endereço;

    public Empregado() {
    }

    public Empregado(int ID, String nome, String endereço) {

        this.ID = ID;
        this.nome = nome;
        this.endereço = endereço;
    }

    public int getID() {
        return ID;
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
