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
@Entity @Table(name = "m_dokter")
public class Dokter {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @Column(nullable = false, unique = true)
    private String nip;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "id_dokter", nullable = false)
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
}
