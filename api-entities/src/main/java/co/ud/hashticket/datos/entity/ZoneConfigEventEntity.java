package co.ud.hashticket.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "zone_config_event")
@NamedQuery(name = "ZoneConfigEventEntity.getZoneConfigByEvent"               , query = "from ZoneConfigEventEntity znCon inner join fetch znCon.configEvent as conEvent inner join fetch conEvent.zoneConfigEvents as znCnf where conEvent.event.id =  ?1 ")
@NamedQuery(name = "ZoneConfigEventEntity.getZoneConfigByEventAndPresentation", query = "from ZoneConfigEventEntity znCon inner join fetch znCon.configEvent as conEvent where conEvent.event.id =  ?1 and conEvent.presentation.id = ?2 ")
@NamedQuery(name = "ZoneConfigEventEntity.getByZoneAndConfigEvent", query = """
        SELECT znCon
          FROM ZoneConfigEventEntity znCon 
         inner join znCon.zone zon
         inner join znCon.configEvent conE
         WHERE zon.id = :zoneId
           AND conE.id = :configEventId
        """)
@NamedQuery(name = "ZoneConfigEventEntity.getByConfigEventId", query = """
        SELECT znCon
          FROM ZoneConfigEventEntity znCon
    INNER JOIN znCon.configEvent conEv
         WHERE conEv.id = :configEventId
        """)
@NamedQuery(name = "ZoneConfigEventEntity.updateCreateTickets", query = """
        UPDATE ZoneConfigEventEntity znCfEv
           SET createTickets = :createTickets
         WHERE znCfEv.id = :id
        """)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Column( name = "number_of_tickets", updatable = false)
    private Long numberOfTickets;
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "number_of_tickets_sold")
    private Long numberOfTicketsSold;
    @Column(name = "create_tickets")
    private Boolean createTickets;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZoneConfigEventEntity that = (ZoneConfigEventEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}