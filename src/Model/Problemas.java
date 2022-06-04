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
public class Problemas {
    
    private int id;
    private String nome;
    private int cat_problemas_cat_prob_id;
    private String categoria;

    public Problemas() {}

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public int getCat_problemas_cat_prob_id() {
        return cat_problemas_cat_prob_id;
    }

    public void setCat_problemas_cat_prob_id(int cat_problemas_cat_prob_id) {
        this.cat_problemas_cat_prob_id = cat_problemas_cat_prob_id;
    }
    
    
    
    
    
}
