package com.system.pessoas;

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

    private String métodoDePagamento = "";

    private int IDNoSindicato;
    public boolean fazParteDoSindicato;
    private ArrayList<TaxaDeServiço> taxas = new ArrayList<>();

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

    public void setIDNoSindicato(int IDNoSindicato) {

        this.IDNoSindicato = IDNoSindicato;
    }

    public void setMétodoDePagamento(String métodoDePagamento) {
        this.métodoDePagamento = métodoDePagamento;
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
