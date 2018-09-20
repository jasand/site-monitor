package no.sensor.db.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq")
    @javax.persistence.SequenceGenerator(
            name="roles_id_seq",
            sequenceName = "roles_id_seq")
    private Long id;

    @Column(name = "role")
    private String role;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "roles")
//    private List<UserEntity> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public List<UserEntity> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<UserEntity> users) {
//        this.users = users;
//    }
}
