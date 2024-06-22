package srimalar.sample.oauth.entity;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "branch")
public class Branch extends AbstractAuditEntity {

    @Column(name = "branch_name", length = 32)
    private String branchName;

    @Column(name = "description")
    private String description;

    @Column(name = "inactive")
    private boolean inactive;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "branch_user_map", joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserAccount> userInfoSet;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public Set<UserAccount> getUserInfoSet() {
        return userInfoSet;
    }

    public void setUserInfoSet(Set<UserAccount> userInfoSet) {
        this.userInfoSet = userInfoSet;
    }
}
