/*
 * Clase que define os atributos da ocorrencia
 */
package Model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Colin
 */
public class Ocorrencia {
    
    private int id;
    private String descricao;
    private Timestamp dataabertura;
    private Timestamp datafechamento;
    private int usuario_usu_id;
    private int status_stat_id;
    private int problemas_prob_id;
    private int ativos_atv_id;
    private int id_atendente;
    private String usuario;
    private String tecnico;
    private String catproblema;
    private String problema;
    private String status;

    public Ocorrencia() {}

    public int getId_atendente() {
        return id_atendente;
    }

    public void setId_atendente(int id_atendente) {
        this.id_atendente = id_atendente;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getCatproblema() {
        return catproblema;
    }

    public void setCatproblema(String catproblema) {
        this.catproblema = catproblema;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Timestamp getDataabertura() {
        return dataabertura;
    }

    public void setDataabertura(Timestamp dataabertura) {
        this.dataabertura = dataabertura;
    }

    public Timestamp getDatafechamento() {
        return datafechamento;
    }

    public void setDatafechamento(Timestamp datafechamento) {
        this.datafechamento = datafechamento;
    }



    public int getUsuario_usu_id() {
        return usuario_usu_id;
    }

    public void setUsuario_usu_id(int usuario_usu_id) {
        this.usuario_usu_id = usuario_usu_id;
    }

    public int getStatus_stat_id() {
        return status_stat_id;
    }

    public void setStatus_stat_id(int status_stat_id) {
        this.status_stat_id = status_stat_id;
    }

    public int getProblemas_prob_id() {
        return problemas_prob_id;
    }

    public void setProblemas_prob_id(int problemas_prob_id) {
        this.problemas_prob_id = problemas_prob_id;
    }

    public int getAtivos_atv_id() {
        return ativos_atv_id;
    }

    public void setAtivos_atv_id(int ativos_atv_id) {
        this.ativos_atv_id = ativos_atv_id;
    }
    
    
    
    
}
