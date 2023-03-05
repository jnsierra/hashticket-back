package co.ud.hashticket.datos.entity;

import co.ud.ud.hashticket.enumeration.StatusTicket;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tickets")
public class TicketsEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventEntity event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private ZoneEntity zone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id")
    private PresentationEntity presentation;
    @Column(name = "number_ticket")
    private Long numberTicket;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 8)
    private StatusTicket state;

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public ZoneEntity getZone() {
        return zone;
    }

    public void setZone(ZoneEntity zone) {
        this.zone = zone;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public PresentationEntity getPresentation() {
        return presentation;
    }

    public void setPresentation(PresentationEntity presentation) {
        this.presentation = presentation;
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