package srimalar.sample.oauth.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user_account")
public class UserAccount extends AbstractAuditEntity {
    @Column(name = "user_name", length = 32)
    private String userName;

    @Column(name = "given_name", length = 48)
    private String givenName;

    @Column(name = "password", length = 128)
    private String password;

    @Column(name = "confirm_password", length = 128)
    private String confirmPassword;

    @JoinColumn(name = "user_role")
    @ManyToOne
    private UserRole userRole;

    @Column(name = "inactive")
    private boolean inactive;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinTable(name = "branch_user_map",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id")
    )
    private Set<Branch> branchSet;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public Set<Branch> getBranchSet() {
        return branchSet;
    }

    public void setBranchSet(Set<Branch> branchSet) {
        this.branchSet = branchSet;
    }
}
