/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.ChangePassword;
import com.ivans.antrian.domain.User;
import com.ivans.antrian.domain.UserPassword;
import com.ivans.antrian.exception.AntrianServerException;
import com.ivans.antrian.exception.ResourceNotFoundException;
import com.ivans.antrian.exception.UnauthenticatedRequestException;
import com.ivans.antrian.helper.SpringSecurityHelper;
import com.ivans.antrian.service.UserDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ivans
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder encoder;
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @RequestMapping("/system/user")
    public Page<User> allUser(Pageable pageable, HttpSession session) {
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "username");
        Page<User> result = userDao.findAll(pr);
        return result;
    }

    @RequestMapping(value = "/system/user/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable String id) {
        User u = userDao.findOne(id);

        if (u == null) {
            return null;
        }

        return u;
    }

    @RequestMapping(value = "/system/user/byEmail", method = RequestMethod.GET)
    public User findUserByEmail(@RequestParam(value = "email", required = true) String email) {
        User u = userDao.findByEmail(email);
        if (u == null) {
            return null;
        }

        return u;
    }

    @RequestMapping(value = "/system/user/byUsername/{username}", method = RequestMethod.GET)
    public User findUserByUsername(@PathVariable String username) {
        User u = userDao.findByUsername(username);

        if (u == null) {
            return null;
        }

        return u;
    }
    
    @RequestMapping(value = "/system/user/byRole/{role}", method = RequestMethod.GET)
    public List<User> findUserByRole(@PathVariable String role) {
        List<User> result = userDao.findByRoleName(role);

        return result;
    }

    @RequestMapping(value = "/system/user/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        User u = userDao.findOne(id);
        if (u != null) {
            userDao.delete(id);
        }
    }

    @RequestMapping(value = "/system/user", method = RequestMethod.POST)
    public void create(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUserPassword(new UserPassword(user, user.getPassword()));
        userDao.save(user);
    }

    @RequestMapping(value = "/system/user/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody User x) {
        User user = userDao.findOne(id);
        if(user == null){
            throw new ResourceNotFoundException();
        }

        UserPassword up = user.getUserPassword();
        if(StringUtils.hasText(x.getPassword())){
            up.setPassword(encoder.encode(x.getPassword()));
        }
        x.setUserPassword(up);
        x.setId(user.getId());
        userDao.save(x);
    }

    @RequestMapping(value = "/user/loggedIn", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public User getLoggedIn() {
        try {
            if (!StringUtils.hasText(SpringSecurityHelper.getUserLogin().getUsername())) {
                return null;
            }

            User user = userDao.findByUsername(SpringSecurityHelper.getUserLogin().getUsername());
            if (user == null) {
                throw new ResourceNotFoundException("Username : " + SpringSecurityHelper.getUserLogin() + " , tidak ditemukan ");
            }

            return user;
        } catch (UnauthenticatedRequestException ex) {
             throw new UnauthenticatedRequestException(ex);
        }
    }

    @RequestMapping(value = "/system/user/validate/password", method = RequestMethod.POST)
    @Transactional(readOnly = true)
    public Boolean validateCurrentPassword(@RequestBody String password) {
        try {
            if (!StringUtils.hasText(SpringSecurityHelper.getUserLogin().getUsername())) {
                return null;
            }

            User user = userDao.findByUsername(SpringSecurityHelper.getUserLogin().getUsername());
            if (user == null) {
                throw new ResourceNotFoundException("Username : " + SpringSecurityHelper.getUserLogin() + " , tidak ditemukan ");
            }

            if (encoder.matches(password, user.getUserPassword().getPassword())) {
                return true;
            } else {
                return false;
            }

        } catch (UnauthenticatedRequestException ex) {
            return false;
        }
    }

    @RequestMapping(value = "/user/change/password", method = RequestMethod.POST)
    public void changePassword(@RequestBody ChangePassword cp) {

        try {
            User user = userDao.findByUsername(SpringSecurityHelper.getUserLogin().getUsername());
            if (user == null) {
                throw new ResourceNotFoundException("Username : " + SpringSecurityHelper.getUserLogin() + " , tidak ditemukan ");
            }

            if (encoder.matches(cp.getCurrentPassword(), user.getUserPassword().getPassword())) {
                if (cp.getNewPassword().equals(cp.getConfirmPassword())) {
                    user.getUserPassword().setPassword(encoder.encode(cp.getNewPassword()));
                    userDao.save(user);
                } else {
                    throw new AntrianServerException("Confirm Password Salah");
                }
            } else {
                throw new AntrianServerException("Your Current Password was incorrect.");
            }
        } catch (UnauthenticatedRequestException ex) {
            throw new UnauthenticatedRequestException(ex);
        }

    }
    
    @RequestMapping(value = "/user/search", method = RequestMethod.GET)
    public List<User> findByAny(@RequestParam("param") String param) {
        return userDao.findByAny("%" + param + "%");
    }
}
