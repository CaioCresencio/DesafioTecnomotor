package com.tecnomotor.desafio.model;


public class MontadoraInfo {

    private Veiculo veiculo;
    private Motorizacao motorizacao;

    public MontadoraInfo(){ }

    public MontadoraInfo(Veiculo veiculo, Motorizacao motorizacao){
        this.setVeiculo(veiculo);
        this.setMotorizacao(motorizacao);
    }


    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Motorizacao getMotorizacao() {
        return motorizacao;
    }

    public void setMotorizacao(Motorizacao motorizacao) {
        this.motorizacao = motorizacao;
    }
}
