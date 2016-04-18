/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.Poli;
import com.ivans.antrian.exception.AntrianServerException;
import com.ivans.antrian.service.PoliDao;
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
@RequestMapping("/api/master/poli")
public class PoliController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(PoliController.class);
    @Autowired
    private PoliDao poliDao;
    
    @RequestMapping(method = RequestMethod.GET)
    public Page<Poli> allPoli(Pageable pageable, HttpSession session) {
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "code");
        Page<Poli> result = poliDao.findAll(pr);
        return result;
    }
    
    @RequestMapping(value = "/withoutPaging", method = RequestMethod.GET)
    public Iterable<Poli> findAllWithoutPaging() {
        return poliDao.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Poli findPoliById(@PathVariable String id) {
        Poli p = poliDao.findOne(id);

        if (p == null) {
            throw new AntrianServerException("Poli Dengan ID : " + id + " Tidak Ditemukan");
        }

        return p;
    }

    @RequestMapping(value = "/byCode/{code}", method = RequestMethod.GET)
    public Poli findByCode(@PathVariable String code) {
        return poliDao.findByCode(code);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        Poli p = poliDao.findOne(id);
        if (p != null) {
            poliDao.delete(id);
        }else{
            throw new AntrianServerException("Poli Dengan ID : " + id + " Tidak Ditemukan");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody @Valid Poli poli, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Save Poli : " + poli.getName() + " [ " + poli.getCode() + " ]");
        poliDao.save(poli);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody @Valid Poli poli) {
        Poli p = poliDao.findOne(id);
        if (p != null) {
            poli.setId(p.getId());
            poliDao.save(poli);
        }else{
            throw new AntrianServerException("Poli Dengan ID : " + id + " Tidak Ditemukan");
        }
    }
    
    @RequestMapping(value="/count", method = RequestMethod.GET)
    public Long count(Pageable pageable){
        return poliDao.count();
    }
    
    
}
