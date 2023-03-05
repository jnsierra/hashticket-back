package co.ud.hashticket.datos.entity;

import co.ud.ud.hashticket.enumeration.StatusTicket;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tickets")
public class TicketEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @EmbeddedId
    private TicketPkEntity ticketPk;
    @Column(name = "number_ticket")
    private Long numberTicket;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 8)
    private StatusTicket state;

    public TicketEntity() {
    }

    public TicketEntity(TicketPkEntity ticketPk, Long numberTicket, StatusTicket state) {
        this.ticketPk = ticketPk;
        this.numberTicket = numberTicket;
        this.state = state;
    }

    public TicketPkEntity getTicketPk() {
        return ticketPk;
    }

    public void setTicketPk(TicketPkEntity ticketPk) {
        this.ticketPk = ticketPk;
    }

    public Long getNumberTicket() {
        return numberTicket;
    }

    public void setNumberTicket(Long numberTicket) {
        this.numberTicket = numberTicket;
    }

    public StatusTicket getState() {
        return state;
    }

    public void setState(StatusTicket state) {
        this.state = state;
    }
}