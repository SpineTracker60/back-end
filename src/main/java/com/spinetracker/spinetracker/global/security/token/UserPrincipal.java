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
    private final String name;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Map<String, Object> attributes;

    private UserPrincipal(Builder builder) {
        this.id = builder.id;
        this.role = builder.role;
        this.name = builder.name;
        this.authorities = builder.authorities;
        this.attributes = builder.attributes;
    }

    // 실제 oauth2를 통한 userprincipal builder
    public static Builder builder(Long id, String role, String name, Map<String, Object> attributes) {
        return new Builder(id, role, name);
    }

    // 토큰 생성할 때를 위한 userprincipal builder
    // 예를들어 은행 사이트에 로그인 연장 버튼을 누를때는 토큰이 재발급 되는것이기때문에 유저 정보를 다시 불러오지 못해서
    // attiribute 필드가 필요가 없다. (기능 명세에 따라 바뀔 수 있음 -> 연장 눌렀을 때 재 로그인 하게 하면 attribute 필요함)
    public static Builder builder(Long id, String role, String name) {
        return new Builder(id, role, name);
    }

    public static class Builder {
        private final Long id;
        private final String role;
        private final String name;
        private final Collection<? extends GrantedAuthority> authorities;
        private Map<String, Object> attributes;

        public Builder(Long id, String role, String name, Map<String, Object> attributes) {
            this.id = id;
            this.role = role;
            this.name = name;
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
            this.attributes = attributes;
        }

        public Builder(Long id, String role, String name) {
            this.id = id;
            this.role = role;
            this.name = name;
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
        return UserPrincipal.builder(member.getId(), RoleEnum.valueOf(member.getRole().name()).getKey(), member.getName(), attributes).build();
    }

    public static UserPrincipal create(FindMemberDTO member, Map<String, Object> attributes) {
        return UserPrincipal.builder(member.getId(), RoleEnum.valueOf(member.getRole()).getKey(), member.getName(), attributes).build();
    }

    public static UserPrincipal create(FindMemberDTO member) {
        return UserPrincipal.builder(member.getId(), RoleEnum.valueOf(member.getRole()).getKey(), member.getName()).build();
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
        return name;
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
        return name;
    }

}
