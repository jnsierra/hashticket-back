package co.ud.hashticket.datos.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "department")
@NamedQueries({
        @NamedQuery(name="DepartmentEntity.findByCountry", query = "FROM DepartmentEntity dep inner join dep.country as country WHERE country.code = :idCountry ")
})
public class DepartmentEntity implements Serializable {
    @Id
    @Column(name = "code", nullable = false, updatable = false)
    private Long code;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code")
    private CountryEntity country;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<CityEntity> cities;

    public DepartmentEntity() {
        System.out.println("Llego al constructor vacio");
    }

    public DepartmentEntity(Long code, String name, CountryEntity country, Set<CityEntity> cities) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.cities = cities;
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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public Set<CityEntity> getCities() {
        return cities;
    }

    public void setCities(Set<CityEntity> cities) {
        this.cities = cities;
    }

}
