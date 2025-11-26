package com.example.demo.Repository.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tickets")
public class Ticket {

    public enum Status {
        PENDENTE,
        ANDAMENTO,
        CONCLUIDO,
        CANCELADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer criador_id;

    @Column
    private Integer destinatario_id;

    @Column
    private Integer responsavel_id;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketUser> observadores = new ArrayList<>();

    @Column(nullable = false, length = 255)
    private String objeto;
    
    @Column(nullable = false, length = 255)
    private String acao;

    @Column(nullable = false, length = 255)
    private String detalhes;

    @Column(nullable = false, length = 255)
    private String local;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 255)
    private Status status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCriadorId() {
        return criador_id;
    }

    public void setCriadorId(Integer criador_id) {
        this.criador_id = criador_id;
    }

    public Integer getDestinatarioId() {
        return destinatario_id;
    }

    public void setDestinatarioId(Integer destinatario_id) {
        this.destinatario_id = destinatario_id;
    }

    public List<TicketUser> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<TicketUser> observadores) {
        this.observadores = observadores;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getResponsavelId() {
        return responsavel_id;
    }

    public void setResponsavelId(Integer responsavel_id) {
        this.responsavel_id = responsavel_id;
    }
}
