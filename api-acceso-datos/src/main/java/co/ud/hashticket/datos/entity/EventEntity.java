package co.ud.hashticket.datos.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "event")
public class EventEntity implements Serializable {
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
    @Column(name="place")
    private String place;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "time")
    private String time;
    @Column(name = "category")
    private String category;
    @Column(name = "minimum_age")
    private int minimumAge;
    @Column(name = "responsible")
    private String responsible;
    @Column(name = "nit")
    private String nit;
    @Column(name = "address")
    private String address;
    @Column(name = "opening_doors")
    private String openingDoors;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "city_code", referencedColumnName = "code"),
            @JoinColumn(name = "department_code", referencedColumnName = "department_code")
    })
    private CityEntity city;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private Set<PresentationEntity> presentation;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private Set<ConfigEventEntity> configEvents;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningDoors() {
        return openingDoors;
    }

    public void setOpeningDoors(String openingDoors) {
        this.openingDoors = openingDoors;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public Set<PresentationEntity> getPresentation() {
        return presentation;
    }

    public void setPresentation(Set<PresentationEntity> presentation) {
        this.presentation = presentation;
    }

    public Set<ConfigEventEntity> getConfigEvents() {
        return configEvents;
    }

    public void setConfigEvents(Set<ConfigEventEntity> configEvents) {
        this.configEvents = configEvents;
    }
}