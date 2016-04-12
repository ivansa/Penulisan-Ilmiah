package com.ivans.antrian.domain;


import com.ivans.antrian.constant.RunnumType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;


@Entity @Table(name = "runnum",
        uniqueConstraints = @UniqueConstraint(columnNames = {"run_date","run_month","run_year","run_prefix"}))
public class RunningNumber {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @Column(name="run_date", nullable = false)
    private String date;
    
    @Column(name="run_month", nullable = false)
    private String month;
    
    @Column(name="run_year", nullable = false)
    private String year;
    
    @Column(name="run_prefix", nullable = false)
    private String prefix;
    
    @Column(nullable = false)
    private Long lastnumber;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RunnumType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getLastnumber() {
        return lastnumber;
    }

    public void setLastnumber(Long lastnumber) {
        this.lastnumber = lastnumber;
    }

    public RunnumType getType() {
        return type;
    }

    public void setType(RunnumType type) {
        this.type = type;
    }
    
}
