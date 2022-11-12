package com.mycompany.sistemaclinica.core.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Atendimento implements Serializable {

//constructor
    public Atendimento() {
    }
//properties

    private int atendimento_id;
    private String disc;
    private LocalDateTime Data;
    private String createdAt;
    private int status;
    private String nome;

//getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

        public String getStatusString() {

        switch (this.status) {
            case 0:
                return "marcado";
            case 1:
                return "concluido";
            case 2:
                return "presente";
            case 3:
                return "faltou";
        }
        return null;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getAtendimento_id() {
        return atendimento_id;
    }

    public void setAtendimento_id(int atendimento_id) {
        this.atendimento_id = atendimento_id;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public LocalDateTime getData() {
        return Data;
    }

    public void setData(LocalDateTime Data) {
        this.Data = Data;
    }

}
