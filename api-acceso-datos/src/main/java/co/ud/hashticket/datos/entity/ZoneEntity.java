package co.ud.hashticket.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public Set<ZoneConfigEventEntity> getZoneConfigEvents() {
        return zoneConfigEvents;
    }

    public void setZoneConfigEvents(Set<ZoneConfigEventEntity> zoneConfigEvents) {
        this.zoneConfigEvents = zoneConfigEvents;
    }

    public Set<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    public void addZoneConfigEvents(ZoneConfigEventEntity zoneConfigEvent) {
        this.zoneConfigEvents.add(zoneConfigEvent);
        zoneConfigEvent.setZone(this);
    }
}
