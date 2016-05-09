package com.ivans.antrian.service;

import com.ivans.antrian.constant.RunnumType;
import com.ivans.antrian.domain.RunningNumber;
import com.ivans.antrian.helper.DateHelper;
import java.util.Date;
import javax.persistence.EntityManager;
import org.apache.commons.lang.StringUtils;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adi
 */
@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RunningNumberService {

    @Autowired
    private EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RunningNumber getLastRecord(Date date, String codeCategory) {

        Session session = entityManager.unwrap(Session.class);
        String dateStr = DateHelper.dateToString(date, "dd");
        String monthStr = DateHelper.dateToString(date, "MM");
        String yearStr = DateHelper.dateToString(date, "yyyy");
        RunningNumber runnum;
        RunnumType type;
        
        if(codeCategory.equals("B")){
            type = RunnumType.BPJS;
        }else if(codeCategory.equals("A")){
            type = RunnumType.REGULER;
        }else{
            type = RunnumType.FARMASI;
        }
        
        // Running Number For PreOrder ====================
        runnum = (RunningNumber) session.createQuery(
                "SELECT run FROM RunningNumber run WHERE run.year = :year AND run.month = :month AND run.date = :date AND run.prefix = :prefix")
                .setLockOptions(LockOptions.UPGRADE)
                .setParameter("year", yearStr)
                .setParameter("month", monthStr)
                .setParameter("date", dateStr)
                .setParameter("prefix", codeCategory)
                .uniqueResult();
        //===============================================

        logger.debug("Runnum [{}] ", runnum);

        if (runnum == null) {
            runnum = new RunningNumber();
            runnum.setLastnumber(0l);
            runnum.setDate(dateStr);
            runnum.setMonth(monthStr);
            runnum.setYear(yearStr);
            runnum.setType(type);
            runnum.setPrefix(codeCategory);
        }

        return runnum;
    }

    public String getNumber(Date date, String codeCategory) {
        if (date == null) {
            date = new Date();
        }
        RunningNumber runnum = getLastRecord(date, codeCategory);
        runnum.setLastnumber(runnum.getLastnumber() + 1);

        return new StringBuilder()
                .append(runnum.getPrefix())
                .append(StringUtils.leftPad(String.valueOf(runnum.getLastnumber()), 3, "0")).append(" - ")
                .append(runnum.getDate()).append("/")
                .append(runnum.getMonth()).append("/")
                .append(runnum.getYear())
                .toString();
    }

    public String getNumberAndUpdate(Date date, String codeCategory) {
        if (date == null) {
            date = new Date();
        }
        RunningNumber runnum = getLastRecord(date, codeCategory);
        runnum.setLastnumber(runnum.getLastnumber() + 1);
        Session session = entityManager.unwrap(Session.class);
        session.save(runnum);
        return new StringBuilder()
                .append(runnum.getPrefix())
                .append(StringUtils.leftPad(String.valueOf(runnum.getLastnumber()), 3, "0")).append(" - ")
                .append(runnum.getDate()).append("/")
                .append(runnum.getMonth()).append("/")
                .append(runnum.getYear())
                .toString();
    }

    public void update(RunningNumber runnum) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(runnum);
    }

}
