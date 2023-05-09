package co.ud.hashticket.datos.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "music_band")
public class MusicBandEntity implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;
    @Id
    @GeneratedValue(generator = "sequence-generator-music")
    @GenericGenerator(
            name = "sequence-generator-music",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "music_band_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id")
    private PresentationEntity presentation;
    @OneToMany(mappedBy = "musicBand")
    private Set<ArtistEntity> artists;
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

    public PresentationEntity getPresentation() {
        return presentation;
    }

    public void setPresentation(PresentationEntity presentation) {
        this.presentation = presentation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicBandEntity that = (MusicBandEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
