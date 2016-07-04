/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.domain;

import java.math.BigDecimal;
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
@Entity
@Table(name = "t_obat_history_detail")
public class HistoryObatDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_history_obat", nullable = false)
    private HistoryObat historyObat;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false)
    private BigDecimal harga;

    @Column(nullable = false)
    private BigDecimal total;
    
    @Column(name = "unit_type", nullable = false)
    private String unitType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HistoryObat getHistoryObat() {
        return historyObat;
    }

    public void setHistoryObat(HistoryObat historyObat) {
        this.historyObat = historyObat;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    
}
