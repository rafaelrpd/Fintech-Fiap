package model;

public class Investimento {
    private int id;
    private String descricao;
    private double valor;
    private double rendimentoMensal;
    private int usuarioId;

    public Investimento() {

    }

    public Investimento(String descricao, double valor, double rendimentoMensal) {
        this.descricao = descricao;
        this.valor = valor;
        this.rendimentoMensal = rendimentoMensal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getRendimentoMensal() {
        return rendimentoMensal;
    }

    public void setRendimentoMensal(double rendimentoMensal) {
        this.rendimentoMensal = rendimentoMensal;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
