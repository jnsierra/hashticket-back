package co.ud.hashticket.datos.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CityPkEntity implements Serializable {
    public CityPkEntity() {
    }
    public CityPkEntity(Long code, Long departmentCode) {
        this.code = code;
        this.departmentCode = departmentCode;
    }

    @Column(name = "code")
    private Long code;
    @Column(name = "department_code")
    private Long departmentCode;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Long departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityPkEntity that = (CityPkEntity) o;
        return Objects.equals(code, that.code) && Objects.equals(departmentCode, that.departmentCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, departmentCode);
    }
}
