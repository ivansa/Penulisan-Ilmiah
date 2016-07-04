/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.service;

import com.ivans.antrian.domain.Antrian;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ivans
 */
@Repository
public interface AntrianDao extends PagingAndSortingRepository<Antrian, String>{
    public Page<Antrian> findByJenisLoketAndStatusAndAntrianDate(String kategori, Boolean status, String antrianDate, Pageable pageable);
    public Page<Antrian> findByNomorLoketAndAntrianDate(int number, String antrianDate, Pageable pageable);
    public Antrian findByNomorAntrian(String nomor);
    public Long countByJenisLoketAndStatusAndAntrianDate(String kategori, Boolean status, String antrianDate);
}
