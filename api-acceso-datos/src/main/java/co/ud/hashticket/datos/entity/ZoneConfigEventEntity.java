package co.ud.hashticket.datos.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "zone_config_event")
@NamedQuery(name = "ZoneConfigEventEntity.getZoneConfigByEvent"               , query = "from ZoneConfigEventEntity znCon inner join fetch znCon.configEvent as conEvent inner join fetch conEvent.zoneConfigEvents as znCnf where conEvent.event.id =  ?1 ")
@NamedQuery(name = "ZoneConfigEventEntity.getZoneConfigByEventAndPresentation", query = "from ZoneConfigEventEntity znCon inner join fetch znCon.configEvent as conEvent where conEvent.event.id =  ?1 and conEvent.presentation.id = ?2 ")
public class ZoneConfigEventEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @GeneratedValue(generator = "sequence-generator-zone-config-event")
    @GenericGenerator(
            name = "sequence-generator-zone-config-event",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "zone_config_event_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "zone_id")
    private ZoneEntity zone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "config_event_id")
    private ConfigEventEntity configEvent;
    @Column( name = "number_of_tickets")
    private Long numberOfTickets;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZoneEntity getZone() {
        return zone;
    }

    public void setZone(ZoneEntity zone) {
        this.zone = zone;
    }

    public ConfigEventEntity getConfigEvent() {
        return configEvent;
    }

    public void setConfigEvent(ConfigEventEntity configEvent) {
        this.configEvent = configEvent;
    }

    public Long getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Long numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
