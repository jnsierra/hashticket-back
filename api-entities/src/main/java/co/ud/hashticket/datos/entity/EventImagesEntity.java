package co.ud.hashticket.datos.entity;

import co.ud.ud.hashticket.enumeration.TypeImages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_images")
@NamedQuery(name = "EventImagesEntity.findByEventAndTypeImages" , query = "from EventImagesEntity eveIm inner join eveIm.event as eve where eve.id = :idEvent and eveIm.typeImages = :typeImages ")
@NamedQuery(name = "EventImagesEntity.findByEvent", query = "from EventImagesEntity eveIm inner join eveIm.event as eve where eve.id = :idEvent ")
public class EventImagesEntity implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;
    @Id
    @GeneratedValue(generator = "sequence-generator-event-images")
    @GenericGenerator(
            name = "sequence-generator-event-images",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "event_images_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "description", length = 8)
    private String description;
    @Column(name = "location", length = 8)
    private String location;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_image", length = 8)
    private TypeImages typeImages;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventImagesEntity that = (EventImagesEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}