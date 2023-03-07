package co.ud.hashticket.datos.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}