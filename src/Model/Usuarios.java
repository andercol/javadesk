/*
 * Clase que define os atributos dos usuários
 */
package Model;

/**
 * @author Colin
 */
public class Usuarios {
    private int id;
    private String nome;
    private String sobreNome;
    private String telefone;
    private String ramal;
    private String email;
    private String senha;
    private boolean status;
    private int perfil_perf_id;
    private int departamento_dep_id;
    private String perfil;
    private String departamento;
    

    public Usuarios() { }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
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

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPerfil_perf_id() {
        return perfil_perf_id;
    }

    public void setPerfil_perf_id(int perfil_perf_id) {
        this.perfil_perf_id = perfil_perf_id;
    }

    public int getDepartamento_dep_id() {
        return departamento_dep_id;
    }

    public void setDepartamento_dep_id(int departamento_dep_id) {
        this.departamento_dep_id = departamento_dep_id;
    }
    
    public void ativo(){
        this.setStatus(true);
    }
    
    public void inativo(){
        this.setStatus(false);
    }
}

