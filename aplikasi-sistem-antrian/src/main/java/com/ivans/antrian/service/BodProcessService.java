/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.service;

import com.ivans.antrian.constant.BodStatus;
import com.ivans.antrian.domain.BodProcess;
import com.ivans.antrian.domain.Dokter;
import com.ivans.antrian.domain.Kuota;
import com.ivans.antrian.exception.BodException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.temporal.ChronoUnit;

/**
 *
 * @author ivans
 */
@Repository
@Transactional(rollbackFor = BodException.class)
public class BodProcessService {

    @Autowired
    private BodDao bodDao;
    @Autowired
    private KuotaDao kuotaDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PoliDao poliDao;
    @Autowired
    private DokterDao dokterDao;

    private final Logger LOGGER = LoggerFactory.getLogger(BodProcessService.class);

    public void bodProcess(String username) throws BodException {
        bodProcess(new Date(), username);
    }

    public void bodProcess(Date date, String username) throws BodException {

        BodProcess bod = bodDao.findByGenerateDate(date);
        if (bod != null && !bod.getStatus().equals(BodStatus.FAILED)) {
            throw new BodException("Proses bod sudah dilakukan / sedang berjalan");
        }

        LOGGER.info("START BOD [{}]", date);
        if (bod == null) {
            bod = new BodProcess();
            bod.setGenerateDate(date);
            bod.setCreatedBy(username);
        }
        bod.setStatus(BodStatus.RUNNING);
        bodDao.save(bod);

        generateKuota(bod);
    }

    private void generateKuota(BodProcess bod) {
        try {
            Date date = new Date();
            Iterable<Dokter> listDokters = dokterDao.findAll();

            for (Dokter dokter : listDokters) {
                LOGGER.info("GENERATE KUOTA [{}]", dokter.getNip());
                createRecordKuota(date, dokter);
            }

            LOGGER.info("PROCESS BOD SUCCESS");
            bod.setStatus(BodStatus.SUCCEDED);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            bod.setStatus(BodStatus.FAILED);
        }

        bodDao.save(bod);
    }
    
    public void createRecordKuota(Date date, Dokter dokter) throws BodException {

        Kuota currentKuota = kuotaDao.findByNipDokterAndKuotaDate(dokter.getNip(), date);
        if (currentKuota == null) {
            LOGGER.info("New Kuota => " + dokter.getNip());
            currentKuota = new Kuota();
            currentKuota.setCurrentKuota(0);
            currentKuota.setMaximumKuota(setMaximumKuota(date, dokter));
            currentKuota.setNamaDokter(dokter.getName());
            currentKuota.setNamaDokter(dokter.getName());
            currentKuota.setPoli(dokter.getPoli());
            currentKuota.setKuotaDate(date);
            
            kuotaDao.save(currentKuota);
        }

    }
    
    private int setMaximumKuota(Date date, Dokter dokter) throws BodException {
        
        SimpleDateFormat formatHari = new SimpleDateFormat("EEE");
        String hari = formatHari.format(date);
        int maxKuota = 0;
        
        if(hari.equals("Mon")){
            maxKuota = dokter.getKuotaSenin();
        } else if(hari.equals("Tue")){
            maxKuota = dokter.getKuotaSelasa();
        } else if(hari.equals("Wed")){
            maxKuota = dokter.getKuotaRabu();
        } else if(hari.equals("Thu")){
            maxKuota = dokter.getKuotaKamis();
        } else if(hari.equals("Fri")){
            maxKuota = dokter.getKuotaJumat();
        } else if(hari.equals("Sat")){
            maxKuota = dokter.getKuotaSabtu();
        } else if(hari.equals("Sun")){
            maxKuota = dokter.getKuotaMinggu();
        }
        
        return maxKuota;
    }

}
