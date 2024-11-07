package model;

public class Divida {
    private int id;
    private String descricao;
    private double valor;
    private double taxaDeJuros;
    private int mesesParaPagar;
    private int usuarioId;

    public Divida() {
    }

    public Divida(String descricao, double valor, double taxaDeJuros, int mesesParaPagar) {
        this.descricao = descricao;
        this.valor = valor;
        this.taxaDeJuros = taxaDeJuros;
        this.mesesParaPagar = mesesParaPagar;
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

    public double getTaxaDeJuros() {
        return taxaDeJuros;
    }

    public void setTaxaDeJuros(double taxaDeJuros) {
        this.taxaDeJuros = taxaDeJuros;
    }

    public int getMesesParaPagar() {
        return mesesParaPagar;
    }

    public void setMesesParaPagar(int mesesParaPagar) {
        this.mesesParaPagar = mesesParaPagar;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
