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
@NamedQuery(name = "ZoneEntity.findByCategory", query = "from ZoneEntity zo inner join fetch zo.category ca where ca.id = :categoryId  ")
@Entity
@Table(name = "zone")
public class ZoneEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @GeneratedValue(generator = "sequence-generator-zone")
    @GenericGenerator(
            name = "sequence-generator-zone",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "zone_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description", nullable = false, unique = true)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @OneToMany(mappedBy = "zone", fetch = FetchType.LAZY)
    private Set<ZoneConfigEventEntity> zoneConfigEvents = new HashSet<>();
    @OneToMany(mappedBy = "ticketPk.zone", fetch = FetchType.LAZY)
    private Set<TicketEntity> tickets = new HashSet<>();
    public void addZoneConfigEvents(ZoneConfigEventEntity zoneConfigEvent) {
        this.zoneConfigEvents.add(zoneConfigEvent);
        zoneConfigEvent.setZone(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZoneEntity that = (ZoneEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
