package com.cypcode.secureapi.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.cypcode.secureapi.security.AuthenticationMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService {

    private static final String AUTH_TOKEN_HEADER_NAME = "API-KEY";
    private static final String AUTH_TOKEN = "API-VALUE";

    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new AuthenticationMapper(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}