package co.ud.hashticket.datos.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @GeneratedValue(generator = "sequence-generator-category")
    @GenericGenerator(
            name = "sequence-generator-category",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "category_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description", nullable = false, unique = true)
    private String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<ZoneEntity> zones = new HashSet<>();
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<TicketsEntity> tickets = new HashSet<>();
    public CategoryEntity() {
    }
    public CategoryEntity(Long id, String name, String description, Set<ZoneEntity> zones) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.zones = zones;
    }

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

    public Set<ZoneEntity> getZones() {
        return zones;
    }

    public void setZones(Set<ZoneEntity> zones) {
        this.zones = zones;
    }

    public Set<TicketsEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketsEntity> tickets) {
        this.tickets = tickets;
    }
}
