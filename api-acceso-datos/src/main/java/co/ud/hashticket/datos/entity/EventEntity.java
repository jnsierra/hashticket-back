package co.ud.hashticket.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "event")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {
    @Id

    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
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
    @Column(name = "city")
    private String city;
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
}