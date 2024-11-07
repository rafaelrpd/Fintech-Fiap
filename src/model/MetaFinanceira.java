package model;

public class MetaFinanceira {
    private int id;
    private String descricao;
    private double valorObjetivo;
    private double valorAtual;
    private int usuarioId;

    public MetaFinanceira() {
    }

    public MetaFinanceira(String descricao, double valorObjetivo) {
        this.descricao = descricao;
        this.valorObjetivo = valorObjetivo;
        this.valorAtual = 0.0;
    }

    // Métodos
    public void adicionarProgresso(double valor) {
        this.valorAtual += valor;
    }

    public double calcularProgressoPercentual() {
        if (valorObjetivo == 0) {
            return 0;
        }
        return (valorAtual / valorObjetivo) * 100;
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

    public double getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(double valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
