package co.ud.hashticket.datos.entity;

import co.ud.ud.hashticket.enumeration.StatusTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
@NamedQuery(name = "TicketEntity.getByEventIdAndPresentationId", query = "from TicketEntity ticket inner join fetch ticket.ticketPk.event as eve inner join fetch ticket.ticketPk.presentation as pr inner join fetch ticket.ticketPk.zone as zone inner join fetch ticket.ticketPk.category as cat inner join fetch ticket.ticketPk.presentation as pr  where  eve.id = :eventId and pr.id = :presentationId order by ticket.ticketPk.numberTicket ")
@NamedQuery(name = "TicketEntity.countByEventIdAndPresentationId", query = "select count(*) from TicketEntity ticket inner join ticket.ticketPk.event as eve inner join ticket.ticketPk.presentation as pr where eve.id = :eventId and pr.id = :presentationId")
@Table(name = "tickets")
public class TicketEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @EmbeddedId
    private TicketPkEntity ticketPk;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 8)
    private StatusTicket state;

    public TicketEntity(TicketPkEntity ticketPk, StatusTicket state) {
        System.out.println("Llego con parametros");
        this.ticketPk = ticketPk;
        this.state = state;
    }

    public TicketEntity() {
        System.out.println("Llego sin parametros");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketEntity that = (TicketEntity) o;

        return ticketPk.equals(that.ticketPk);
    }

    @Override
    public int hashCode() {
        return ticketPk.hashCode();
    }
}