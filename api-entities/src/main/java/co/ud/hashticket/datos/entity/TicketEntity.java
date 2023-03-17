package co.ud.hashticket.datos.entity;

import co.ud.ud.hashticket.enumeration.StatusTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "tickets")
public class TicketEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @EmbeddedId
    private TicketPkEntity ticketPk;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 8)
    private StatusTicket state;

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