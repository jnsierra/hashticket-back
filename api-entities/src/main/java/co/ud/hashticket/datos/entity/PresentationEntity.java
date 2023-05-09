package co.ud.hashticket.datos.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "PresentationEntity.findByEvent", query = "from PresentationEntity pre inner join fetch pre.event as eve  WHERE eve.id = :idEvent ")
@Getter @Setter
@Entity
@Table(name = "presentation")
public class PresentationEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @GeneratedValue(generator = "sequence-generator-presentation")
    @GenericGenerator(
            name = "sequence-generator-presentation",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "presentation_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventEntity event;
    @OneToMany(mappedBy = "presentation", fetch = FetchType.LAZY)
    private Set<MusicBandEntity> musicBand = new HashSet<>();
    @OneToMany(mappedBy = "ticketPk.presentation", fetch = FetchType.LAZY)
    private Set<TicketEntity> tickets = new HashSet<>();
    @OneToMany(mappedBy = "presentation", fetch = FetchType.LAZY)
    private Set<ConfigEventEntity> configEvents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PresentationEntity entity = (PresentationEntity) o;

        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}