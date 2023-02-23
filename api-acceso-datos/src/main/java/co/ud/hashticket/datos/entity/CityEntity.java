package co.ud.hashticket.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityEntity {
    @EmbeddedId
    private CityPkEntity cityPk;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_code")
    private DepartmentEntity department;

}