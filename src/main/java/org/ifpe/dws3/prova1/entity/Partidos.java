package org.ifpe.dws3.prova1.entity;


import java.util.ArrayList;
import java.util.List;



public class Partidos {

    private Integer id;

    private String nome;


    private Integer numero;

    private List<Candidatos> candidatos = new ArrayList<>();


    private Integer votos;

    private String sigla;


    protected Partidos() {
    }

    public Partidos(Integer id, String nome, Integer numero, List<Candidatos> candidatos, Integer votos, String sigla) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.candidatos = candidatos;
        this.votos = votos;
        this.sigla = sigla;
    }

    public Partidos(String nome, Integer numero, List<Candidatos> candidatos, Integer votos, String sigla) {
        this.nome = nome;
        this.numero = numero;
        this.candidatos = candidatos;
        this.votos = votos;
        this.sigla = sigla;
    }

    public Partidos(String nome, Integer numero, Integer votos, String sigla) {
        this.nome = nome;
        this.numero = numero;
        this.votos = votos;
        this.sigla = sigla;
    }

    public void removerCandidato(Candidatos candidatos) {
        this.candidatos.remove(candidatos);
    }

    public void addCandidato(Candidatos candidatos) {
        this.candidatos.add(candidatos);
    }

    public void addVoto() {
        this.votos++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Candidatos> getCandidatos() {
        return candidatos;
    }
}


