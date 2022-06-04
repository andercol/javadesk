/*
 * Clase que define os atributos de departamento
 */
package Model;

/**
 *
 * @author Colin
 */
public class Departamento {
    
    private int id;
    private String nome;
    private int usuarios_usu_id;
    private String nomeresp;
    private String emailresp;
    

    public Departamento() {}

    public String getNomeresp() {
        return nomeresp;
    }

    public void setNomeresp(String nomeresp) {
        this.nomeresp = nomeresp;
    }

    public String getEmailresp() {
        return emailresp;
    }

    public void setEmailresp(String emailresp) {
        this.emailresp = emailresp;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getUsuarios_usu_id() {
        return usuarios_usu_id;
    }

    public void setUsuarios_usu_id(int usuarios_usu_id) {
        this.usuarios_usu_id = usuarios_usu_id;
    }
    
    
}
