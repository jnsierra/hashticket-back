package co.ud.hashticket.datos.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "country")
public class CountryEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @Column(name = "code", nullable = false, updatable = false)
    private Long code;
    @Column(name = "name")
    private String name;
    @Column(name = "diminutive_alpha3")
    private String diminutive;
    @OneToMany(mappedBy = "country")
    private Set<DepartmentEntity> departments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}