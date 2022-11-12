package com.mycompany.sistemaclinica.view;

import com.mycompany.sistemaclinica.core.DAO.AtendimentoDAO;
import com.mycompany.sistemaclinica.core.entities.Atendimento;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.schedule.ScheduleEntryMoveEvent;
import org.primefaces.event.schedule.ScheduleEntryResizeEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

@Named
@ViewScoped
public class AtendimentoBean implements Serializable {
    //constructor

    public AtendimentoBean() {

    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public Atendimento getAtendimentoSelecionado() {
        return atendimentoSelecionado;
    }

    public void setAtendimentoSelecionado(Atendimento atendimentoSelecionado) {
        this.atendimentoSelecionado = atendimentoSelecionado;
    }

    public List<Atendimento> getAtendimentosSelecionados() {
        return atendimentosSelecionados;
    }

    public void setAtendimentosSelecionados(List<Atendimento> atendimentosSelecionados) {
        this.atendimentosSelecionados = atendimentosSelecionados;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public AtendimentoEvent getEvent() {
        return event;
    }

//properties do datatable
    public void setEvent(AtendimentoEvent event) {
        this.event = event;
    }
    private List<Atendimento> atendimentos;
    private Atendimento atendimentoSelecionado;
    private List<Atendimento> atendimentosSelecionados;
//properties do schedule
    private ScheduleModel eventModel;
    private AtendimentoEvent event = new AtendimentoEvent();

//postConstruct method
    @PostConstruct
    public void init() {
        //schedule scope
        eventModel = new DefaultScheduleModel();
        List<Atendimento> lista = new AtendimentoDAO().listarTodos();
        for (Atendimento atendimentoIndex : lista) {
            event.setEvent(atendimentoIndex);
            eventModel.addEvent(event);
            event = new AtendimentoEvent();
        }
        //datatable scope
        this.atendimentos = lista;
        this.atendimentoSelecionado = new Atendimento();
    }

    //Schedule methods
    public void deleteEvent() {
        new AtendimentoDAO().deletar(event.getEvent().getAtendimento_id());
        eventModel.deleteEvent(event);
        System.out.println(event.getId());
        event = new AtendimentoEvent();
    }

    public void addEvent() {
        if (event.getId() == null) {
            eventModel.addEvent(event);
            new AtendimentoDAO().salvar(event.getEvent());
        } else {
            eventModel.updateEvent(event);
            new AtendimentoDAO().alterar(event.getEvent());
        }

        event = new AtendimentoEvent();

    }

    public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
        event = new AtendimentoEvent(selectEvent.getObject());
    }

    public void onEventSelect(SelectEvent<AtendimentoEvent> selectEvent) {
        event = selectEvent.getObject();
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Delta:" + event.getDeltaAsDuration());
        this.event = (AtendimentoEvent) event.getScheduleEvent();
        addEvent();
        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Start-Delta:" + event.getDeltaStartAsDuration() + ", End-Delta: " + event.getDeltaEndAsDuration());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //datatable methods
    
    public void mixedPaciente(){
        if(this.atendimentoSelecionado.getAtendimento_id()== 0){
            salvarAtendimento();
        }else{
            alterarAtendimento();
        }
    }
    public void salvarAtendimento() {
  
        this.atendimentos.add(atendimentoSelecionado);
        new AtendimentoDAO().salvar(atendimentoSelecionado);
        this.atendimentoSelecionado = new Atendimento();
    }
    
    public void alterarAtendimento(){
        new AtendimentoDAO().alterar(atendimentoSelecionado);
        this.atendimentoSelecionado = new Atendimento();
    }
     public void deletarAtendimento(){
          new AtendimentoDAO().deletar(this.atendimentoSelecionado.getAtendimento_id());
      }
     
       public String getDeleteButtonMessage() {
        if (hasSelectedAtendimentos()) {
            int size = this.atendimentosSelecionados.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }
        public boolean hasSelectedAtendimentos() {
        return this.atendimentosSelecionados != null && !this.atendimentosSelecionados.isEmpty();
    }
        public void openNew(){
            this.atendimentoSelecionado = new Atendimento();
        }
        
        public List<Atendimento> listarTop(int status){
            List<Atendimento> lista = new AtendimentoDAO().listTop(status);
            return lista;
        }
}
