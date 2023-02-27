package co.ud.hashticket.datos.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "artist")
public class ArtistEntity {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "artist_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musicband_id")
    private MusicBandEntity musicBand;
    public ArtistEntity() {
    }
    public ArtistEntity(Long id, String name, String description, MusicBandEntity musicBand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.musicBand = musicBand;
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

    public MusicBandEntity getMusicBand() {
        return musicBand;
    }

    public void setMusicBand(MusicBandEntity musicBand) {
        this.musicBand = musicBand;
    }
}
