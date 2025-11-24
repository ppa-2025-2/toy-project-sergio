package com.example.demo.Repository.Entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "criador_id")
    private User criador;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private User destinatario;
    
    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private User responsavel;

    @ManyToMany
    @JoinTable(
        name = "tickets_users", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "ticket_id")
    )
    private Set<User> observadores;

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

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCriador() {
        return criador;
    }

    public void setCriador(User criador) {
        this.criador = criador;
    }

    public User getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(User destinatario) {
        this.destinatario = destinatario;
    }

    public Set<User> getObservadores() {
        return observadores;
    }

    public void setObservadores(Set<User> observadores) {
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

    public User getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(User responsavel) {
        this.responsavel = responsavel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
