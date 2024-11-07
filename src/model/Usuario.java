package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;

    private List<FonteDeReceita> receitas;
    private List<Gasto> gastos;
    private List<Divida> dividas;
    private List<Investimento> investimentos;
    private List<MetaFinanceira> metas;

    public Usuario() {
        receitas = new ArrayList<>();
        gastos = new ArrayList<>();
        dividas = new ArrayList<>();
        investimentos = new ArrayList<>();
        metas = new ArrayList<>();
    }

    public Usuario(String nome, String email, String senha) {
        this();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void adicionarReceita(FonteDeReceita receita) {
        receitas.add(receita);
    }

    public void adicionarGasto(Gasto gasto) {
        gastos.add(gasto);
    }

    public void adicionarDivida(Divida divida) {
        dividas.add(divida);
    }

    public void adicionarInvestimento(Investimento investimento) {
        investimentos.add(investimento);
    }

    public void adicionarMeta(MetaFinanceira meta) {
        metas.add(meta);
    }

    // Atributo id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Atributo nome
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Atributo email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Atributo senha
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<FonteDeReceita> getReceitas() {
        return receitas;
    }
    public void setReceitas(List<FonteDeReceita> receitas) {
        this.receitas = receitas;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }
    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public List<Divida> getDividas() {
        return dividas;
    }
    public void setDividas(List<Divida> dividas) {
        this.dividas = dividas;
    }

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }
    public void setInvestimentos(List<Investimento> investimentos) {
        this.investimentos = investimentos;
    }

    public List<MetaFinanceira> getMetas() {
        return metas;
    }
    public void setMetas(List<MetaFinanceira> metas) {
        this.metas = metas;
    }

    public void removerReceita(FonteDeReceita receita) {
        receitas.remove(receita);
    }

    public void removerGasto(Gasto gasto) {
        gastos.remove(gasto);
    }

    public void removerDivida(Divida divida) {
        dividas.remove(divida);
    }

    public void removerInvestimento(Investimento investimento) {
        investimentos.remove(investimento);
    }

    public void removerMeta(MetaFinanceira meta) {
        metas.remove(meta);
    }
}
