/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.service;

import com.ivans.antrian.domain.JadwalDokter;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @authtor ivans
 */
@Repository
public interface DokterDao extends PagingAndSortingRepository<JadwalDokter, String>{
    JadwalDokter findByCode(String code);
}
