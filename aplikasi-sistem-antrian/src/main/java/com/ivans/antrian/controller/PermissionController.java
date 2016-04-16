/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.Permission;
import com.ivans.antrian.service.PermissionDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/api/system/permission")
public class PermissionController {

    private final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    private PermissionDao permissionDao;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Permission> allPermission(Pageable pageable, HttpSession session) {
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "label");
        Page<Permission> result = permissionDao.findAll(pr);
        return result;
    }
    
    @RequestMapping(value = "/withoutPaging", method = RequestMethod.GET)
    public Iterable<Permission> findAllWithoutPaging() {
        return permissionDao.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Permission findPermissionById(@PathVariable String id) {
        Permission p = permissionDao.findOne(id);

        if (p == null) {
            return null;
        }

        return p;
    }

    @RequestMapping(value = "/byValue/{value}", method = RequestMethod.GET)
    public Permission findByValue(@PathVariable String value) {
        return permissionDao.findByValue(value);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        Permission p = permissionDao.findOne(id);
        if (p != null) {
            permissionDao.delete(id);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody @Valid Permission permission, HttpServletRequest request, HttpServletResponse response) {
        permissionDao.save(permission);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody @Valid Permission permission) {
        Permission p = permissionDao.findOne(id);
        if (p != null) {
            permission.setId(p.getId());
            permissionDao.save(permission);
        }
    }
    
    @RequestMapping(value="/count", method = RequestMethod.GET)
    public Long count(Pageable pageable){
        return permissionDao.count();
    }
    
    @RequestMapping(value = "/byLabel/{label}", method = RequestMethod.GET)
    public Permission findByLabel(@PathVariable String label) {
        return permissionDao.findByLabel(label);
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Permission> findByAny(@RequestParam("param") String param) {
        return permissionDao.findByAny("%" + param + "%");
    }
}
