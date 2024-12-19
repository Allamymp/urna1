package org.ifpe.dws3.prova1;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Candidatos {

    private Integer id;

    private String nome;

    private Integer numero;

    private Integer votos = 0;

    private Integer partido_num;



    public void addVotos() {
        this.votos++;
    }


    public Candidatos(Integer id, String nome, Integer numero, Integer votos, Integer partido) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.votos = votos;
        this.partido_num = partido;
    }
    public Candidatos(String nome, Integer numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public Candidatos() {
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

    public Integer getPartido_num() {
        return partido_num;
    }

    public void setPartido_num(Integer partido_num) {
        this.partido_num = partido_num;
    }
}
