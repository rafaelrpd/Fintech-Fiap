package model;

public class FonteDeReceita {
    private int id;
    private String descricao;
    private double valor;
    private int usuarioId;

    // Construtor padrão
    public FonteDeReceita() {
    }

    // Construtor utilizado para inserção (3 parâmetros)
    public FonteDeReceita(String descricao, double valor, int usuarioId) {
        this.descricao = descricao;
        this.valor = valor;
        this.usuarioId = usuarioId;
    }

    // Construtor utilizado para edição (4 parâmetros)
    public FonteDeReceita(int id, String descricao, double valor, int usuarioId) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.usuarioId = usuarioId;
    }

    // Getters e Setters
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

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
