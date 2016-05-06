/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.KategoriAntrian;
import com.ivans.antrian.domain.User;
import com.ivans.antrian.exception.BodException;
import com.ivans.antrian.exception.ResourceNotFoundException;
import com.ivans.antrian.helper.SpringSecurityHelper;
import com.ivans.antrian.service.BodProcessService;
import com.ivans.antrian.service.UserDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ivans
 */
@RestController
@RequestMapping("/api/generate/kuota")
public class BodController {

    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private BodProcessService bodService;
    @Autowired
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.POST)
    public void generate() throws BodException {
        User user = userDao.findByUsername(SpringSecurityHelper.getUserLogin().getUsername());
        if (user == null) {
            throw new ResourceNotFoundException("Username : " + SpringSecurityHelper.getUserLogin() + " , tidak ditemukan ");
        }
        bodService.bodProcess(user.getUsername());
    }
}
