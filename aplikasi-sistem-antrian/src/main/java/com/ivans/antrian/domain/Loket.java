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
@Entity @Table(name = "m_loket")
public class Loket {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @Column(nullable = false, unique = true)
    private int nomorLoket;
    
    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private KategoriAntrian kategori;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNomorLoket() {
        return nomorLoket;
    }

    public void setNomorLoket(int nomorLoket) {
        this.nomorLoket = nomorLoket;
    }

    public KategoriAntrian getKategori() {
        return kategori;
    }

    public void setKategori(KategoriAntrian kategori) {
        this.kategori = kategori;
    }
    
    
    
    
}
