package co.ud.hashticket.datos.entity;

import javax.persistence.*;

@Entity
@Table(name = "city")
@NamedQueries({
        @NamedQuery(name="CityEntity.findByDepartment", query = "from CityEntity city inner join fetch city.department as dep where dep.code = :departmentCode ")
})
public class CityEntity {
    @EmbeddedId
    private CityPkEntity cityPk;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_code", insertable = false, updatable = false)
    private DepartmentEntity department;

    public CityEntity() {
    }

    public CityEntity(CityPkEntity cityPk, String name, DepartmentEntity department) {
        this.cityPk = cityPk;
        this.name = name;
        this.department = department;
    }

    public CityPkEntity getCityPk() {
        return cityPk;
    }

    public void setCityPk(CityPkEntity cityPk) {
        this.cityPk = cityPk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}