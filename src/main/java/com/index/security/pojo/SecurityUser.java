package com.index.security.pojo;

import com.index.security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Auther: Index
 * @Date: 2018/12/28 15:05
 * @Description:
 */
public class SecurityUser extends User implements UserDetails {
    private static final long serialVersionUID = 1L;

    public SecurityUser(User user) {
        if (user != null) {
            this.setId(user.getId());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setSex(user.getSex());
            this.setEmail(user.getEmail());
            this.setPhotoid(user.getPhotoid());
            this.setRole(user.getRole());
            this.setProprety(user.getProprety());
            this.setNickname(user.getNickname());
            this.setTelephone(user.getTelephone());
            this.setRegistrationtime(user.getRegistrationtime());
            this.setUpdatetime(user.getUpdatetime());
            this.setUpdateby(user.getUpdateby());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String role = this.getUsername();
        if (role != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        }
        return authorities;
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
