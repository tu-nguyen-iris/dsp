package io.dev.gateway.Service.User;

import io.dev.gateway.entity.Roles;
import io.dev.gateway.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by TuNguyen
 * In 12/14/2021
 */
@Data
@AllArgsConstructor
public class CustomUserDetail implements UserDetails {

    User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Roles> roleList = user.getRolesList();
        List<SimpleGrantedAuthority> authorize = new ArrayList();
        roleList.stream().forEach(item -> authorize.add(new SimpleGrantedAuthority(item.getRoleName())));
        System.out.println(authorize + "lst authorzie");
        return authorize;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
