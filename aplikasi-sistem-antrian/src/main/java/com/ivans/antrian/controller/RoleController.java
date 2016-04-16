/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.Role;
import com.ivans.antrian.service.RoleDao;
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
@RequestMapping("/api/system")
public class RoleController {

    private final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleDao roleDao;

    @RequestMapping("/role")
    public Page<Role> allRole(Pageable pageable, HttpSession session) {
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "name");
        Page<Role> result = roleDao.findAll(pr);
        return result;
    }
    
    @RequestMapping(value = "/role/withoutPaging", method = RequestMethod.GET)
    public Iterable<Role> findAllWithoutPaging() {
        return roleDao.findAll();
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public Role findRoleById(@PathVariable String id) {
        Role r = roleDao.findOne(id);

        if (r == null) {
            return null;
        }

        return r;
    }

    @RequestMapping(value = "/role/byName/{name}", method = RequestMethod.GET)
    public Role findByName(@PathVariable String name) {
        return roleDao.findByName(name);
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        Role r = roleDao.findOne(id);
        if (r != null) {
            roleDao.delete(id);
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public void create(@RequestBody @Valid Role role, HttpServletRequest request, HttpServletResponse response) {
        roleDao.save(role);
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody @Valid Role role) {
        Role r = roleDao.findOne(id);
        if (r != null) {

            role.setId(r.getId());
            roleDao.save(role);
        }
    }

    @RequestMapping(value = "/role/count", method = RequestMethod.GET)
    public Long count(Pageable pageable) {
        return roleDao.count();
    }
    
    @RequestMapping(value = "/role/search", method = RequestMethod.GET)
    public List<Role> findByAny(@RequestParam("param") String param) {
        return roleDao.findByAny("%" + param + "%");
    }
}
