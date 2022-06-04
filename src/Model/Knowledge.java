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
public class Knowledge {
    
    private int id;
    private String codigo_erro;
    private String descricao;
    private String motivo;
    private String resolucao;
    private int usuarios_usu_id;
    private int problemas_prob_id;

    public Knowledge() {}

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo_erro() {
        return codigo_erro;
    }

    public void setCodigo_erro(String codigo_erro) {
        this.codigo_erro = codigo_erro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    public int getUsuarios_usu_id() {
        return usuarios_usu_id;
    }

    public void setUsuarios_usu_id(int usuarios_usu_id) {
        this.usuarios_usu_id = usuarios_usu_id;
    }

    public int getProblemas_prob_id() {
        return problemas_prob_id;
    }

    public void setProblemas_prob_id(int problemas_prob_id) {
        this.problemas_prob_id = problemas_prob_id;
    }
    
    
    
    
}
