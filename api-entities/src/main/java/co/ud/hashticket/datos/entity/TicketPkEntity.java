package co.ud.hashticket.datos.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TicketPkEntity implements Serializable {
    private static final long serialVersionUID = 1234568L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", updatable = false)
    private EventEntity event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", updatable = false)
    private ZoneEntity zone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", updatable = false)
    private CategoryEntity category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id", updatable = false)
    private PresentationEntity presentation;
    @Column(name = "number_ticket", updatable = false)
    private Long numberTicket;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketPkEntity that = (TicketPkEntity) o;

        if (!event.equals(that.event)) return false;
        if (!zone.equals(that.zone)) return false;
        if (!category.equals(that.category)) return false;
        if (!presentation.equals(that.presentation)) return false;
        return numberTicket.equals(that.numberTicket);
    }

    @Override
    public int hashCode() {
        int result = event.hashCode();
        result = 31 * result + zone.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + presentation.hashCode();
        result = 31 * result + numberTicket.hashCode();
        return result;
    }
}
