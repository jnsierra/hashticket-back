package co.ud.hashticket.datos.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "city")
@NamedQuery(name="CityEntity.findByDepartment", query = "from CityEntity city inner join fetch city.department as dep where dep.code = ?1 ")
public class CityEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @EmbeddedId
    private CityPkEntity cityPk;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_code", insertable = false, updatable = false)
    private DepartmentEntity department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityEntity that = (CityEntity) o;

        return Objects.equals(cityPk, that.cityPk);
    }

    @Override
    public int hashCode() {
        return cityPk != null ? cityPk.hashCode() : 0;
    }
}