/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Colin
 */
public class Ativos {
    
    private int id;
    private String tipo;
    private String descricao;
    private String modelo;
    private String fabricante;
    private String num_serie;
    private String nf;
    private String cnpjforn;
    private String fornecedor;
    private String locado;
    private int fornecedor_for_id;
    private String fornecedor_for_cnpj;
    private int locacao_ativos_loc_id;

    public Ativos() {}

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCnpjforn() {
        return cnpjforn;
    }

    public void setCnpjforn(String cnpjforn) {
        this.cnpjforn = cnpjforn;
    }

    public String getLocado() {
        return locado;
    }

    public void setLocado(String locado) {
        this.locado = locado;
    }

    public int getFornecedor_for_id() {
        return fornecedor_for_id;
    }

    public void setFornecedor_for_id(int fornecedor_for_id) {
        this.fornecedor_for_id = fornecedor_for_id;
    }

    public int getLocacao_ativos_loc_id() {
        return locacao_ativos_loc_id;
    }

    public void setLocacao_ativos_loc_id(int locacao_ativos_loc_id) {
        this.locacao_ativos_loc_id = locacao_ativos_loc_id;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(String num_serie) {
        this.num_serie = num_serie;
    }

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public String getFornecedor_for_cnpj() {
        return fornecedor_for_cnpj;
    }

    public void setFornecedor_for_cnpj(String fornecedor_for_cnpj) {
        this.fornecedor_for_cnpj = fornecedor_for_cnpj;
    }

   
   
    
    
    
}
