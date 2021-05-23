package rs.ac.uns.ftn.tim5.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import rs.ac.uns.ftn.tim5.security.UserRole;

import java.util.Set;

public class JwtModel {
    private Long id;
    private String email;
    private UserRole userRole;
    private Set<GrantedAuthority> grantedAuthorityList;

    public JwtModel(){}

    public JwtModel(Long id, String email, UserRole userRole, Set<GrantedAuthority> grantedAuthorityList) {
        this.id = id;
        this.email = email;
        this.userRole = userRole;
        this.grantedAuthorityList = grantedAuthorityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Set<GrantedAuthority> getGrantedAuthorityList() {
        return grantedAuthorityList;
    }

    public void setGrantedAuthorityList(Set<GrantedAuthority> grantedAuthorityList) {
        this.grantedAuthorityList = grantedAuthorityList;
    }
}
