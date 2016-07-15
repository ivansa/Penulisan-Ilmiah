/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.Antrian;
import com.ivans.antrian.helper.DateHelper;
import com.ivans.antrian.service.AntrianDao;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.bp.Instant;

/**
 *
 * @author ivans
 */
@RestController
@RequestMapping("/api/report")
public class ReportAntrianHarianController {

    @Autowired
    private AntrianDao antrianDao;
    private final Logger LOGGER = LoggerFactory.getLogger(ReportAntrianHarianController.class);

    @RequestMapping(value = "/antrian", method = RequestMethod.GET)
    public Page<Antrian> search(@RequestParam(required = false) String nomor, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date tanggal, Pageable pageable) {
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "nomorAntrian");
        long millisOneDay = 60 * 60 * 24;
        Instant instantDate = Instant.ofEpochMilli(tanggal.getTime()).plusSeconds(millisOneDay);
        String dateStr = DateHelper.dateToString(new Date(instantDate.toEpochMilli()), "yyyy-MM-dd");
        
        Page<Antrian> result;
        if (StringUtils.hasText(nomor)) {
            result = antrianDao.findByNomorAntrianStartingWithAndAntrianDate(nomor, dateStr, pr);
        } else {
            result = antrianDao.findByAntrianDate(dateStr, pr);
        }
        return result;
    }
}
