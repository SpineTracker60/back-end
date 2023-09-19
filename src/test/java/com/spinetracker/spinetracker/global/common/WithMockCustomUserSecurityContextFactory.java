package com.spinetracker.spinetracker.global.common;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.AgeRangeEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.GenderEnum;
import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        FindMemberDTO mockFindMemberDTO = new FindMemberDTO(
                Long.parseLong(annotation.id()),
                "mockName",
                GenderEnum.FEMALE.name(),
                AgeRangeEnum.FIFTY.name(),
                "학생",
                "profileImage",
                annotation.provider(),
                annotation.role(),
                annotation.email()

        );

        UserPrincipal principal = UserPrincipal.create(mockFindMemberDTO);
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
