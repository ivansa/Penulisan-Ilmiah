/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.KategoriAntrian;
import com.ivans.antrian.exception.AntrianServerException;
import com.ivans.antrian.service.KategoriAntrianDao;
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
@RequestMapping("/api/master/category")
public class CategoryController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private KategoriAntrianDao categoryDao;
    
    @RequestMapping(method = RequestMethod.GET)
    public Page<KategoriAntrian> allCategory(Pageable pageable, HttpSession session) {
        PageRequest pr = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "code");
        Page<KategoriAntrian> result = categoryDao.findAll(pr);
        return result;
    }
    
    @RequestMapping(value = "/withoutPaging", method = RequestMethod.GET)
    public Iterable<KategoriAntrian> findAllWithoutPaging() {
        return categoryDao.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public KategoriAntrian findCategoryById(@PathVariable String id) {
        KategoriAntrian ka = categoryDao.findOne(id);

        if (ka == null) {
            throw new AntrianServerException("Category Dengan ID : " + id + " Tidak Ditemukan");
        }

        return ka;
    }

    @RequestMapping(value = "/byCode/{code}", method = RequestMethod.GET)
    public KategoriAntrian findByCode(@PathVariable String code) {
        return categoryDao.findByCode(code);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        KategoriAntrian ka = categoryDao.findOne(id);
        if (ka != null) {
            categoryDao.delete(id);
        }else{
            throw new AntrianServerException("Category Dengan ID : " + id + " Tidak Ditemukan");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody @Valid KategoriAntrian category, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Save Category : " + category.getName() + " [ " + category.getCode() + " ]");
        categoryDao.save(category);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable String id, @RequestBody @Valid KategoriAntrian category) {
        KategoriAntrian ka = categoryDao.findOne(id);
        if (ka != null) {
            category.setId(ka.getId());
            categoryDao.save(category);
        }else{
            throw new AntrianServerException("Category Dengan ID : " + id + " Tidak Ditemukan");
        }
    }
    
    @RequestMapping(value="/count", method = RequestMethod.GET)
    public Long count(Pageable pageable){
        return categoryDao.count();
    }
    
}
