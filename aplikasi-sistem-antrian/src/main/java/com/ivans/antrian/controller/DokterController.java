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
import com.ivans.antrian.service.DokterDao;
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
@RequestMapping("/api/master/dokter")
public class DokterController {
    private final Logger LOGGER = LoggerFactory.getLogger(DokterController.class);
    @Autowired
    private DokterDao dokterDao;
    
    @RequestMapping(method = RequestMethod.GET)
    public Page<JadwalDokter> allDokter(Pageable pageable, HttpSession session) {
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "code");
        Page<JadwalDokter> result = dokterDao.findAll(pr);
        return result;
    }
    
    @RequestMapping(value = "/withoutPaging", method = RequestMethod.GET)
    public Iterable<JadwalDokter> findAllWithoutPaging() {
        return dokterDao.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JadwalDokter findDokterById(@PathVariable String id) {
        JadwalDokter d = dokterDao.findOne(id);

        if (d == null) {
            throw new AntrianServerException("Dokter Dengan ID : " + id + " Tidak Ditemukan");
        }

        return d;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        JadwalDokter d = dokterDao.findOne(id);
        if (d != null) {
            dokterDao.delete(id);
        }else{
            throw new AntrianServerException("Dokter Dengan ID : " + id + " Tidak Ditemukan");
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody @Valid JadwalDokter dokter, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Save Dokter : " + dokter.getCode());
        dokterDao.save(dokter);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody @Valid JadwalDokter dokter) {
        JadwalDokter d = dokterDao.findOne(id);
        if (d != null) {
            dokter.setId(d.getId());
            dokterDao.save(dokter);
        }else{
            throw new AntrianServerException("Dokter Dengan ID : " + id + " Tidak Ditemukan");
        }
    }
    
    @RequestMapping(value = "/byCode/{code}", method = RequestMethod.GET)
    public JadwalDokter findByCode(@PathVariable String code) {
        return dokterDao.findByCode(code);
    }
    
}
