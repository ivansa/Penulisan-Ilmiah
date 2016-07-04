/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ivans
 */
@Entity
@Table(name = "t_obat_history")
public class HistoryObat {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_pasien", nullable = true)
    private Pasien pasien;

    @Column(nullable = true)
    private String doctorName;

    @Column(nullable = false)
    private String buyerName;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date transactionDate;

    @Column(name = "total_pembelian", nullable = false)
    private BigDecimal totalPembelian;

    @Cascade(CascadeType.ALL)
    @OneToMany(mappedBy = "historyObat", orphanRemoval = true)
    List<HistoryObatDetail> detail = new ArrayList<HistoryObatDetail>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getTotalPembelian() {
        return totalPembelian;
    }

    public void setTotalPembelian(BigDecimal totalPembelian) {
        this.totalPembelian = totalPembelian;
    }

    public List<HistoryObatDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<HistoryObatDetail> detail) {
        this.detail = detail;
    }
    
    
}
