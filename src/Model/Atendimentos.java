/*
 * Clase que define os atributos de atendimento
 */
package Model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Colin
 */
public class Atendimentos {
    
    private int id;
    private Timestamp data_atendimento;
    private String descricao;
    private int ocorrencia_oco_id;
    private int ususarios_usu_id;
    private String usuario;
               

    public Atendimentos() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getData_atendimento() {
        return data_atendimento;
    }

    public void setData_atendimento(Timestamp data_atendimento) {
        this.data_atendimento = data_atendimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getOcorrencia_oco_id() {
        return ocorrencia_oco_id;
    }

    public void setOcorrencia_oco_id(int ocorrencia_oco_id) {
        this.ocorrencia_oco_id = ocorrencia_oco_id;
    }

    public int getUsusarios_usu_id() {
        return ususarios_usu_id;
    }

    public void setUsusarios_usu_id(int ususarios_usu_id) {
        this.ususarios_usu_id = ususarios_usu_id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

 
    
    
}
