/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.JadwalDokter;
import com.ivans.antrian.domain.Loket;
import com.ivans.antrian.domain.Poli;
import com.ivans.antrian.exception.AntrianServerException;
import com.ivans.antrian.service.LoketDao;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ivans
 */
@RestController
@RequestMapping("/api/master/loket")
public class LoketController {
    private final Logger LOGGER = LoggerFactory.getLogger(PoliController.class);
    @Autowired
    private LoketDao loketDao;
    
    @RequestMapping(method = RequestMethod.GET)
    public Page<Loket> allLoket(Pageable pageable, HttpSession session) {
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "nomorLoket");
        Page<Loket> result = loketDao.findAll(pr);
        return result;
    }
    
    @RequestMapping(value = "/withoutPaging", method = RequestMethod.GET)
    public Iterable<Loket> findAllWithoutPaging() {
        return loketDao.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Loket findLoketById(@PathVariable String id) {
        Loket l = loketDao.findOne(id);

        if (l == null) {
            throw new AntrianServerException("Loket Dengan ID : " + id + " Tidak Ditemukan");
        }

        return l;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        Loket l = loketDao.findOne(id);
        if (l != null) {
            loketDao.delete(id);
        }else{
            throw new AntrianServerException("Loket Dengan ID : " + id + " Tidak Ditemukan");
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody @Valid Loket loket, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Save Loket : " + loket.getNomorLoket());
        loketDao.save(loket);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody @Valid Loket loket) {
        Loket l = loketDao.findOne(id);
        if (l != null) {
            loket.setId(l.getId());
            loketDao.save(loket);
        }else{
            throw new AntrianServerException("Poli Dengan ID : " + id + " Tidak Ditemukan");
        }
    }
    
    @RequestMapping(value = "/byNomor/{nomor}", method = RequestMethod.GET)
    public Loket findByNomor(@PathVariable int nomor) {
        return loketDao.findByNomorLoket(nomor);
    }
}
