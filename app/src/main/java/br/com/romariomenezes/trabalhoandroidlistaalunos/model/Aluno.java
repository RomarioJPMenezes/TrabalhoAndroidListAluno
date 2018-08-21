package br.com.romariomenezes.trabalhoandroidlistaalunos.model;

import android.widget.EditText;

import java.io.Serializable;

import br.com.romariomenezes.utils.MaskEditUtil;

/**
 * Created by Romário Menezes on 12/06/2018.
 */

public class Aluno implements Serializable {

    private String rg;
    private String cpf;
    private String nome;
    private String telefone;
    private String dataNascimento;
    private String email;
    private String senha;

    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {

        return
                "\nNOME: " + nome +"\n"+
                "CPF: " + cpf +"\n"+
                "RG: " + rg +"\n"+
                "TELEFONE: " + telefone  +"\n"+
                "DATA NASCIMENTO: " + dataNascimento  +"\n"+
                "E-MAIL: " + email+"\n"
                ;
    }
}
