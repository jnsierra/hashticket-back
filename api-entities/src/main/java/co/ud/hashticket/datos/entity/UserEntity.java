package co.ud.hashticket.datos.entity;

import co.ud.ud.hashticket.enumeration.USER_STATE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "UserEntity.updateAttempts", query = "update UserEntity usu set usu.attempts = :attempts WHERE usu.id = :id")
@Entity
@Table(name = "user_tickets")
public class UserEntity  extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;
    @Id
    @GeneratedValue(generator = "sequence-generator-user")
    @GenericGenerator(
            name = "sequence-generator-user",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "change_password")
    private String changePassword;
    @Column(name = "attempts")
    private Long attempts;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private USER_STATE state;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_tickets_user_type",
            joinColumns = { @JoinColumn(name = "user_tickets_id") },
            inverseJoinColumns = { @JoinColumn( name = "user_type_id")}

    )
    private Set<UserTypeEntity> userTypes;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
