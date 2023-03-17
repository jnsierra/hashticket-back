package co.ud.hashticket.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CityPkEntity implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Column(name = "code")
    private Long code;
    @Column(name = "department_code")
    private Long departmentCode;
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
