package com.mycompany.sistemaclinica.view;

import com.mycompany.sistemaclinica.core.entities.Atendimento;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.primefaces.model.DefaultScheduleEvent;


public class AtendimentoEvent extends DefaultScheduleEvent implements Serializable {
 
//constructor
public AtendimentoEvent(){
    
}    
       public AtendimentoEvent(LocalDateTime startDate) {
        this.setStartDate(startDate);
        this.setEndDate(startDate);
    } 
//properties
    private int atendimento_id;
    private String disc;
    private String data;
    private String createdAt;
    private int status;
    private String nome;
    
//getters and setters
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
       
 //Schedule events methods
    public Atendimento getEvent(){
        Atendimento atendimento = new Atendimento();
        atendimento.setDisc(this.disc);
        atendimento.setData(this.getStartDate());
        atendimento.setCreatedAt(this.createdAt);
        atendimento.setStatus(this.status);
        atendimento.setAtendimento_id(this.atendimento_id);
        atendimento.setNome(this.nome);
        return atendimento;
    } 
    public void setEvent(Atendimento atendimento){
        this.data = atendimento.getData().toString();
        this.atendimento_id = atendimento.getAtendimento_id();
        this.createdAt = atendimento.getCreatedAt();
        this.disc = atendimento.getDisc();
        this.nome = atendimento.getNome();
        this.status = atendimento.getStatus();
        this.setStartDate(atendimento.getData());
        this.setEndDate(atendimento.getData());
        this.setTitle("Atendimento de "+atendimento.getNome());
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
}
