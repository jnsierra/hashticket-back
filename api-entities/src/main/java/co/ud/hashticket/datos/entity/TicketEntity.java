package co.ud.hashticket.datos.entity;

import co.ud.ud.hashticket.enumeration.StatusTicket;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
@NamedQuery(name = "TicketEntity.getByEventIdAndPresentationId", query = """
            from TicketEntity ticket 
inner join fetch ticket.ticketPk.event as eve 
inner join fetch ticket.ticketPk.presentation as pr 
inner join fetch ticket.ticketPk.zone as zone 
inner join fetch ticket.ticketPk.category as cat 
           where eve.id = :eventId 
             and pr.id = :presentationId 
        order by ticket.ticketPk.numberTicket 
            """)
@NamedQuery(name = "TicketEntity.countByEventIdAndPresentationId", query = "select count(*) from TicketEntity ticket inner join ticket.ticketPk.event as eve inner join ticket.ticketPk.presentation as pr where eve.id = :eventId and pr.id = :presentationId")
@NamedQuery(name = "TicketEntity.updateState", query = """
        Update TicketEntity tic
           set state = :state ,
           confirmationNumber = :confirmationNumber , 
           userEmail = :user
         where tic.ticketPk.event.id = :eventId
           and tic.ticketPk.zone.id = :zoneId
           and tic.ticketPk.category.id = :categoryId
           and tic.ticketPk.presentation.id = :presentationId
           and tic.ticketPk.numberTicket = :numberTicket
        """)
@NamedQuery(name = "TicketEntity.getByEventAndPresentationAndZoneAndCategory", query = """
        SELECT ticket
        FROM TicketEntity ticket
        inner join fetch ticket.ticketPk.event as eve
        inner join fetch ticket.ticketPk.presentation as pr
        inner join fetch ticket.ticketPk.zone as zone 
        inner join fetch ticket.ticketPk.category as cat 
        WHERE eve.id = :eventId 
          AND pr.id = :presentationId
          AND zone.id = :zoneId
          AND cat.id = :categoryId
          AND ticket.state = 'CREATED'           
       ORDER BY ticket.ticketPk.numberTicket 
        """)
@NamedQuery(name = "TicketEntity.getByEmailAndEventAndPresentation", query = """
        SELECT ticket
          FROM TicketEntity ticket
         INNER JOIN ticket.ticketPk.presentation  pre
         INNER JOIN ticket.ticketPk.event ev
         WHERE ticket.userEmail = :email
           AND ev.id = :eventId
           AND pre.id = :presentationId
           AND pre.id = :presentationId
        """)
@Table(name = "tickets")
public class TicketEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @EmbeddedId
    private TicketPkEntity ticketPk;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 8)
    private StatusTicket state;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "confirmation")
    private String confirmationNumber;

    public TicketEntity(TicketPkEntity ticketPk, StatusTicket state) {
        this.ticketPk = ticketPk;
        this.state = state;
    }

    public TicketEntity() {
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