/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ivans
 */
@Entity @Table(name = "m_jadwal_dokter")
public class JadwalDokter {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @Column(nullable = false, unique = true)
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "id_poli", nullable = false)
    private Poli poli;
    
    @Column(name = "kuota_senin", nullable = false)
    private int kuotaSenin = 0;
    @Column(name = "kuota_selasa", nullable = false)
    private int kuotaSelasa = 0;
    @Column(name = "kuota_rabu", nullable = false)
    private int kuotaRabu = 0;
    @Column(name = "kuota_kamis", nullable = false)
    private int kuotaKamis = 0;
    @Column(name = "kuota_jumat", nullable = false)
    private int kuotaJumat = 0;
    @Column(name = "kuota_sabtu", nullable = false)
    private int kuotaSabtu = 9;
    @Column(name = "kuota_minggu", nullable = false)
    private int kuotaMinggu = 0;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Poli getPoli() {
        return poli;
    }

    public void setPoli(Poli poli) {
        this.poli = poli;
    }

    public int getKuotaSenin() {
        return kuotaSenin;
    }

    public void setKuotaSenin(int kuotaSenin) {
        this.kuotaSenin = kuotaSenin;
    }

    public int getKuotaSelasa() {
        return kuotaSelasa;
    }

    public void setKuotaSelasa(int kuotaSelasa) {
        this.kuotaSelasa = kuotaSelasa;
    }

    public int getKuotaRabu() {
        return kuotaRabu;
    }

    public void setKuotaRabu(int kuotaRabu) {
        this.kuotaRabu = kuotaRabu;
    }

    public int getKuotaKamis() {
        return kuotaKamis;
    }

    public void setKuotaKamis(int kuotaKamis) {
        this.kuotaKamis = kuotaKamis;
    }

    public int getKuotaJumat() {
        return kuotaJumat;
    }

    public void setKuotaJumat(int kuotaJumat) {
        this.kuotaJumat = kuotaJumat;
    }

    public int getKuotaSabtu() {
        return kuotaSabtu;
    }

    public void setKuotaSabtu(int kuotaSabtu) {
        this.kuotaSabtu = kuotaSabtu;
    }

    public int getKuotaMinggu() {
        return kuotaMinggu;
    }

    public void setKuotaMinggu(int kuotaMinggu) {
        this.kuotaMinggu = kuotaMinggu;
    }
    
    
}
