package co.ud.hashticket.datos.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@NamedQuery(name = "CategoryEntity.getByEventAndPresentation", query = """
         SELECT cat
          FROM CategoryEntity cat
         inner join cat.zones zon
         inner join zon.zoneConfigEvents zonCon
         inner join zonCon.configEvent confEvent
         where confEvent.event.id = :eventId
           and confEvent.presentation.id = :presentationId         
        """)
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
    @OneToMany(mappedBy = "ticketPk.category", fetch = FetchType.LAZY)
    private Set<TicketEntity> tickets = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
