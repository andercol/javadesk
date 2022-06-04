/*
 * Clase que define os atributos de perfil
 */
package Model;

/**
 *
 * @author Colin
 */
public class Perfil {
    
    private int id;
    private String perfil;
    private String descricao;

    
    
    public Perfil() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
