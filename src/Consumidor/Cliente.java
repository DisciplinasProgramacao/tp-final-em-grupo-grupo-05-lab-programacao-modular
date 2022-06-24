package Consumidor;

import java.io.Serializable;

/*
 * Classe que representa a pessoa que realiza pedidos no restaurante
 */
public class Cliente implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nome;
    private String cpf;
    /*construtor*/
    public Cliente(String nome, String cpf){
        this.setNome(nome);
        this.setCpf(cpf);
    }
    /*getters e setters*/
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}