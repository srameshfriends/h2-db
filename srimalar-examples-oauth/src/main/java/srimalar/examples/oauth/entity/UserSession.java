package srimalar.examples.oauth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_session")
public class UserSession extends BaseEntity {
    @JoinColumn(name = "user_account")
    @ManyToOne
    private UserAccount userAccount;

    @Column(name = "id_token", length = 256)
    private String idToken;

    @Column(name = "access_token", length = 256)
    private String accessToken;

    @Column(name = "refresh_token", length = 256)
    private String refreshToken;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
