package no.sensor.db.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "user_sessions")
public class UserSessionEntity {
    @Id
    @Column(name = "token")
    private String token;

    @Column(name = "username")
    private String userName;

    @Column(name = "expires")
    private Timestamp expires;

    public UserSessionEntity() {
    }

    public UserSessionEntity(String token, String userName, Timestamp expires) {
        this.token = token;
        this.userName = userName;
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getExpires() {
        return expires;
    }

    public void setExpires(Timestamp expires) {
        this.expires = expires;
    }
}
