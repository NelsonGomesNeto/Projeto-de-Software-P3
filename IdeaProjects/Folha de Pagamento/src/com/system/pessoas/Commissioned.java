package com.system.pessoas;

import com.system.pagamentos.Venda;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alunoic on 28/07/17.
 */
public class Commissioned extends Empregado {

    private double taxaDeComissão;
    private ArrayList<Venda> vendas = new ArrayList<>();

    public Commissioned(int ID, String nome, String endereço) {
        super(ID, nome, endereço);
    }

    public Commissioned(int ID, String nome, String endereço, double taxaDeComissão) {
        super(ID, nome, endereço);

        this.taxaDeComissão = taxaDeComissão;
    }

    public void lançarResultadoDeVenda(Date data, double valorDaVenda) {

        Venda cartãoDePonto = new Venda(data, valorDaVenda);
        this.vendas.add(cartãoDePonto);
    }

    public int getQuantidadeDeVendas() {

        return this.vendas.size();
    }

    public ArrayList<Venda> getVendas() {
        return this.vendas;
    }

    @Override
    public String toString() {
        return "Commissioned{" +
                super.toString() +
                ", taxaDeComissão=" + taxaDeComissão +
                ", vendas=" + vendas +
                '}';
    }
}
