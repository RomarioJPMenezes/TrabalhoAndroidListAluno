package br.com.romariomenezes.trabalhoandroidlistaalunos.model;

import java.io.Serializable;

/**
 * Created by Romário Menezes on 04/08/2018.
 */

public class User implements Serializable {

    private String email;
    private String senha;

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
}
