/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.Antrian;
import com.ivans.antrian.domain.Kuota;
import com.ivans.antrian.domain.Permission;
import com.ivans.antrian.helper.DateHelper;
import com.ivans.antrian.service.AntrianDao;
import com.ivans.antrian.service.KuotaDao;
import java.util.Date;
import javax.servlet.http.HttpSession;
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
public class ReportKuotaController {
    @Autowired
    private KuotaDao kuotaDao;
    
    @RequestMapping(value = "/kuota", method = RequestMethod.GET)
    public Page<Kuota> search(@RequestParam(required = false) String dokter, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date tanggal, Pageable pageable) {
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "codeDokter");
        long millisOneDay = 60 * 60 * 24;
        Instant instantDate = Instant.ofEpochMilli(tanggal.getTime()).plusSeconds(millisOneDay);
        
        Page<Kuota> result;
        if(StringUtils.hasText(dokter)){
            result = kuotaDao.findByNamaDokterContainingAndKuotaDate(dokter, new Date(instantDate.toEpochMilli()), pr);
        }else{
            result = kuotaDao.findByKuotaDate(new Date(instantDate.toEpochMilli()), pr);
        }
        return result;
    }
}
