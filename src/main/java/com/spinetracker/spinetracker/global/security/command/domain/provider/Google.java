package com.spinetracker.spinetracker.global.security.command.domain.provider;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;

import java.util.Map;

public class Google extends OAuth2UserInfo {

    public Google(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {

        return String.valueOf(attributes.get("sub"));
    }

    @Override
    public String getName() {

        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {

        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {

        return (String) attributes.get("picture");
    }

    @Override
    public String getProvider(){
        return PlatformEnum.GOOGLE.name();
    }
}
