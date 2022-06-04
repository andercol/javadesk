/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

//import java.util.Date;

import java.sql.Date;



/**
 *
 * @author Colin
 */
public class LocacaoAtivos {
    
    private int id;
    private Date datalocacao;
    private Date datadevolucao;
    private int usuarios_usu_id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String ramal;
    private String email;
    private String departamento;
    private int idequipe;
    private String tipo;
    private String descricao;
    private String modelo;
    private String fabricante;
    private String numserie;
    private boolean locado;

    public LocacaoAtivos() {}

    public boolean isLocado() {
        return locado;
    }

    public void setLocado(boolean locado) {
        this.locado = locado;
    }

    
    
    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getIdequipe() {
        return idequipe;
    }

    public void setIdequipe(int idequipe) {
        this.idequipe = idequipe;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
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

    public String getNumserie() {
        return numserie;
    }

    public void setNumserie(String numserie) {
        this.numserie = numserie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatalocacao() {
        return datalocacao;
    }

    public void setDatalocacao(Date datalocacao) {
        this.datalocacao = datalocacao;
    }

    public Date getDatadevolucao() {
        return datadevolucao;
    }

    public void setDatadevolucao(Date datadevolucao) {
        this.datadevolucao = datadevolucao;
    }

    public int getUsuarios_usu_id() {
        return usuarios_usu_id;
    }

    public void setUsuarios_usu_id(int usuarios_usu_id) {
        this.usuarios_usu_id = usuarios_usu_id;
    }
    
    
    
    
    
}
