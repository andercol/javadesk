/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Classe para criar uma sessão do usuário
 * @author Colin
 *
 * Modo de utilizar
 * Sessao sessao = Sessao.getInstance(); //pega uma sessão
 * sessao.setUsersessao(usuario); //passa o usuario a ser instanciado no login
 * quando quiser saber o usuáio logado:
 * Sessao.getInstance().getUsersessao();
 */
 
 
public class Sessao {
    private static Sessao instance = null;
    private Usuarios usersessao;
    
	//construtor padrão
    private Sessao(){}

    public Usuarios getUsersessao() {
        return usersessao;
    }
	
	//seta o usuário para a sessão
    public void setUsersessao(Usuarios usersessao) {
        this.usersessao = usersessao;
    }
    
	// verifica se a sessão já foi instanciada
    public static Sessao getInstance(){
        if(instance == null){
            instance = new Sessao();
        }
        return instance;
    }
}


