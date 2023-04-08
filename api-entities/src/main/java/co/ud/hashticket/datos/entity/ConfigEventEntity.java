package co.ud.hashticket.datos.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NamedQuery(name = "ConfigEventEntity.getByEventIdAndPresentation", query = "from ConfigEventEntity cEvent inner join fetch cEvent.event as eve inner join fetch cEvent.presentation as pr where pr.id = :idPresentation and eve.id = :idEvent")
@NamedQuery(name = "ConfigEventEntity.findByEventId", query = "from ConfigEventEntity cEvent inner join fetch cEvent.event as eve WHERE eve.id = :idEvent ")
@Entity
@Table(name = "config_event")
public class ConfigEventEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @GeneratedValue(generator = "sequence-generator-config-event")
    @GenericGenerator(
            name = "sequence-generator-config-event",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "config_event_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventEntity event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id")
    private PresentationEntity presentation;
    @Column(name = "door_opening")
    private LocalTime doorOpening;
    @Column(name = "number_of_tickets")
    private BigDecimal numberOfTickets;
    @Column(name = "number_of_tickets_sold")
    private BigDecimal numberOfTicketsSold;
    @Column(name = "event_date")
    private LocalDate eventDate;
    @OneToMany(mappedBy = "configEvent")
    private Set<ZoneConfigEventEntity> zoneConfigEvents = new HashSet<>();

    public void addZoneConfigEvents(ZoneConfigEventEntity zoneConfigEvent) {
        this.zoneConfigEvents.add(zoneConfigEvent);
        zoneConfigEvent.setConfigEvent(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigEventEntity that = (ConfigEventEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
