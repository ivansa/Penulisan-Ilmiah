/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.Antrian;
import com.ivans.antrian.domain.AntrianPanggilan;
import com.ivans.antrian.domain.Loket;
import com.ivans.antrian.domain.User;
import com.ivans.antrian.exception.AntrianServerException;
import com.ivans.antrian.exception.ResourceNotFoundException;
import com.ivans.antrian.helper.DateHelper;
import com.ivans.antrian.helper.SpringSecurityHelper;
import com.ivans.antrian.service.AntrianDao;
import com.ivans.antrian.service.AntrianPemanggilanDao;
import com.ivans.antrian.service.KategoriAntrianDao;
import com.ivans.antrian.service.LoketDao;
import com.ivans.antrian.service.UserDao;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ivans
 */
@RestController
@RequestMapping("/api/transaksi/loket")
public class LoketPageController {

    @Autowired
    private KategoriAntrianDao categoryDao;

    @Autowired
    private AntrianDao antrianDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoketDao loketDao;

    @Autowired
    private AntrianPemanggilanDao pemanggilanDao;

    private final Logger LOGGER = LoggerFactory.getLogger(LoketPageController.class);

    @RequestMapping(value = "/get/antrian/{category}/{noLoket}", method = RequestMethod.GET)
    public Map<String, Object> getAntrian(@PathVariable String category, @PathVariable int noLoket) {
        PageRequest pr = new PageRequest(0, 1, Sort.Direction.ASC, "nomorAntrian");
        PageRequest prCurrent = new PageRequest(0, 1, Sort.Direction.DESC, "nomorAntrian");
        String today = DateHelper.dateToString(new Date(), "yyyy-MM-dd");

        Map<String, Object> result = new HashMap<String, Object>();
        LOGGER.info("Category ========= " + category);
        LOGGER.info("Tanggal =========== " + today);
        Page<Antrian> antrianPage = antrianDao.findByJenisLoketAndStatusAndAntrianDate(category, Boolean.FALSE, today, pr);

        LOGGER.info(String.valueOf(antrianPage.getContent().size()));
        Page<Antrian> current = antrianDao.findByNomorLoketAndAntrianDate(noLoket, today, prCurrent);
        
        Long totalAntrian = antrianDao.countByJenisLoketAndStatusAndAntrianDate(category, Boolean.FALSE, today);
        Boolean status = true;
        if (!current.getContent().isEmpty() && current.getContent().get(0) != null) {
            AntrianPanggilan pemanggilan = pemanggilanDao.findByNomorAntrian(current.getContent().get(0).getNomorAntrian());
            
            status =  pemanggilan == null ? pemanggilan.getStatus():false;
        }

        result.put(category, antrianPage);
        result.put("current", current);
        result.put("pemanggilan", status);
        result.put("totalAntrian", totalAntrian);
        return result;
    }

    @RequestMapping(value = "/take", method = RequestMethod.GET)
    public Map<String, Object> takeAntrian(@RequestParam String number, @RequestParam int loket) {

        Antrian antrian = antrianDao.findByNomorAntrian(number);
        if (antrian == null || antrian.getStatus()) {
            throw new AntrianServerException("Antrian Tidak Ada atau Sudah Di Proses ");
        }

        antrian.setNomorLoket(loket);
        antrian.setStatus(Boolean.TRUE);
        antrian.setUpdatedDate(new Date());

        antrianDao.save(antrian);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("number", antrian.getNomorAntrian());
        result.put("loket", antrian.getNomorLoket());

        return result;
    }

    @RequestMapping(value = "/get/loket", method = RequestMethod.GET)
    public Loket getNomorLoket() {
        User user = userDao.findByUsername(SpringSecurityHelper.getUserLogin().getUsername());
        if (user == null || user.getLoket() == null) {
            throw new ResourceNotFoundException("Username/Loket : " + SpringSecurityHelper.getUserLogin() + " , tidak ditemukan ");
        }

        Loket loket = loketDao.findOne(user.getLoket().getId());
        if (loket == null) {
            throw new ResourceNotFoundException("Loket dengan username : " + SpringSecurityHelper.getUserLogin() + " , tidak ditemukan ");
        }
        return loket;
    }

}
