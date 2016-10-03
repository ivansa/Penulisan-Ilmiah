/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.domain;

/**
 *
 * @author ivans
 */
public class AntrianCreate {
    private String categoryCode;
    private String idKuota;
    private String nomorPasien;
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getIdKuota() {
        return idKuota;
    }

    public void setIdKuota(String idKuota) {
        this.idKuota = idKuota;
    }

    public String getNomorPasien() {
        return nomorPasien;
    }

    public void setNomorPasien(String nomorPasien) {
        this.nomorPasien = nomorPasien;
    }
    
    
}
