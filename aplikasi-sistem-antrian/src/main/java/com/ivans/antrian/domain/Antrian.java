/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ivans
 */

@Entity @Table(name = "t_log_antrian")
public class Antrian {
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @Column(name="nomor_antrian", nullable = false, unique = true)
    private String nomorAntrian;
    
    @Column(name="jenis_loket", nullable = false)
    private String jenisLoket;
    
    @ManyToOne
    @JoinColumn(name = "id_kuota", nullable = false)
    private Kuota dokter;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="antrian_date", nullable = false)
    private Date antrianDate = new Date();
    
    @Column(nullable = false)
    private Boolean status = Boolean.FALSE;
    //===========================================================
    @Column(name="nomor_loket", nullable = true)
    private int nomorLoket;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_date")
    private Date updatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomorAntrian() {
        return nomorAntrian;
    }

    public void setNomorAntrian(String nomorAntrian) {
        this.nomorAntrian = nomorAntrian;
    }

    public String getJenisLoket() {
        return jenisLoket;
    }

    public void setJenisLoket(String jenisLoket) {
        this.jenisLoket = jenisLoket;
    }

    public Kuota getDokter() {
        return dokter;
    }

    public void setDokter(Kuota dokter) {
        this.dokter = dokter;
    }

    public Date getAntrianDate() {
        return antrianDate;
    }

    public void setAntrianDate(Date antrianDate) {
        this.antrianDate = antrianDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getNomorLoket() {
        return nomorLoket;
    }

    public void setNomorLoket(int nomorLoket) {
        this.nomorLoket = nomorLoket;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    
}
