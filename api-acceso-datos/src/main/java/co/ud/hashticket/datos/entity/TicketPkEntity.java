package co.ud.hashticket.datos.entity;

import lombok.Builder;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Builder
@Embeddable
public class TicketPkEntity implements Serializable {
    private static final long serialVersionUID = 1234568L;

    public TicketPkEntity() {
    }

    public TicketPkEntity(EventEntity event, ZoneEntity zone, CategoryEntity category, PresentationEntity presentation) {
        this.event = event;
        this.zone = zone;
        this.category = category;
        this.presentation = presentation;
    }

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
}
