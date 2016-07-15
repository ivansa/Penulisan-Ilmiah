/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.service;

import com.ivans.antrian.domain.Kuota;
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
public interface KuotaDao extends PagingAndSortingRepository<Kuota, String>{
    Kuota findByCodeDokterAndKuotaDate(String code, Date date);
    List<Kuota> findByPoliIdAndKuotaDate(String idPoli, Date date);
    Page<Kuota> findByKuotaDate(Date date, Pageable pageable);
    Page<Kuota> findByNamaDokterContainingAndKuotaDate(String dokter, Date date, Pageable pageable);
}
