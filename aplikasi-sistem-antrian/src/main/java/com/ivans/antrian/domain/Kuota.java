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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ivans
 */
@Entity @Table(name = "t_kuota",
uniqueConstraints = @UniqueConstraint(columnNames = {"code_dokter","kuota_date"}))
public class Kuota {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @Column(name = "nama_dokter", nullable = false)
    private String namaDokter;
    
    @Column(name = "description_dokter", nullable = false)
    private String descriptionDokter;
    
    @Column(name = "code_dokter", nullable = false)
    private String codeDokter;
    
    @ManyToOne
    @JoinColumn(name = "id_poli", nullable = true)
    private Poli poli;
    
    @Column(name = "maximum_kuota", nullable = false)
    private int maximumKuota;
    
    @Column(name="current_kuota", nullable = false)
    private int currentKuota;
    
    @Temporal(TemporalType.DATE)
    @Column(name="kuota_date", nullable = false)
    private Date kuotaDate = new Date();

    public String getDescriptionDokter() {
        return descriptionDokter;
    }

    public void setDescriptionDokter(String descriptionDokter) {
        this.descriptionDokter = descriptionDokter;
    }

    public String getCodeDokter() {
        return codeDokter;
    }

    public void setCodeDokter(String codeDokter) {
        this.codeDokter = codeDokter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public Poli getPoli() {
        return poli;
    }

    public void setPoli(Poli poli) {
        this.poli = poli;
    }

    public int getMaximumKuota() {
        return maximumKuota;
    }

    public void setMaximumKuota(int maximumKuota) {
        this.maximumKuota = maximumKuota;
    }

    public int getCurrentKuota() {
        return currentKuota;
    }

    public void setCurrentKuota(int currentKuota) {
        this.currentKuota = currentKuota;
    }

    public Date getKuotaDate() {
        return kuotaDate;
    }

    public void setKuotaDate(Date kuotaDate) {
        this.kuotaDate = kuotaDate;
    }
    
    
    
}
