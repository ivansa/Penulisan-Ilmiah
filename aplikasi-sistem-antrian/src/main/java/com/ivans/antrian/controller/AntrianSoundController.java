/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import com.ivans.antrian.domain.AntrianPanggilan;
import com.ivans.antrian.service.AntrianDao;
import com.ivans.antrian.service.AntrianPemanggilanDao;
import com.ivans.antrian.service.KategoriAntrianDao;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ivans
 */
@RestController
@RequestMapping("/api/pemanggilan")
public class AntrianSoundController {

    @Autowired
    private KategoriAntrianDao categoryDao;

    @Autowired
    private AntrianDao antrianDao;

    @Autowired
    private AntrianPemanggilanDao pemanggilanDao;

    @RequestMapping(value = "/set", method = RequestMethod.GET)
    public void set(@RequestParam String number, @RequestParam String loket) {

        AntrianPanggilan pemanggilan = pemanggilanDao.findByNomorAntrian(number);
        if (pemanggilan == null) {
            pemanggilan.setNomorAntrian(number);
            pemanggilan.setTerbilang(setTerbilang(number, loket));
        }

        pemanggilan.setStatus(Boolean.FALSE);
        pemanggilan.setTimestamp(new Date());

        pemanggilanDao.save(pemanggilan);
    }

    @RequestMapping(value = "/recall", method = RequestMethod.GET)
    public void recall(@RequestParam String number) {

        AntrianPanggilan pemanggilan = pemanggilanDao.findByNomorAntrian(number);
        pemanggilan.setStatus(Boolean.FALSE);
        pemanggilan.setTimestamp(new Date());

        pemanggilanDao.save(pemanggilan);
    }

    private String setTerbilang(String nomor, String loket) {
        try {
            String kodeCategory = nomor.substring(0, 1);
            int nomorAntrian = Integer.parseInt(nomor.substring(1, 4));
            String nomorTerbilang = numberToTerbilang(nomorAntrian);
            String loketTerbilang = numberToTerbilang(Integer.parseInt(loket));

            String result;
            if (String.valueOf(nomorAntrian).length() == 1) {
                result = kodeCategory + " kosong kosong " + nomorTerbilang + " counter " + loketTerbilang;
            } else if (String.valueOf(nomorAntrian).length() == 1) {
                result = kodeCategory + " kosong " + nomorTerbilang + " counter " + loketTerbilang;
            } else {
                result = kodeCategory + " " + nomorTerbilang + " counter " + loketTerbilang;
            }

            return result;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }

    }

    private String numberToTerbilang(double angka) {
        String[] nominal = {"", "satu", "dua", "tiga", "empat", "lima", "enam",
            "tujuh", "delapan", "sembilan", "sepuluh", "sebelas"};

        if (angka < 12) {
            return nominal[(int) angka];
        }

        if (angka >= 12 && angka <= 19) {
            return nominal[(int) angka % 10] + " belas ";
        }

        if (angka >= 20 && angka <= 99) {
            return nominal[(int) angka / 10] + " puluh " + nominal[(int) angka % 10];
        }

        if (angka >= 100 && angka <= 199) {
            return "seratus " + numberToTerbilang(angka % 100);
        }

        if (angka >= 200 && angka <= 999) {
            return nominal[(int) angka / 100] + " ratus " + numberToTerbilang(angka % 100);
        }

        if (angka >= 1000 && angka <= 1999) {
            return "seribu " + numberToTerbilang(angka % 1000);
        }

        if (angka >= 2000 && angka <= 999999) {
            return numberToTerbilang((int) angka / 1000) + " ribu " + numberToTerbilang(angka % 1000);
        }

        if (angka >= 1000000 && angka <= 999999999) {
            return numberToTerbilang((int) angka / 1000000) + " juta " + numberToTerbilang(angka % 1000000);
        }

        return "";
    }
}
