package co.ud.hashticket.datos.entity;

import co.ud.ud.hashticket.enumeration.EventStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "EventEntity.findByIdAndPresentation", query = """ 
                                                           from EventEntity event 
                                                          inner join fetch event.presentation as pr 
                                                          inner join fetch event.configEvents as conEv
                                                          inner join fetch event.city as cit
                                                          inner join fetch cit.department as dep
                                                          inner join fetch dep.country as coun
                                                          inner join fetch event.categoryEvent as catEve
                                                          where  event.id = :eventId 
                                                            and pr.id = :presentationId
                                                            and conEv.event.id = event.id
                                                            and conEv.presentation.id = pr.id
                                                                """)
@NamedQuery(name = "EventEntity.findByEventStatusAndAfterTodayEvent", query = """
                                                           FROM EventEntity event
                                                          WHERE event.eventStatus = :eventStatus
                                                            AND event.date > current_date
        
        """)
@Getter @Setter
@Entity
@Table(name = "event")
public class EventEntity extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @GeneratedValue(generator = "sequence-generator-event")
    @GenericGenerator(
            name = "sequence-generator-event",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "event_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "name", nullable = false, updatable = false)
    private String name;
    @Column(name="place")
    private String place;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "time")
    private String time;
    @Column(name = "minimum_age")
    private int minimumAge;
    @Column(name = "responsible")
    private String responsible;
    @Column(name = "nit")
    private String nit;
    @Column(name = "address")
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_code", referencedColumnName = "code")
    @JoinColumn(name = "department_code", referencedColumnName = "department_code")
    private CityEntity city;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private Set<PresentationEntity> presentation;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private Set<ConfigEventEntity> configEvents;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 8)
    private EventStatus eventStatus;
    @OneToMany(mappedBy = "ticketPk.event", fetch = FetchType.LAZY)
    private Set<TicketEntity> tickets;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEventEntity categoryEvent;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private Set<EventImagesEntity> eventImagesEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventEntity that = (EventEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}