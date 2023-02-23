package co.ud.hashticket.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "city")
@NamedQueries({
        @NamedQuery(name="CityEntity.findByDepartment", query = "from CityEntity city inner join fetch city.department as dep where dep.code = :departmentCode ")
})
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
    @JoinColumn(name = "department_code", insertable = false, updatable = false)
    private DepartmentEntity department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityEntity that = (CityEntity) o;
        return Objects.equals(cityPk, that.cityPk) && Objects.equals(name, that.name) && Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityPk, name, department);
    }
}