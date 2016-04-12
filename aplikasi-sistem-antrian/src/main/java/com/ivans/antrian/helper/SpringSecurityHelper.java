/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.helper;

import com.ivans.antrian.exception.UnauthenticatedRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


public class SpringSecurityHelper {
    
    public static User getUserLogin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            throw new UnauthenticatedRequestException("Unauthenticated Request");
        }

        Object principal = auth.getPrincipal();
        if (principal == null) {
            throw new UnauthenticatedRequestException("Invalid principal object");
        }

        if (!org.springframework.security.core.userdetails.User.class.isAssignableFrom(principal.getClass())) {
            throw new UnauthenticatedRequestException("Invalid authentication object " + principal.getClass().getName());
        }

        User u = (User) principal;
        return u;
    }
    
}
