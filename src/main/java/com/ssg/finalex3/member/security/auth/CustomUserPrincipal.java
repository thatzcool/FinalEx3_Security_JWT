package com.ssg.finalex3.member.security.auth;

import lombok.RequiredArgsConstructor;

import java.security.Principal;


@RequiredArgsConstructor
public class CustomUserPrincipal implements Principal {

    private final String mid;

    @Override
    public String getName() {
        return mid;
    }
}