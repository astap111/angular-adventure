package org.itechart.configuration;

import org.itechart.entity.Role;
import org.itechart.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class SecurityUser extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUser(User user) {
        if (user != null) {
            setId(user.getId());
            setLogin(user.getLogin());
            setPassword(user.getPassword());
            setRole(user.getRole());
            setBirthDate(user.getBirthDate());
            setFirstName(user.getFirstName());
            setLastName(user.getLastName());
            setMiddleName(user.getMiddleName());
            setEmail(user.getEmail());
        }
    }

    public SecurityUser() {
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Role userRole = this.getRole();

        if (userRole != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.getRoleName());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}