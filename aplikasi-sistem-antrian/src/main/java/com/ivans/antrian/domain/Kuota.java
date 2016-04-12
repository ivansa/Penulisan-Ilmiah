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
@Entity @Table(name = "t_kuota")
public class Kuota {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "id_dokter", nullable = false)
    private Dokter dokter;
    
    @Column(name = "maximum_kuota", nullable = false)
    private int maximumKuota;
    
    @Column(name="current_kuota", nullable = false)
    private int currentKuota;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date kuotaDate = new Date();
    
    
}
