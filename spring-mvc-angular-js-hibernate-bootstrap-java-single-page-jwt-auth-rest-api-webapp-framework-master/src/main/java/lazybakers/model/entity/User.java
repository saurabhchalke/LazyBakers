package lazybakers.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lazybakers.framework.data.JPAEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(indexes = {  @Index(name="email_idx", columnList = "email", unique = true),
                    @Index(name="displayName_idx", columnList = "display_name") })
public class User extends JPAEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Role {
        USER,
        ADMIN
    }

    private String email;
    private @JsonIgnore String password;
    private String mobilenumber;
    private boolean enabled;
    private Role role;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "login_count")
    private @JsonIgnore Integer loginCount;
    @Column(name = "current_login_at")
    private Date currentLoginAt;
    @Column(name = "last_login_at")
    private Date lastLoginAt;
    @Column(name = "current_login_ip")
    private @JsonIgnore String currentLoginIp;
    @Column(name = "last_login_ip")
    private @JsonIgnore String lastLoginIp;

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Column @Email @NotNull @NotBlank
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore @Column(nullable = false, length = 60)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(nullable = false)
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name="display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonIgnore @Column
    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    @Column
    public Date getCurrentLoginAt() {
        return currentLoginAt;
    }

    public void setCurrentLoginAt(Date currentLoginAt) {
        this.currentLoginAt = currentLoginAt;
    }

    @Column
    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    @JsonIgnore @Column
    public String getCurrentLoginIp() {
        return currentLoginIp;
    }

    public void setCurrentLoginIp(String currentLoginIp) {
        this.currentLoginIp = currentLoginIp;
    }

    @JsonIgnore @Column
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * Method to create the hash of the password before storing
     *
     * @param pass
     *
     * @return SHA hash digest of the password
     */
    public static synchronized String hashPassword(String pass) {
        return passwordEncoder.encode(pass);
    }

    public static synchronized boolean doesPasswordMatch(String rawPass, String encodedPass) {
        return passwordEncoder.matches(rawPass, encodedPass);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", passphrase='" + password + '\'' +
                ", enabled=" + enabled +
                ", role=" + role +
                ", displayName='" + displayName + '\'' +
                ", loginCount=" + loginCount +
                ", currentLoginAt=" + currentLoginAt +
                ", lastLoginAt=" + lastLoginAt +
                ", currentLoginIp='" + currentLoginIp + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                '}';
    }

	public String getmobilenumber() {
		return mobilenumber;
	}

	public void setmobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public User(String email, String password, String mobilenumber, boolean enabled, Role role, String displayName,
			Integer loginCount, Date currentLoginAt, Date lastLoginAt, String currentLoginIp, String lastLoginIp) {
		super();
		this.email = email;
		this.password = password;
		this.mobilenumber = mobilenumber;
		this.enabled = enabled;
		this.role = role;
		this.displayName = displayName;
		this.loginCount = loginCount;
		this.currentLoginAt = currentLoginAt;
		this.lastLoginAt = lastLoginAt;
		this.currentLoginIp = currentLoginIp;
		this.lastLoginIp = lastLoginIp;
	}

	public User() {

	}
	
	
}
