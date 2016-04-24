/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.domain;

import com.ivans.antrian.constant.BodStatus;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ivans
 */
@Entity @Table(name = "t_bod_process")
public class BodProcess {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @Column(name="created_by", nullable = false)
    private String createdBy;
    
    @Temporal(TemporalType.DATE)
    @Column(name="generate_date",nullable = false)
    private Date generateDate = new Date();
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BodStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(Date generateDate) {
        this.generateDate = generateDate;
    }

    public BodStatus getStatus() {
        return status;
    }

    public void setStatus(BodStatus status) {
        this.status = status;
    }
    
    
}
