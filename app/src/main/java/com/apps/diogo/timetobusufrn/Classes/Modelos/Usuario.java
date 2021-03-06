package com.apps.diogo.timetobusufrn.Classes.Modelos;

/**
 * Created by Diogo on 30/09/2017.
 */

import java.io.Serializable;

public class Usuario implements Serializable
{
    public static final String USER_INFO = "USER_INFO";
    
    private int matricula;
    private String senha;
    private String nome;
    private byte[] foto;
    
    public Usuario(int matricula, String senha, String nome, byte[] foto)
    {
        super();
        this.matricula = matricula;
        this.senha = senha;
        this.nome = nome;
        this.foto = foto;
    }
    
    public Usuario(int matricula, String senha, String nome)
    {
        super();
        this.matricula = matricula;
        this.senha = senha;
        this.nome = nome;
        this.foto = null;
    }
    
    public Usuario( int matricula, String nome)
    {
        super();
        this.matricula = matricula;
        this.senha = "";
        this.nome = nome;
        this.foto = null;
    }
    
    public Usuario( int matricula, String nome, byte[] foto )
    {
        super();
        this.matricula = matricula;
        this.senha = "";
        this.nome = nome;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }
    
    public String getSMatricula()
    {
        return getMatricula() + "";
    }
    
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public byte[] getFoto()
    {
        return foto;
    }
    
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
    @Override
    public boolean equals(Object o)
    {
        return ((Usuario)o).matricula == this.matricula;
    }
}
