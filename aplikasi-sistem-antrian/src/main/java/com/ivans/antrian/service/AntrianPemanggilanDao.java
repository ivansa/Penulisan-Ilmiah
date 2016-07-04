/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.service;

import com.ivans.antrian.domain.AntrianPanggilan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ivans
 */
@Repository
public interface AntrianPemanggilanDao extends PagingAndSortingRepository<AntrianPanggilan, String>{
    
    public AntrianPanggilan findByNomorAntrian(String nomor);
    public Page<AntrianPanggilan> findByStatus(Boolean status, Pageable pageable);
}
