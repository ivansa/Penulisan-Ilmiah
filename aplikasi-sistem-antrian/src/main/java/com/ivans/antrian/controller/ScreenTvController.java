/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.AntrianPanggilan;
import com.ivans.antrian.domain.KategoriAntrian;
import com.ivans.antrian.service.AntrianPemanggilanDao;
import com.ivans.antrian.service.KategoriAntrianDao;
import com.ivans.antrian.service.LoketDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ivans
 */
@RestController
@RequestMapping("/api/screen/tv")
public class ScreenTvController {
    private final Logger LOGGER = LoggerFactory.getLogger(ScreenTvController.class);

    @Autowired
    private KategoriAntrianDao categoryDao;
    
    @Autowired
    private LoketDao loketDao;
    
    @Autowired
    private AntrianPemanggilanDao pemanggilanDao;
    
    @RequestMapping(value = "/get/loket/byCategory", method = RequestMethod.GET)
    public Map<String, Object> findAllLoketByCategory() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> listCategory = new ArrayList<Map<String, Object>>();
        
        for(KategoriAntrian category : categoryDao.findAll()){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("category", category);
            map.put("loket", loketDao.findByKategoriIdOrderByNomorLoketAsc(category.getId()));
            listCategory.add(map);
        }
        
        PageRequest pr = new PageRequest(0, 1, Sort.Direction.ASC, "timestamp");
        Page<AntrianPanggilan> pageCall = pemanggilanDao.findByStatus(Boolean.TRUE, pr);
        
        result.put("listCategory", listCategory);
        
        if(pageCall.getTotalElements() > 0){
            result.put("call", pageCall.getContent().get(0));
        }
        
        return result;
    }
}
