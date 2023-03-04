package co.ud.hashticket.datos.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

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
    public CountryEntity() {
    }

    public CountryEntity(Long code, String name, String diminutive, Set<DepartmentEntity> departments) {
        this.code = code;
        this.name = name;
        this.diminutive = diminutive;
        this.departments = departments;
    }
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiminutive() {
        return diminutive;
    }

    public void setDiminutive(String diminutive) {
        this.diminutive = diminutive;
    }

    public Set<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentEntity> departments) {
        this.departments = departments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(diminutive, that.diminutive) && Objects.equals(departments, that.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, diminutive, departments);
    }
}