/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivans.antrian.helper;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;

/**
 *
 * @author adi
 */
public class DateHelper {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DateHelper.class);
    
    public static Date dateWithMinTime(Date startDate){
        Instant start = Instant.ofEpochMilli(startDate.getTime());
        LocalDateTime localdt = LocalDateTime.ofInstant(start, ZoneId.systemDefault());
        localdt = localdt.with(ChronoField.MILLI_OF_DAY, 0);
        return new Date(localdt.toInstant(localdt.atZone(ZoneId.systemDefault()).getOffset()).toEpochMilli());
    }
    
    public static Date dateWithMaxTime(Date endDate){
        Instant start = Instant.ofEpochMilli(endDate.getTime());
        LocalDateTime localdt = LocalDateTime.ofInstant(start, ZoneId.systemDefault());
        localdt = localdt.plus(1, ChronoUnit.DAYS).with(ChronoField.MILLI_OF_DAY, 0).minus(1, ChronoUnit.SECONDS);
        return new Date(localdt.toInstant(localdt.atZone(ZoneId.systemDefault()).getOffset()).toEpochMilli());
    }
    
    public static Date minDateOfMonth(Date date){
        Instant i = Instant.ofEpochMilli(date.getTime());
        LocalDateTime localdt = LocalDateTime.ofInstant(i, ZoneId.systemDefault())
                .with(ChronoField.DAY_OF_MONTH, 1)
                .with(ChronoField.MILLI_OF_DAY, 0);
        return new Date(localdt.toInstant(localdt.atZone(ZoneId.systemDefault()).getOffset()).toEpochMilli());
    }
    
    public static Date maxDateOfMonth(Date date){
        Instant i = Instant.ofEpochMilli(date.getTime());
        LocalDateTime localdt = LocalDateTime.ofInstant(i, ZoneId.systemDefault())
                .plus(1, ChronoUnit.MONTHS)
                .with(ChronoField.DAY_OF_MONTH, 1)
                .with(ChronoField.MILLI_OF_DAY, 0)
                .minus(1, ChronoUnit.SECONDS);
        return new Date(localdt.toInstant(localdt.atZone(ZoneId.systemDefault()).getOffset()).toEpochMilli());
    }
    
    public static Date calculateDate(LocalDateTime ldt, Integer value, ChronoUnit unit){
        ldt = ldt.plus(value, unit);
        return convertToDate(ldt);
    }
    
    public static String dateToString(Date date, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.systemDefault());
        return formatter.format(Instant.ofEpochMilli(date.getTime()));
    }
    
    public static Date stringToDate(String strDate, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.systemDefault());
        return new Date(formatter.parse(strDate).getLong(ChronoField.MILLI_OF_SECOND));
    }
    
    public static Date convertToDate(LocalDateTime localDateTime){
        return new Date(localDateTime.toInstant(localDateTime.atZone(ZoneId.systemDefault()).getOffset()).toEpochMilli());
    }
    
    public static Date convertToDate(Instant instant){
        return new Date(instant.toEpochMilli());
    }
    
    public static Date dateWithField(LocalDateTime ldt, ChronoField field, Integer value){
        ldt = ldt.with(field, value);
        return convertToDate(ldt);
    }
    
    public static Instant calculateDate(Date start, Long inc, ChronoField field, Long valueField){
        
        DateTimeFormatter fmtLengkap = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS").withZone(ZoneId.systemDefault());
        
        Instant startCall = Instant.ofEpochMilli(start.getTime());
        if(field!=null){
            startCall = startCall.with(field, valueField);
        }
        
        LOGGER.debug("CALCULATE SDATE [{}] PLUS [{}] ", fmtLengkap.format(startCall), inc);
        
        Instant endCall = startCall.plusSeconds(inc);
        
        if(field!=null){
            endCall = endCall.with(field, valueField);
        }
        
        LOGGER.debug("CALCULATE EDATE [{}]  ", fmtLengkap.format(endCall));
        return endCall;
    }
    
    public static Date convertInstantToDate(Instant instant){
        return new Date(instant.toEpochMilli());
    }
    
    public static Long compare(Date date1, Date date2){
        Instant i1 = Instant.ofEpochMilli(date1.getTime());
        Instant i2 = Instant.ofEpochMilli(date2.getTime());
        
        return i1.getEpochSecond() - i2.getEpochSecond();
    }
    
    public static Long compare(Instant i1, Instant i2){
        return i1.getEpochSecond() - i2.getEpochSecond();
    }
}