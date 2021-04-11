package ro.dan.dragu.account.management.dal.entity;

import javax.persistence.*;

@Entity
@Table(name="ACCOUNTS")
public class Account {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="USER_NAME", nullable=false, unique = true)
    private String name;

    @Column(name="PASSWORD", nullable=false)
    private String password;

    @Column(name="NAME", nullable=false)
    private String fullName;

    @Column(name="EMAIL", nullable=false, unique=true)
    private String email;

    @Column(name="IS_DISABLED", nullable=false)
    private Boolean disabled;

    @Column(name="ROLE", nullable=false)
    @Enumerated(EnumType.STRING)
    private AccountRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public AccountRole getRole() {
        return role;
    }

    public void setRole(AccountRole role) {
        this.role = role;
    }
}
