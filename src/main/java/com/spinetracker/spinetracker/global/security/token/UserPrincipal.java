package com.spinetracker.spinetracker.global.security.token;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class UserPrincipal implements OAuth2User, UserDetails {

    @Getter
    private final Long id;
    private final String role;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Map<String, Object> attributes;

    private UserPrincipal(Builder builder) {
        this.id = builder.id;
        this.role = builder.role;
        this.authorities = builder.authorities;
        this.attributes = builder.attributes;
    }

    public static Builder builder(Long id, String role, Map<String, Object> attributes) {
        return new Builder(id, role);
    }

    public static Builder builder(Long id, String role) {
        return new Builder(id, role);
    }

    public static class Builder {
        private final Long id;
        private final String role;
        private final Collection<? extends GrantedAuthority> authorities;
        private Map<String, Object> attributes;

        public Builder(Long id, String role, Map<String, Object> attributes) {
            this.id = id;
            this.role = role;
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
            this.attributes = attributes;
        }

        public Builder(Long id, String role) {
            this.id = id;
            this.role = role;
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
        }

        /**
         * 위 생성자에서 만약 입력 값 중 옵셔널 값일 경우 Setter를 통해 값을 초기화
         * 위 경우 Builder 클래스의 옵셔널 필드는 final 키워드 사용 불가
         * 이러한 경우 Builder 패턴을 사용하는 의미가 크게 낮아짐.
         */

        public UserPrincipal build() {
            return new UserPrincipal(this);
        }
    }

    public static UserPrincipal create(Member member, Map<String, Object> attributes) {
        return UserPrincipal.builder(member.getId(), RoleEnum.valueOf(member.getRole().name()).getKey(), attributes).build();
    }

    public static UserPrincipal create(FindMemberDTO member, Map<String, Object> attributes) {
        return UserPrincipal.builder(member.getId(), RoleEnum.valueOf(member.getRole()).getKey(), attributes).build();
    }

    public static UserPrincipal create(FindMemberDTO member) {
        return UserPrincipal.builder(member.getId(), RoleEnum.valueOf(member.getRole()).getKey()).build();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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

    @Override
    public String getName() {
        return null;
    }

}
