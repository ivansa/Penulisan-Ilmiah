/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.Antrian;
import com.ivans.antrian.domain.AntrianCreate;
import com.ivans.antrian.domain.AntrianPrint;
import com.ivans.antrian.domain.BodProcess;
import com.ivans.antrian.domain.KategoriAntrian;
import com.ivans.antrian.domain.Kuota;
import com.ivans.antrian.domain.Poli;
import com.ivans.antrian.exception.AntrianServerException;
import com.ivans.antrian.helper.DateHelper;
import com.ivans.antrian.service.AntrianDao;
import com.ivans.antrian.service.BodDao;
import com.ivans.antrian.service.KategoriAntrianDao;
import com.ivans.antrian.service.KuotaDao;
import com.ivans.antrian.service.PoliDao;
import com.ivans.antrian.service.RunningNumberService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@RequestMapping("/api/pendaftaran")
public class PendaftaranContoller {

    private final Logger LOGGER = LoggerFactory.getLogger(PendaftaranContoller.class);

    @Autowired
    private KategoriAntrianDao categoryDao;

    @Autowired
    private PoliDao poliDao;

    @Autowired
    private KuotaDao kuotaDao;

    @Autowired
    private BodDao bodDao;

    @Autowired
    private AntrianDao antrianDao;

    @Autowired
    private RunningNumberService runnService;

    @RequestMapping(value = "/get/category", method = RequestMethod.GET)
    public Iterable<KategoriAntrian> findAllCategory() {
        return categoryDao.findAll();
    }

    @RequestMapping(value = "/get/poli", method = RequestMethod.GET)
    public Iterable<Poli> findAllPoli() {
        return poliDao.findAll();
    }

    @RequestMapping(value = "/get/dokter/{idPoli}", method = RequestMethod.GET)
    public List<Kuota> findAllDokter(@PathVariable String idPoli) {
        return kuotaDao.findByPoliIdAndKuotaDate(idPoli, new Date());
    }

    @RequestMapping(value = "/check/bod", method = RequestMethod.GET)
    public Map checkBod() {
        Map<String, Boolean> result = new HashMap<String, Boolean>();
        BodProcess bod = bodDao.findByGenerateDate(new Date());
        if (bod != null) {
            result.put("isBod", Boolean.TRUE);
        } else {
            result.put("isBod", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public AntrianPrint create(@RequestBody AntrianCreate param, HttpServletRequest request, HttpServletResponse response) {
        Kuota kuota;
        if (param.getCategoryCode().equals("F")) {
            kuota = kuotaDao.findByCodeDokterAndKuotaDate("F", new Date());
        } else {
            kuota = kuotaDao.findOne(param.getIdKuota());
        }

        String tiketNumber = runnService.getNumberAndUpdate(new Date(), param.getCategoryCode());

        if (!StringUtils.hasText(tiketNumber)) {
            throw new AntrianServerException("Gagal Dalam Membuat Ticket Number");
        }

        String today = DateHelper.dateToString(new Date(), "yyyy-MM-dd");
        Antrian antrian = new Antrian();
        antrian.setDokter(kuota);
        antrian.setNomorAntrian(tiketNumber);
        antrian.setStatus(Boolean.FALSE);
        antrian.setAntrianDate(today);
        antrian.setTimestamp(new Date());
        antrian.setJenisLoket(param.getCategoryCode());
        antrian.setNomorPasien(param.getNomorPasien());

        kuota.setCurrentKuota(kuota.getCurrentKuota() + 1);
        antrianDao.save(antrian);
        kuotaDao.save(kuota);
        AntrianPrint result = new AntrianPrint();
        result.setNomorAntrian(tiketNumber);
        result.setName(kuota.getNamaDokter());
        result.setDescription(kuota.getDescriptionDokter());
        return result;
    }

}
