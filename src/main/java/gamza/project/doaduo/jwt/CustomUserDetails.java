package gamza.project.doaduo.jwt;

import gamza.project.doaduo.entity.UserEntity;
import gamza.project.doaduo.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        switch (user.getUserRole()) {
            case REQUESTER: authorityList.add(getAuthorities(UserRole.REQUESTER));
            case RESPONDENT: authorityList.add(getAuthorities(UserRole.RESPONDENT));
        }

        return authorityList;
    }

    private GrantedAuthority getAuthorities(UserRole userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole);
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