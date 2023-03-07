package co.ud.hashticket.datos.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "config_event_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
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
}
