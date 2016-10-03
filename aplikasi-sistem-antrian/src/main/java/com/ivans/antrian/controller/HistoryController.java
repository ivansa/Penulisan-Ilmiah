/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.HistoryObat;
import com.ivans.antrian.domain.HistoryObatDetail;
import com.ivans.antrian.domain.HistoryPasien;
import com.ivans.antrian.domain.Pasien;
import com.ivans.antrian.service.HistoryObatDao;
import com.ivans.antrian.service.HistoryPasienDao;
import com.ivans.antrian.service.PasienDao;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/api/transaksi")
public class HistoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(HistoryController.class);
    @Autowired
    private PasienDao pasienDao;
    @Autowired
    private HistoryPasienDao historyDao;
    @Autowired
    private HistoryObatDao obatDao;

    @RequestMapping(value = "/pasien/{no}", method = RequestMethod.GET)
    public Pasien findByPasien(@PathVariable String no) {
        return pasienDao.findByNoPasien(no);
    }

    @RequestMapping(value = "/pasien/save", method = RequestMethod.POST)
    public Pasien savePasien(@RequestBody @Valid Pasien pasien, HttpServletRequest request, HttpServletResponse response) {
        Pasien p = pasienDao.findByNoPasien(pasien.getNoPasien());
        if(p != null) pasien.setId(p.getId());
        if(pasien.getNoPasien() == null) pasien.setNoPasien(UUID.randomUUID().toString());
        pasien.setUpdatedDate(new Date());
        pasien.setValidate(Boolean.FALSE);
        pasienDao.save(pasien);
        
        return pasienDao.findByNoPasien(pasien.getNoPasien());
    }

    @RequestMapping(value = "/history/pasien/save", method = RequestMethod.POST)
    public void saveHistoryPasien(@RequestBody HistoryPasien history, HttpServletRequest request, HttpServletResponse response) {
        historyDao.save(history);
    }

    @RequestMapping(value = "/history/obat/save", method = RequestMethod.POST)
    public void saveHistoryObat(@RequestBody HistoryObat history, HttpServletRequest request, HttpServletResponse response) {
        for (HistoryObatDetail detail : history.getDetail()) {
            detail.setHistoryObat(history);
        }
        obatDao.save(history);
    }

    @RequestMapping(value = "/history/obat/{id}", method = RequestMethod.GET)
    public Page<HistoryObat> findHistoryObat(@PathVariable String id, Pageable pageable) {
        PageRequest pr = new PageRequest(0, 5, Sort.Direction.DESC, "transactionDate");
        Page<HistoryObat> result = obatDao.findByPasienId(id, pageable);
        return result;
    }

    @RequestMapping(value = "/history/pasien/{id}", method = RequestMethod.GET)
    public Page<HistoryPasien> findHistoryPasien(@PathVariable String id, Pageable pageable) {
        PageRequest pr = new PageRequest(0, 5, Sort.Direction.DESC, "transactionDate");
        Page<HistoryPasien> result = historyDao.findByPasienId(id, pageable);
        return result;
    }
}
